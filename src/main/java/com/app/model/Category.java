package com.app.model;

public class Category {
    private long id;
    private String name;
    //Privet vsem iz preispodni

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
