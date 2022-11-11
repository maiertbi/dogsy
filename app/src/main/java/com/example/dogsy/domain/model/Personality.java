package com.example.dogsy.domain.model;

public class Personality {
    private int id;
    private String name;

    public Personality() {
    }

    public Personality(int id, String name) {
        this.id = id;
        this.name = name;
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
}
