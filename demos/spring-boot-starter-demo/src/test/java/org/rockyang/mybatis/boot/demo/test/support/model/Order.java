package org.rockyang.mybatis.test.support.model;


import org.rockyang.mybatis.plus.support.BaseModel;

import javax.persistence.Table;

/**
 * order model
 * @author yangjian
 */
@Table(name = "orders")
public class Order extends BaseModel<Integer> {

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
