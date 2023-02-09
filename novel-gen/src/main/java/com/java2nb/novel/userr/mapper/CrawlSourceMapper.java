package com.java2nb.novel.userr.mapper;

import static com.java2nb.novel.userr.mapper.CrawlSourceDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.java2nb.novel.userr.entity.CrawlSource;
import java.util.List;
import javax.annotation.Generated;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.delete.DeleteDSL;
import org.mybatis.dynamic.sql.delete.MyBatis3DeleteModelAdapter;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.render.RenderingStrategy;
import org.mybatis.dynamic.sql.select.MyBatis3SelectModelAdapter;
import org.mybatis.dynamic.sql.select.QueryExpressionDSL;
import org.mybatis.dynamic.sql.select.SelectDSL;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.MyBatis3UpdateModelAdapter;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;

@Mapper
public interface CrawlSourceMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<CrawlSource> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("CrawlSourceResult")
    CrawlSource selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="CrawlSourceResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="source_name", property="sourceName", jdbcType=JdbcType.VARCHAR),
        @Result(column="source_status", property="sourceStatus", jdbcType=JdbcType.TINYINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="crawl_rule", property="crawlRule", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<CrawlSource> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(crawlSource);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, crawlSource);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Integer id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, crawlSource)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(CrawlSource record) {
        return insert(SqlBuilder.insert(record)
                .into(crawlSource)
                .map(id).toProperty("id")
                .map(sourceName).toProperty("sourceName")
                .map(sourceStatus).toProperty("sourceStatus")
                .map(createTime).toProperty("createTime")
                .map(updateTime).toProperty("updateTime")
                .map(crawlRule).toProperty("crawlRule")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(CrawlSource record) {
        return insert(SqlBuilder.insert(record)
                .into(crawlSource)
                .map(id).toPropertyWhenPresent("id", record::getId)
                .map(sourceName).toPropertyWhenPresent("sourceName", record::getSourceName)
                .map(sourceStatus).toPropertyWhenPresent("sourceStatus", record::getSourceStatus)
                .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                .map(updateTime).toPropertyWhenPresent("updateTime", record::getUpdateTime)
                .map(crawlRule).toPropertyWhenPresent("crawlRule", record::getCrawlRule)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<CrawlSource>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, sourceName, sourceStatus, createTime, updateTime, crawlRule)
                .from(crawlSource);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<CrawlSource>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, sourceName, sourceStatus, createTime, updateTime, crawlRule)
                .from(crawlSource);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default CrawlSource selectByPrimaryKey(Integer id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, sourceName, sourceStatus, createTime, updateTime, crawlRule)
                .from(crawlSource)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(CrawlSource record) {
        return UpdateDSL.updateWithMapper(this::update, crawlSource)
                .set(id).equalTo(record::getId)
                .set(sourceName).equalTo(record::getSourceName)
                .set(sourceStatus).equalTo(record::getSourceStatus)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateTime).equalTo(record::getUpdateTime)
                .set(crawlRule).equalTo(record::getCrawlRule);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(CrawlSource record) {
        return UpdateDSL.updateWithMapper(this::update, crawlSource)
                .set(id).equalToWhenPresent(record::getId)
                .set(sourceName).equalToWhenPresent(record::getSourceName)
                .set(sourceStatus).equalToWhenPresent(record::getSourceStatus)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .set(crawlRule).equalToWhenPresent(record::getCrawlRule);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(CrawlSource record) {
        return UpdateDSL.updateWithMapper(this::update, crawlSource)
                .set(sourceName).equalTo(record::getSourceName)
                .set(sourceStatus).equalTo(record::getSourceStatus)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateTime).equalTo(record::getUpdateTime)
                .set(crawlRule).equalTo(record::getCrawlRule)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(CrawlSource record) {
        return UpdateDSL.updateWithMapper(this::update, crawlSource)
                .set(sourceName).equalToWhenPresent(record::getSourceName)
                .set(sourceStatus).equalToWhenPresent(record::getSourceStatus)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .set(crawlRule).equalToWhenPresent(record::getCrawlRule)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}