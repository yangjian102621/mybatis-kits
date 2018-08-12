package com.ppblock.mybatis.plus.test.support.model;


import com.ppblock.mybatis.spring.plus.support.BaseIntegerModel;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * order model
 * @author yangjian
 */
@Table(name = "orders")
public class Order extends BaseIntegerModel {

    @Id
    private String orderNo;

    public Order() {}

    public Order(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderNo='" + orderNo + '\'' +
                '}';
    }
}
