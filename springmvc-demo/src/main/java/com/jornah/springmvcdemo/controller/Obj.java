package com.jornah.springmvcdemo.controller;

public class Obj{
    private String payname;
    private long id;

    public Obj(String payname, Long id) {
        this.payname = payname;
        this.id = id;
    }

    public void setPayname(String payname) {
        this.payname = payname;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPayname() {
        return payname;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Obj{" +
                "payname='" + payname + '\'' +
                ", id=" + id +
                '}';
    }
}