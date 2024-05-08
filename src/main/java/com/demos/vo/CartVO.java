package com.demos.vo;

import java.io.Serializable;

/**
 * VO全称Value Object,值对象。当进行select查询时,
 * 查询的结果属于多张表中的内容,此时发现结果集不能直接使用某个POJO实体类来接收,
 * 因为POJO实体类不能包含多表查询出来的信息,解决方式是:重新去构建一个新的对象,
 * 这个对象用于存储所查询出来的结果集对应的映射,所以把这个对象称之为值对象.
 */
public class CartVO implements Serializable {
    private Integer cid;
    private Integer uid;
    private Integer pid;
    private Long price;
    private Integer num;
    private String title;
    private Long realPrice;
    private String image;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(Long realPrice) {
        this.realPrice = realPrice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "CartVO{" +
                "cid=" + cid +
                ", uid=" + uid +
                ", pid=" + pid +
                ", price=" + price +
                ", num=" + num +
                ", title='" + title + '\'' +
                ", realPrice=" + realPrice +
                ", image='" + image + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CartVO)) {
            return false;
        }

        CartVO cartVO = (CartVO) o;

        if (getCid() != null ? !getCid().equals(cartVO.getCid()) : cartVO.getCid() != null) {
            return false;
        }
        if (getUid() != null ? !getUid().equals(cartVO.getUid()) : cartVO.getUid() != null) {
            return false;
        }
        if (getPid() != null ? !getPid().equals(cartVO.getPid()) : cartVO.getPid() != null) {
            return false;
        }
        if (getPrice() != null ? !getPrice().equals(cartVO.getPrice()) : cartVO.getPrice() != null) {
            return false;
        }
        if (getNum() != null ? !getNum().equals(cartVO.getNum()) : cartVO.getNum() != null) {
            return false;
        }
        if (getTitle() != null ? !getTitle().equals(cartVO.getTitle()) : cartVO.getTitle() != null) {
            return false;
        }
        if (getRealPrice() != null ? !getRealPrice().equals(cartVO.getRealPrice()) : cartVO.getRealPrice() != null) {
            return false;
        }
        return getImage() != null ? getImage().equals(cartVO.getImage()) : cartVO.getImage() == null;
    }

    @Override
    public int hashCode() {
        int result = getCid() != null ? getCid().hashCode() : 0;
        result = 31 * result + (getUid() != null ? getUid().hashCode() : 0);
        result = 31 * result + (getPid() != null ? getPid().hashCode() : 0);
        result = 31 * result + (getPrice() != null ? getPrice().hashCode() : 0);
        result = 31 * result + (getNum() != null ? getNum().hashCode() : 0);
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        result = 31 * result + (getRealPrice() != null ? getRealPrice().hashCode() : 0);
        result = 31 * result + (getImage() != null ? getImage().hashCode() : 0);
        return result;
    }
}
