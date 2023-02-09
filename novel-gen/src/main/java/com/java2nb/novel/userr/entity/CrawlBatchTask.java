package com.java2nb.novel.userr.entity;

import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import javax.annotation.Generated;

public class CrawlBatchTask {
    @ApiModelProperty(value = "主键")
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long id;

    @ApiModelProperty(value = "爬虫源ID")
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer sourceId;

    @ApiModelProperty(value = "成功抓取数量")
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer crawlCountSuccess;

    @ApiModelProperty(value = "目标抓取数量")
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer crawlCountTarget;

    @ApiModelProperty(value = "任务状态，1：正在运行，0已停止")
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Byte taskStatus;

    @ApiModelProperty(value = "任务开始时间")
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date startTime;

    @ApiModelProperty(value = "任务结束时间")
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date endTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Long getId() {
        return id;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setId(Long id) {
        this.id = id;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Integer getSourceId() {
        return sourceId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Integer getCrawlCountSuccess() {
        return crawlCountSuccess;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setCrawlCountSuccess(Integer crawlCountSuccess) {
        this.crawlCountSuccess = crawlCountSuccess;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Integer getCrawlCountTarget() {
        return crawlCountTarget;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setCrawlCountTarget(Integer crawlCountTarget) {
        this.crawlCountTarget = crawlCountTarget;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Byte getTaskStatus() {
        return taskStatus;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setTaskStatus(Byte taskStatus) {
        this.taskStatus = taskStatus;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Date getStartTime() {
        return startTime;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Date getEndTime() {
        return endTime;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}