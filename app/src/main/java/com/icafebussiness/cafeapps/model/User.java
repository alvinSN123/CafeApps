package com.icafebussiness.cafeapps.model;

public class User {
    private String Name;
    private System Password;

    //Nama : Alvin Satria Nugraha
    //Nim : 10116063

    public User(String name, String s) {
    }

    public User(String name, System password) {
        Name = name;
        Password = password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public System getPassword() {
        return Password;
    }

    public void setPassword(System password) {
        Password = password;
    }
}
