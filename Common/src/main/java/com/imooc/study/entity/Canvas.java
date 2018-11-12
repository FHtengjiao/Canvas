package com.imooc.study.entity;

import java.util.Date;

public class Canvas {
    /**
     * 定义canvas属性，对应canvas表中的字段
     * */
    private Long id;
    private String name;
    private String category;
    private String smallImg;
    private Integer price;
    private Date createTime;
    private Date updateTime;
    private String description;
    private String creator;
    private String details;

    public Canvas() {
    }

    public Canvas(String creator, Date createTime, Date updateTime) {
        this.creator = creator;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Canvas(Date updateTime) {
        this.updateTime = updateTime;
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSmallImg() {
        return smallImg;
    }

    public void setSmallImg(String smallImg) {
        this.smallImg = smallImg;
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

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "Canvas{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", Category='" + category + '\'' +
                ", smallImg=" + smallImg +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", description='" + description + '\'' +
                '}';
    }
}
