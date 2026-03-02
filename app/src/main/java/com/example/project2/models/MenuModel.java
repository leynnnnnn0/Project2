package com.example.project2.models;

public class MenuModel {

    public String image_path, name, description;
    public double price;
    public int id, quantity;

    public MenuModel(int id,String image_path, String name, String description, double price, int quantity) {
        this.id = id;
        this.image_path = image_path;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    public MenuModel(int id, String image_path, String name, double price) {
        this.id = id;
        this.image_path = image_path;
        this.name = name;
        this.price = price;
    }


}
