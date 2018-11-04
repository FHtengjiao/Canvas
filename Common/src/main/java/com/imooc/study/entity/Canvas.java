package com.imooc.study.entity;

import java.util.Arrays;
import java.util.Date;

public class Canvas {
    /**
     * 定义canvas属性，对应canvas表中的字段
     * */
    private Long id;
    private String name;
    private String category;
    private Byte[] smallImg;
    private Date createTime;
    private Date updateTimte;
    private String description;

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Byte[] getSmallImg() {
        return smallImg;
    }

    public void setSmallImg(Byte[] smallImg) {
        this.smallImg = smallImg;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTimte() {
        return updateTimte;
    }

    public void setUpdateTimte(Date updateTimte) {
        this.updateTimte = updateTimte;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Canvas{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", Category='" + category + '\'' +
                ", smallImg=" + Arrays.toString(smallImg) +
                ", createTime=" + createTime +
                ", updateTimte=" + updateTimte +
                ", description='" + description + '\'' +
                '}';
    }
}
