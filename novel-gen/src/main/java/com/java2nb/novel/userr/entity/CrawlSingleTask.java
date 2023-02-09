package com.java2nb.novel.userr.entity;

import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import javax.annotation.Generated;

public class CrawlSingleTask {
    @ApiModelProperty(value = "主键")
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long id;

    @ApiModelProperty(value = "爬虫源ID")
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer sourceId;

    @ApiModelProperty(value = "源站小说ID")
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String sourceBookId;

    @ApiModelProperty(value = "任务状态，0：失败，1：成功，2；未执行")
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Byte taskStatus;

    @ApiModelProperty(value = "已经执行次数，最多执行5次")
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Byte excCount;

    @ApiModelProperty(value = "创建时间")
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date createTime;

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
    public String getSourceBookId() {
        return sourceBookId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setSourceBookId(String sourceBookId) {
        this.sourceBookId = sourceBookId == null ? null : sourceBookId.trim();
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
    public Byte getExcCount() {
        return excCount;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setExcCount(Byte excCount) {
        this.excCount = excCount;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Date getCreateTime() {
        return createTime;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}