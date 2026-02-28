package com.example.project2.models;

public class OrderModel {
    public String orderNumber, status;
    public int id;

    public OrderModel(int id, String orderNumber, String status) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.status = status;
    }
}
