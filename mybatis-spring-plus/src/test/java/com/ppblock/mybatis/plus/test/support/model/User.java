package com.ppblock.mybatis.plus.test.support.model;


import com.ppblock.mybatis.spring.plus.support.BaseModel;

import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author chenzhaoju
 */
@Table(name = "user")
public class User extends BaseModel<String> {

    private String name;
    private int age;
    private int sex;
    private int status;
    private BigDecimal balance = BigDecimal.ZERO;
    @Transient
    private int phone;

    public User() {}

    public User(String id, String name, int age, int sex) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.createTime = new Date(System.currentTimeMillis());
        this.updateTime = this.createTime;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getSex() {
        return sex;
    }

    public int getPhone() {
        return phone;
    }

    public User setPhone(int phone) {
        this.phone = phone;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", status=" + status +
                ", balance=" + balance +
                ", phone=" + phone +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
