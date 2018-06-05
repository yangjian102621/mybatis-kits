package com.aizone.mybatis.plus.test.support.model;


import com.aizone.mybatis.spring.plus.support.BaseModel;

import javax.persistence.Transient;

/**
 *
 * @author chenzhaoju
 */
public class Order extends BaseModel {

    private String number;
    private int count;
    @Transient
    private double amount;

    public Order(String number, int count, double amount) {
        this.number = number;
        this.count = count;
        this.amount = amount;
    }


}
