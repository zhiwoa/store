package com.demos.vo;

import java.util.Date;

public class OrderVO {
    private Integer id;
    private Integer oid;
    private Integer pid;
    private String title;
    private String image;
    private Long price;
    private Integer num;
    private Integer status;
    private Date orderTime;
    private String recvName;

    @Override
    public String toString() {
        return "OrderVO{" +
                "id=" + id +
                ", oid=" + oid +
                ", pid=" + pid +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                ", num=" + num +
                ", status=" + status +
                ", orderTime=" + orderTime +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRecvName() {
        return recvName;
    }

    public void setRecvName(String recvName) {
        this.recvName = recvName;
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public OrderVO() {
    }

    public OrderVO(Integer id, Integer oid, Integer pid, String title, String image, Long price, Integer num, Integer status, Date orderTime) {
        this.id = id;
        this.oid = oid;
        this.pid = pid;
        this.title = title;
        this.image = image;
        this.price = price;
        this.num = num;
        this.status = status;
        this.orderTime = orderTime;
    }
}
