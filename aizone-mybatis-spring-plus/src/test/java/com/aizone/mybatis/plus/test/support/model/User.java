package com.aizone.mybatis.plus.test.support.model;


import com.aizone.mybatis.spring.plus.support.BaseModel;

import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author chenzhaoju
 */
@Table(name = "user")
public class User extends BaseModel<String> {

    private String name;
    private int age;
    private int sex;
    private Integer status;
    @Transient
    private int phone;

    public User() {}

    public User(String id, String name, int age, int sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
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

    @Override
    public String toString() {
        return "User{" +
                "id='" + this.getId() + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", status=" + status +
                ", phone=" + phone +
                '}';
    }
}
