package com.example.project2.models;

public class OrderItemModel {
    public String name;
    public double total;
    public int id, quantity;

    public OrderItemModel(int id, String name, double total, int quantity) {
        this.id = id;
        this.name = name;
        this.total = total;
        this.quantity = quantity;
    }
}
