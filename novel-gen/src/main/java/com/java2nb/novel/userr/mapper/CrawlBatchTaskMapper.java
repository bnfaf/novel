package com.java2nb.novel.userr.mapper;

import static com.java2nb.novel.userr.mapper.CrawlBatchTaskDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.java2nb.novel.userr.entity.CrawlBatchTask;
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
public interface CrawlBatchTaskMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<CrawlBatchTask> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("CrawlBatchTaskResult")
    CrawlBatchTask selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="CrawlBatchTaskResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="source_id", property="sourceId", jdbcType=JdbcType.INTEGER),
        @Result(column="crawl_count_success", property="crawlCountSuccess", jdbcType=JdbcType.INTEGER),
        @Result(column="crawl_count_target", property="crawlCountTarget", jdbcType=JdbcType.INTEGER),
        @Result(column="task_status", property="taskStatus", jdbcType=JdbcType.TINYINT),
        @Result(column="start_time", property="startTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="end_time", property="endTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<CrawlBatchTask> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(crawlBatchTask);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, crawlBatchTask);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, crawlBatchTask)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(CrawlBatchTask record) {
        return insert(SqlBuilder.insert(record)
                .into(crawlBatchTask)
                .map(id).toProperty("id")
                .map(sourceId).toProperty("sourceId")
                .map(crawlCountSuccess).toProperty("crawlCountSuccess")
                .map(crawlCountTarget).toProperty("crawlCountTarget")
                .map(taskStatus).toProperty("taskStatus")
                .map(startTime).toProperty("startTime")
                .map(endTime).toProperty("endTime")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(CrawlBatchTask record) {
        return insert(SqlBuilder.insert(record)
                .into(crawlBatchTask)
                .map(id).toPropertyWhenPresent("id", record::getId)
                .map(sourceId).toPropertyWhenPresent("sourceId", record::getSourceId)
                .map(crawlCountSuccess).toPropertyWhenPresent("crawlCountSuccess", record::getCrawlCountSuccess)
                .map(crawlCountTarget).toPropertyWhenPresent("crawlCountTarget", record::getCrawlCountTarget)
                .map(taskStatus).toPropertyWhenPresent("taskStatus", record::getTaskStatus)
                .map(startTime).toPropertyWhenPresent("startTime", record::getStartTime)
                .map(endTime).toPropertyWhenPresent("endTime", record::getEndTime)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<CrawlBatchTask>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, sourceId, crawlCountSuccess, crawlCountTarget, taskStatus, startTime, endTime)
                .from(crawlBatchTask);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<CrawlBatchTask>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, sourceId, crawlCountSuccess, crawlCountTarget, taskStatus, startTime, endTime)
                .from(crawlBatchTask);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default CrawlBatchTask selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, sourceId, crawlCountSuccess, crawlCountTarget, taskStatus, startTime, endTime)
                .from(crawlBatchTask)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(CrawlBatchTask record) {
        return UpdateDSL.updateWithMapper(this::update, crawlBatchTask)
                .set(id).equalTo(record::getId)
                .set(sourceId).equalTo(record::getSourceId)
                .set(crawlCountSuccess).equalTo(record::getCrawlCountSuccess)
                .set(crawlCountTarget).equalTo(record::getCrawlCountTarget)
                .set(taskStatus).equalTo(record::getTaskStatus)
                .set(startTime).equalTo(record::getStartTime)
                .set(endTime).equalTo(record::getEndTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(CrawlBatchTask record) {
        return UpdateDSL.updateWithMapper(this::update, crawlBatchTask)
                .set(id).equalToWhenPresent(record::getId)
                .set(sourceId).equalToWhenPresent(record::getSourceId)
                .set(crawlCountSuccess).equalToWhenPresent(record::getCrawlCountSuccess)
                .set(crawlCountTarget).equalToWhenPresent(record::getCrawlCountTarget)
                .set(taskStatus).equalToWhenPresent(record::getTaskStatus)
                .set(startTime).equalToWhenPresent(record::getStartTime)
                .set(endTime).equalToWhenPresent(record::getEndTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(CrawlBatchTask record) {
        return UpdateDSL.updateWithMapper(this::update, crawlBatchTask)
                .set(sourceId).equalTo(record::getSourceId)
                .set(crawlCountSuccess).equalTo(record::getCrawlCountSuccess)
                .set(crawlCountTarget).equalTo(record::getCrawlCountTarget)
                .set(taskStatus).equalTo(record::getTaskStatus)
                .set(startTime).equalTo(record::getStartTime)
                .set(endTime).equalTo(record::getEndTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(CrawlBatchTask record) {
        return UpdateDSL.updateWithMapper(this::update, crawlBatchTask)
                .set(sourceId).equalToWhenPresent(record::getSourceId)
                .set(crawlCountSuccess).equalToWhenPresent(record::getCrawlCountSuccess)
                .set(crawlCountTarget).equalToWhenPresent(record::getCrawlCountTarget)
                .set(taskStatus).equalToWhenPresent(record::getTaskStatus)
                .set(startTime).equalToWhenPresent(record::getStartTime)
                .set(endTime).equalToWhenPresent(record::getEndTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}