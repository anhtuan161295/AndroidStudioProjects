package com.example.qq.dawd_assignment09;

import java.io.Serializable;

/**
 * Created by QQ on 4/12/2017.
 */

public class Employee implements Serializable {
    private int id;
    private String name;
    private String dateofbirth;
    private String email;

    public Employee() {
    }

    public Employee(int id, String name, String dateofbirth, String email) {
        this.id = id;
        this.name = name;
        this.dateofbirth = dateofbirth;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(String dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return id + " - " + name;
    }
}
