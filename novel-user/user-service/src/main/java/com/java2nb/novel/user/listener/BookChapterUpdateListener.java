package com.java2nb.novel.user.listener;

import com.java2nb.novel.user.feign.BookFeignClient;
import com.rabbitmq.client.Channel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class BookChapterUpdateListener {
    private final BookFeignClient bookFeignClient;

    private final RedissonClient redissonClient;

    @SneakyThrows
    @RabbitListener(queues = {"ADD-NEW-CHAPTER"})
    public void updatechapter(Long bookId, Channel channel, Message message) {
        log.debug("收到更新小说章节消息：" + bookId);
        log.info("请刷新以便获取最新章节信息");
    }



}
