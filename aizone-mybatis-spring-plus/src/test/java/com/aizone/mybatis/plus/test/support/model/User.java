package com.aizone.mybatis.plus.test.support.model;


import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;

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

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String toString() {
        return "[" + id + "," + name + "," + sex + "," + age + "]";
    }

}
