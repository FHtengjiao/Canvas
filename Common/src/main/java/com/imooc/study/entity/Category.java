package com.imooc.study.entity;

import java.util.Date;

public class Category {
    /**
     * 定义category的属性，对应canvascategory表中的字段
     * */
    private Long id;
    private String name;
    private Date createTime;
    private Date updateTime;
    private String description;

    public Category(){}

    public Category(String name, Date createTime, Date updateTime, String description) {
        this.name = name;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.description = description;
    }

    public Category(Long id, String name, Date updateTime, String description) {
        this.id = id;
        this.name = name;
        this.updateTime = updateTime;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", description='" + description + '\'' +
                '}';
    }
}
