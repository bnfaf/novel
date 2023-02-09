package com.java2nb.novel.book.service.impl;

import com.github.pagehelper.PageHelper;
import com.java2nb.novel.book.entity.*;
import com.java2nb.novel.book.feign.UserFeignClient;
import com.java2nb.novel.book.mapper.*;
import com.java2nb.novel.book.service.BookService;
import com.java2nb.novel.book.vo.BookCommentVO;
import com.java2nb.novel.common.bean.PageBean;
import com.java2nb.novel.common.enums.ResponseStatus;
import com.java2nb.novel.common.exception.BusinessException;
import com.java2nb.novel.common.utils.BeanUtil;
import com.java2nb.novel.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import net.sf.jsqlparser.statement.select.Select;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.dynamic.sql.SortSpecification;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.mybatis.dynamic.sql.where.condition.IsEqualTo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.orderbyhelper.OrderByHelper;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.java2nb.novel.book.mapper.BookDynamicSqlSupport.book;
import static org.mybatis.dynamic.sql.SqlBuilder.*;
import static org.mybatis.dynamic.sql.select.SelectDSL.select;

/**
 * 小说服务接口实现
 *
 * @author xiongxiaoyang
 * @version 1.0
 * @since 2020/5/28
 */
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookMapper bookMapper;

    private final BookCategoryMapper bookCategoryMapper;

    private final BookIndexMapper bookIndexMapper;

    private final BookContentMapper bookContentMapper;

    private final BookCommentMapper bookCommentMapper;

    private final BookCommentReplyMapper bookCommentReplyMapper;

    private final UserFeignClient userFeignClient;

    private final RabbitTemplate rabbitTemplate;

    @Override
    public List<Book> queryBookByMinUpdateTime(Date minDate, int limit) {
        return bookMapper.selectMany(select(book.allColumns())
                .from(book)
                .where(BookDynamicSqlSupport.updateTime, isGreaterThan(minDate))
                .orderBy(BookDynamicSqlSupport.updateTime)
                .limit(limit)
                .build()
                .render(RenderingStrategies.MYBATIS3));
    }

    @Override
    public List<Book> queryBookByIds(List<Long> ids) {
        return bookMapper.selectMany(select(BookDynamicSqlSupport.id, BookDynamicSqlSupport.catId, BookDynamicSqlSupport.catName,
                BookDynamicSqlSupport.bookName, BookDynamicSqlSupport.authorName,
                BookDynamicSqlSupport.lastIndexId, BookDynamicSqlSupport.lastIndexName, BookDynamicSqlSupport.lastIndexUpdateTime,
                BookDynamicSqlSupport.picUrl, BookDynamicSqlSupport.bookDesc, BookDynamicSqlSupport.score)
                .from(book)
                .where(BookDynamicSqlSupport.id, isIn(ids))
                .build()
                .render(RenderingStrategies.MYBATIS3)
        );
    }

    @Override
    public List<Book> listRank(Byte type, Integer limit) {
        SortSpecification sortSpecification = BookDynamicSqlSupport.visitCount.descending();
        switch (type) {
            case 1: {
                //最新入库排序
                sortSpecification = BookDynamicSqlSupport.createTime.descending();
                break;
            }
            case 2: {
                //最新更新时间排序
                sortSpecification = BookDynamicSqlSupport.lastIndexUpdateTime.descending();
                break;
            }
            case 3: {
                //评论数量排序
                sortSpecification = BookDynamicSqlSupport.commentCount.descending();
                break;
            }
            default: {
                break;
            }
        }
        SelectStatementProvider selectStatement =
                select(BookDynamicSqlSupport.id, BookDynamicSqlSupport.catId,
                        BookDynamicSqlSupport.catName, BookDynamicSqlSupport.bookName,
                        BookDynamicSqlSupport.lastIndexId, BookDynamicSqlSupport.lastIndexName,
                        BookDynamicSqlSupport.authorId, BookDynamicSqlSupport.authorName,
                        BookDynamicSqlSupport.picUrl, BookDynamicSqlSupport.bookDesc,
                        BookDynamicSqlSupport.wordCount, BookDynamicSqlSupport.lastIndexUpdateTime)
                        .from(book)
                        .where(BookDynamicSqlSupport.wordCount, isGreaterThan(0))
                        .orderBy(sortSpecification)
                        .limit(limit)
                        .build()
                        .render(RenderingStrategies.MYBATIS3);
        return bookMapper.selectMany(selectStatement);
    }

    @Override
    public List<BookCategory> listBookCategory() {
        SelectStatementProvider selectStatementProvider = select(BookCategoryDynamicSqlSupport.id, BookCategoryDynamicSqlSupport.name, BookCategoryDynamicSqlSupport.workDirection)
                .from(BookCategoryDynamicSqlSupport.bookCategory)
                .orderBy(BookCategoryDynamicSqlSupport.sort)
                .build()
                .render(RenderingStrategies.MYBATIS3);
        return bookCategoryMapper.selectMany(selectStatementProvider);
    }

    @Override
    public Book queryBookDetail(Long id) {
        SelectStatementProvider selectStatement = select(BookDynamicSqlSupport.book.allColumns())
                .from(BookDynamicSqlSupport.book)
                .where(BookDynamicSqlSupport.id, isEqualTo(id))
                .limit(1)
                .build()
                .render(RenderingStrategies.MYBATIS3);
        List<Book> books = bookMapper.selectMany(selectStatement);
        return books.size() > 0 ? books.get(0) : null;
    }

    @Override
    public void addVisitCount(Long bookId, int addCount) {
        bookMapper.addVisitCount(bookId, addCount);
    }

    @Override
    public long queryIndexCount(Long bookId) {
        SelectStatementProvider selectStatement = select(count(BookIndexDynamicSqlSupport.id))
                .from(BookIndexDynamicSqlSupport.bookIndex)
                .where(BookIndexDynamicSqlSupport.bookId, isEqualTo(bookId))
                .build()
                .render(RenderingStrategies.MYBATIS3);

        return bookIndexMapper.count(selectStatement);
    }

    @Override
    public BookContent queryBookContent(Long bookIndexId) {
        SelectStatementProvider selectStatement = select(BookContentDynamicSqlSupport.id, BookContentDynamicSqlSupport.content)
                .from(BookContentDynamicSqlSupport.bookContent)
                .where(BookContentDynamicSqlSupport.indexId, isEqualTo(bookIndexId))
                .limit(1)
                .build()
                .render(RenderingStrategies.MYBATIS3);
        return bookContentMapper.selectMany(selectStatement).get(0);
    }

    @Override
    public List<Book> listRecBookByCatId(Integer catId) {
        return bookMapper.listRecBookByCatId(catId);
    }

    @Override
    public PageBean<BookComment> listBookCommentByPage(Long bookId, int page, int pageSize) {
        //分页查询小说评论数据
        PageHelper.startPage(page, pageSize);
        List<BookComment> bookCommentList = bookCommentMapper.selectMany(
                select(BookCommentDynamicSqlSupport.id, BookCommentDynamicSqlSupport.bookId,
                        BookCommentDynamicSqlSupport.createUserId,
                        BookCommentDynamicSqlSupport.commentContent, BookCommentDynamicSqlSupport.replyCount,
                        BookCommentDynamicSqlSupport.createTime)
                        .from(BookCommentDynamicSqlSupport.bookComment)
                        .where(BookCommentDynamicSqlSupport.bookId, isEqualTo(bookId))
                        .orderBy(BookCommentDynamicSqlSupport.createTime.descending())
                        .build()
                        .render(RenderingStrategies.MYBATIS3));

        //根据评论人ID集合查询出评论人集合数据
        List<User> users = userFeignClient.queryById(bookCommentList.stream().map(BookComment::getCreateUserId).collect(Collectors.toList()));

        Map<Long, User> userMap = users.stream().collect(Collectors.toMap(User::getId, Function.identity(), (key1, key2) -> key2));

        //将评论数据和评论人数据关联起来 TODO 评论表增加用户相关的冗余字段，用户信息更新后用户服务通过mq发送message，其他服务消费message更新所有的冗余字段
        List<BookCommentVO> resultList = new ArrayList<>(bookCommentList.size());
        bookCommentList.forEach(bookComment->{
            BookCommentVO bookCommentVO = new BookCommentVO();
            BeanUtils.copyProperties(bookComment, bookCommentVO);
            User user = userMap.get(bookComment.getCreateUserId());
            if (user != null) {
                bookCommentVO.setCreateUserName(user.getUsername());
                bookCommentVO.setCreateUserPhoto(user.getUserPhoto());
            }
            resultList.add(bookCommentVO);
        });
        PageBean<BookComment> pageBean = new PageBean<>(bookCommentList);
        pageBean.setList(resultList);

        return pageBean;
    }

    @Override
    public List<BookIndex> listNewIndex(Long bookId, String orderBy, Integer limit) {
        if (StringUtils.isNotBlank(orderBy)) {
            OrderByHelper.orderBy(orderBy);
        }
//        PageHelper.orderBy(orderBy);
        if (limit != null) {
            PageHelper.startPage(1, limit);
        }

        SelectStatementProvider selectStatement = select(BookIndexDynamicSqlSupport.id, BookIndexDynamicSqlSupport.bookId,
                BookIndexDynamicSqlSupport.indexNum, BookIndexDynamicSqlSupport.indexName,
                BookIndexDynamicSqlSupport.updateTime, BookIndexDynamicSqlSupport.isVip)
                .from(BookIndexDynamicSqlSupport.bookIndex)
                .where(BookIndexDynamicSqlSupport.bookId, isEqualTo(bookId))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        return bookIndexMapper.selectMany(selectStatement);
    }

    @Override
    public Long queryFirstBookIndexId(Long bookId) {
        SelectStatementProvider selectStatement = select(BookIndexDynamicSqlSupport.id)
                .from(BookIndexDynamicSqlSupport.bookIndex)
                .where(BookIndexDynamicSqlSupport.bookId, isEqualTo(bookId))
                .orderBy(BookIndexDynamicSqlSupport.indexNum)
                .limit(1)
                .build()
                .render(RenderingStrategies.MYBATIS3);
        return bookIndexMapper.selectMany(selectStatement).get(0).getId();
    }

    @Override
    public BookIndex queryBookIndex(Long bookIndexId) {
        SelectStatementProvider selectStatement = select(BookIndexDynamicSqlSupport.id, BookIndexDynamicSqlSupport.bookId, BookIndexDynamicSqlSupport.indexNum, BookIndexDynamicSqlSupport.indexName, BookIndexDynamicSqlSupport.wordCount, BookIndexDynamicSqlSupport.updateTime, BookIndexDynamicSqlSupport.isVip)
                .from(BookIndexDynamicSqlSupport.bookIndex)
                .where(BookIndexDynamicSqlSupport.id, isEqualTo(bookIndexId))
                .orderBy(BookIndexDynamicSqlSupport.indexNum)
                .build()
                .render(RenderingStrategies.MYBATIS3);
        return bookIndexMapper.selectMany(selectStatement).get(0);
    }

    @Override
    public Map<String, Long> queryPreAndNextBookIndexId(Long bookId, Integer indexNum) {
        Map<String, Long> result = new HashMap<>(2);
//        // 上一章节的id查询
//        SelectStatementProvider selectStatement = select(BookIndexDynamicSqlSupport.id)
//                .from(BookIndexDynamicSqlSupport.bookIndex)
//                .where(BookIndexDynamicSqlSupport.bookId, isEqualTo(bookId))
//                .and(BookIndexDynamicSqlSupport.indexNum, isLessThan(indexNum))
//                .orderBy(BookIndexDynamicSqlSupport.indexNum.descending())
//                .limit(1)
//                .build()
//                .render(RenderingStrategies.MYBATIS3);
//        List<BookIndex> list = bookIndexMapper.selectMany(selectStatement);
//        if (list.size() == 0) {
//            result.put("preBookIndexId", 0L);
//        } else {
//            result.put("preBookIndexId", list.get(0).getId());
//        }
//
//        selectStatement = select(BookIndexDynamicSqlSupport.id)
//                .from(BookIndexDynamicSqlSupport.bookIndex)
//                .where(BookIndexDynamicSqlSupport.bookId, isEqualTo(bookId))
//                .and(BookIndexDynamicSqlSupport.indexNum, isGreaterThan(indexNum))
//                .orderBy(BookIndexDynamicSqlSupport.indexNum)
//                .limit(1)
//                .build()
//                .render(RenderingStrategies.MYBATIS3);
//        list = bookIndexMapper.selectMany(selectStatement);
//        if (list.size() == 0) {
//            result.put("nextBookIndexId", 0L);
//        } else {
//            result.put("nextBookIndexId", list.get(0).getId());
//        }
        SelectStatementProvider selectStatement = select(BookIndexDynamicSqlSupport.id)
                .from(BookIndexDynamicSqlSupport.bookIndex)
                .where(BookIndexDynamicSqlSupport.bookId, isEqualTo(bookId))
                .and(BookIndexDynamicSqlSupport.indexNum, isIn(indexNum-1, indexNum+1))
                .orderBy(BookIndexDynamicSqlSupport.indexNum)
                .build()
                .render(RenderingStrategies.MYBATIS3);
        List<BookIndex> list = bookIndexMapper.selectMany(selectStatement);
        for (BookIndex bookIndex: list) {
            if (bookIndex.getIndexNum() == indexNum-1){
                result.put("preBookIndexId", bookIndex.getId());
            } else if (bookIndex.getIndexNum() == indexNum+1) {
                result.put("nextBookIndexId", bookIndex.getId());
            }
        }
        if (!result.containsKey("preBookIndexId")){
            result.put("preBookIndexId", 0L);
        }
        if (!result.containsKey("nextBookIndexId")){
            result.put("nextBookIndexId", 0L);
        }



        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addBookComment(Long userId, BookComment comment) {
        // 不太正常的业务流程
        // 涉及多表操作，添加事务
        //判断该用户是否已评论过该书籍
        SelectStatementProvider selectStatement = select(count(BookCommentDynamicSqlSupport.id))
                .from(BookCommentDynamicSqlSupport.bookComment)
                .where(BookCommentDynamicSqlSupport.createUserId, isEqualTo(userId))
                .and(BookCommentDynamicSqlSupport.bookId, isEqualTo(comment.getBookId()))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        if (bookCommentMapper.count(selectStatement) > 0) {
            throw new BusinessException(ResponseStatus.HAS_COMMENTS);
        }
        //增加评论
        comment.setCreateUserId(userId);
        comment.setCreateTime(new Date());
        bookCommentMapper.insertSelective(comment);
        //增加书籍评论数
        bookMapper.addCommentCount(comment.getBookId());

    }

    @Override
    public PageBean<BookComment> listUserCommentByPage(Long userId, int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        return new PageBean<>(bookCommentMapper.selectMany(
                select(BookCommentDynamicSqlSupport.id, BookCommentDynamicSqlSupport.bookId,
                        BookCommentDynamicSqlSupport.createUserId,
                        BookCommentDynamicSqlSupport.commentContent, BookCommentDynamicSqlSupport.replyCount,
                        BookCommentDynamicSqlSupport.createTime)
                        .from(BookCommentDynamicSqlSupport.bookComment)
                        .where(BookCommentDynamicSqlSupport.createUserId, isEqualTo(userId))
                        .orderBy(BookCommentDynamicSqlSupport.createTime.descending())
                        .build()
                        .render(RenderingStrategies.MYBATIS3)));
    }

    @Override
    public List<Book> queryNetworkPicBooks(String localPicPrefix, Integer limit) {


        return bookMapper.selectMany(
                select(BookDynamicSqlSupport.id, BookDynamicSqlSupport.picUrl)
                        .from(book)
                        .where(BookDynamicSqlSupport.picUrl, isLike("http%"))
                        .and(BookDynamicSqlSupport.picUrl, isNotLike(localPicPrefix))
                        .limit(limit)
                        .build()
                .render(RenderingStrategies.MYBATIS3));
    }

    @Override
    public void updateBookPic(String picUrl, Long bookId) {

        bookMapper.update(update(book)
                .set(BookDynamicSqlSupport.picUrl)
                .equalTo(picUrl)
                .set(BookDynamicSqlSupport.updateTime)
                .equalTo(new Date())
                .where(BookDynamicSqlSupport.id, isEqualTo(bookId))
                .build()
                .render(RenderingStrategies.MYBATIS3));

    }
    @Transactional
    @SneakyThrows
    @Override
    public void addBookCommentReply(Long userId, Long commentId, BookCommentReply commentReply) {
        // 根据评论的id查询评论的用户id
        SelectStatementProvider statementProvider = select(BookCommentDynamicSqlSupport.id, BookCommentReplyDynamicSqlSupport.replyContent)
                .from(BookCommentDynamicSqlSupport.bookComment)
                .where(BookContentDynamicSqlSupport.id, isEqualTo(commentId))
                .build().render(RenderingStrategies.MYBATIS3);
        BookComment bookComment = bookCommentMapper.selectMany(statementProvider).get(0);
        Long id = bookComment.getId();
        Integer replyCount = bookComment.getReplyCount();
        // 增加该条评论的回复数量并保存
        UpdateStatementProvider updateStatementProvider = update(BookCommentDynamicSqlSupport.bookComment)
                .set(BookCommentDynamicSqlSupport.createTime)
                .equalTo(new Date())
                .set(BookCommentDynamicSqlSupport.replyCount)
                .equalTo(replyCount + 1)
                .where(BookCommentDynamicSqlSupport.id, isEqualTo(commentId))
                .build().render(RenderingStrategies.MYBATIS3);
        bookCommentMapper.update(updateStatementProvider);
        // 保存该条评论的回复
        commentReply.setCommentId(id);
        commentReply.setCreateTime(new Date());
        commentReply.setCreateUserId(userId);
        bookCommentReplyMapper.insert(commentReply);
    }
    @Transactional
    @SneakyThrows
    @Override
    public void addBookChatperContent(Long bookId, Integer indexNum, Integer indexName, Integer wordCount, String content) {
        // 1. 向book_index新增数据
        Date curTime = new Date();
        BookIndex bookIndex = new BookIndex();
        bookIndex.setBookId(bookId);
        bookIndex.setCreateTime(curTime);
        bookIndex.setIndexNum(indexNum);
        bookIndex.setUpdateTime(curTime);
        bookIndex.setWordCount(wordCount);
        bookIndexMapper.insert(bookIndex);
        // 2. 想book_content新增数据
        BookContent bookContent = new BookContent();
        SelectStatementProvider selectStatementProvider = select(BookIndexDynamicSqlSupport.id)
                .from(BookIndexDynamicSqlSupport.bookIndex)
                .where(BookIndexDynamicSqlSupport.bookId, isEqualTo(bookId))
                .and(BookIndexDynamicSqlSupport.indexNum, isEqualTo(indexNum))
                .limit(1)
                .build()
                .render(RenderingStrategies.MYBATIS3);
        Long id = bookIndexMapper.selectMany(selectStatementProvider).get(0).getId();
        bookContent.setIndexId(id);
        bookContent.setContent(content);
        bookContentMapper.insert(bookContent);
        // 3.  通过mq向用户发送章节更新提醒

        rabbitTemplate.convertAndSend("ADD-NEW-CHAPTER", null, bookId);
    }


}
