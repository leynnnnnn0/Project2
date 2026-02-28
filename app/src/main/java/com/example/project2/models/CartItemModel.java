package com.example.project2.models;

public class CartItemModel {
    public String name;
    public double price;
    public int id, quantity;

    public CartItemModel(int id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}
