package com.example.project2.models;

public class UserModel {


    public String fullname, email, password, role;
    public int id;


    public UserModel(int id, String fullname, String email, String password, String role) {
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.role = role;
    }


    public UserModel(int id, String fullname, String email) {
        this.id = id;
        this.fullname = fullname;
        this.email = email;
    }


    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getFullname() {
        return fullname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
}
