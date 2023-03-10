package com.java2nb.novel.userr.entity;

import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import javax.annotation.Generated;

public class HomeFriendLink {
    @ApiModelProperty(value = "主键")
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer id;

    @ApiModelProperty(value = "链接名")
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String linkName;

    @ApiModelProperty(value = "链接url")
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String linkUrl;

    @ApiModelProperty(value = "排序号")
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Byte sort;

    @ApiModelProperty(value = "是否开启，0：不开启，1：开启")
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Byte isOpen;

    @ApiModelProperty(value = "创建人id")
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long createUserId;

    @ApiModelProperty(value = "创建时间")
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date createTime;

    @ApiModelProperty(value = "更新者用户id")
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long updateUserId;

    @ApiModelProperty(value = "更新时间")
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date updateTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Integer getId() {
        return id;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setId(Integer id) {
        this.id = id;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getLinkName() {
        return linkName;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setLinkName(String linkName) {
        this.linkName = linkName == null ? null : linkName.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getLinkUrl() {
        return linkUrl;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl == null ? null : linkUrl.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Byte getSort() {
        return sort;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setSort(Byte sort) {
        this.sort = sort;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Byte getIsOpen() {
        return isOpen;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setIsOpen(Byte isOpen) {
        this.isOpen = isOpen;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Long getCreateUserId() {
        return createUserId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
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
    public Long getUpdateUserId() {
        return updateUserId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Date getUpdateTime() {
        return updateTime;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}