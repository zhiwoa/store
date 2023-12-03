package com.demos.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
//实体类的基本类
public class BaseEntity implements Serializable {
    private String createdUser; //VARCHAR(20) COMMENT '日志-创建人',
    private Date createdTime; //DATETIME COMMENT '日志-创建时间',
    private String modifiedUser;// VARCHAR(20) COMMENT '日志-最后修改执行人',
    private Date modifiedTime ;//DATETIME COMMENT '日志-最后修改时间',
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseEntity)) return false;

        BaseEntity that = (BaseEntity) o;

        if (getCreatedUser() != null ? !getCreatedUser().equals(that.getCreatedUser()) : that.getCreatedUser() != null)
            return false;
        if (getCreatedTime() != null ? !getCreatedTime().equals(that.getCreatedTime()) : that.getCreatedTime() != null)
            return false;
        if (getModifiedUser() != null ? !getModifiedUser().equals(that.getModifiedUser()) : that.getModifiedUser() != null)
            return false;
        return getModifiedTime() != null ? getModifiedTime().equals(that.getModifiedTime()) : that.getModifiedTime() == null;
    }

    @Override
    public int hashCode() {
        int result = getCreatedUser() != null ? getCreatedUser().hashCode() : 0;
        result = 31 * result + (getCreatedTime() != null ? getCreatedTime().hashCode() : 0);
        result = 31 * result + (getModifiedUser() != null ? getModifiedUser().hashCode() : 0);
        result = 31 * result + (getModifiedTime() != null ? getModifiedTime().hashCode() : 0);
        return result;
    }

}
