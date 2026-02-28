package com.example.project2.models;

public class UserModel {
    public String firstName, email;
    public int id;

    public UserModel(int id, String firstName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.email = email;
    }
}
