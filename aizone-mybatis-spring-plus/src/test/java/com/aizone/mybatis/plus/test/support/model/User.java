package com.aizone.mybatis.plus.test.support.model;


import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

/**
 *
 * @author yangjian
 */
@Table(name = "user")
public class User {
    @Column(name = "id")
    private String id;
    private String name;
    private int age;
    private int sex;
    private Date addTime;
    private Integer status;
    @Transient
    private int phone;

    public User() {}

    public User(String id, String name, int age, int sex) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public String getId() {
        return id;
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

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
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

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", addTime=" + addTime +
                ", status=" + status +
                ", phone=" + phone +
                '}';
    }
}
