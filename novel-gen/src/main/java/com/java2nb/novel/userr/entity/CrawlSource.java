package com.java2nb.novel.userr.entity;

import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import javax.annotation.Generated;

public class CrawlSource {
    @ApiModelProperty(value = "主键")
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer id;

    @ApiModelProperty(value = "源站名")
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String sourceName;

    @ApiModelProperty(value = "爬虫源状态，0：关闭，1：开启")
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Byte sourceStatus;

    @ApiModelProperty(value = "创建时间")
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date updateTime;

    @ApiModelProperty(value = "爬取规则（json串）")
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String crawlRule;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Integer getId() {
        return id;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setId(Integer id) {
        this.id = id;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getSourceName() {
        return sourceName;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setSourceName(String sourceName) {
        this.sourceName = sourceName == null ? null : sourceName.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Byte getSourceStatus() {
        return sourceStatus;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setSourceStatus(Byte sourceStatus) {
        this.sourceStatus = sourceStatus;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Date getCreateTime() {
        return createTime;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Date getUpdateTime() {
        return updateTime;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getCrawlRule() {
        return crawlRule;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setCrawlRule(String crawlRule) {
        this.crawlRule = crawlRule == null ? null : crawlRule.trim();
    }
}