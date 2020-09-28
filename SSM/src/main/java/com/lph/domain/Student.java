package com.lph.domain;

import java.io.Serializable;

public class Student implements Serializable {
    private String id;
    private String name;
    private String password;
    private String email;
    private String telephone;
    private int state;//设置状态  //0:离线  1:在线  2:测试中
    private int type; //用户类型  //0:普通的  1:监考老师 2:管理员

    public Student() {
    }

    public Student(String id, String name, String password, String email, String telephone, int state, int type) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.telephone = telephone;
        this.state = state;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", state=" + state +
                ", type=" + type +
                '}';
    }
}
