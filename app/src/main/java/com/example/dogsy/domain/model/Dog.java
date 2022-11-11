package com.example.dogsy.domain.model;

import java.util.ArrayList;

public class Dog {
    private int id;
    private String name;
    private int age;
    private char gender;
    private char size;
    private String breed;
    private String biography;
    private int userId;
    private ArrayList<Integer> pictureId;
    private ArrayList<Integer> personalityId;

    public Dog() {
    }

    public Dog(int id, String name, int age, char gender, char size, String breed, String biography, int userId, ArrayList<Integer> pictureId, ArrayList<Integer> personalityId) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.size = size;
        this.breed = breed;
        this.biography = biography;
        this.userId = userId;
        this.pictureId = pictureId;
        this.personalityId = personalityId;
    }

    public Dog(int id, String name, int age, char gender, char size, String breed, String biography, int userId, ArrayList<Integer> pictureId) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.size = size;
        this.breed = breed;
        this.biography = biography;
        this.userId = userId;
        this.pictureId = pictureId;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public char getSize() {
        return size;
    }

    public void setSize(char size) {
        this.size = size;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public ArrayList<Integer> getPictureId() {
        return pictureId;
    }

    public void setPictureId(ArrayList<Integer> pictureId) {
        this.pictureId = pictureId;
    }

    public ArrayList<Integer> getPersonalityId() {
        return personalityId;
    }

    public void setPersonalityId(ArrayList<Integer> personalityId) {
        this.personalityId = personalityId;
    }
}
