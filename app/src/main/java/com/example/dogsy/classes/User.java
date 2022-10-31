package com.example.dogsy.classes;

import java.util.ArrayList;
import java.util.Date;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private Date birthday;
    private char gender;
    private String email;
    private String phone;
    private String password;
    private String city;
    private String country;
    private String biography;
    private ArrayList<Integer> pictureId;
    private ArrayList<Integer> dogId;

    public User() {
    }

    public User(int id, String firstName, String lastName, Date birthday, char gender, String email, String phone, String password, String city, String country, String biography, ArrayList<Integer> pictureId, ArrayList<Integer> dogId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.city = city;
        this.country = country;
        this.biography = biography;
        this.pictureId = pictureId;
        this.dogId = dogId;
    }

    public User(int id, String firstName, String lastName, Date birthday, char gender, String email, String phone, String password, String city, String country, String biography, ArrayList<Integer> pictureId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.city = city;
        this.country = country;
        this.biography = biography;
        this.pictureId = pictureId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public ArrayList<Integer> getPictureId() {
        return pictureId;
    }

    public void setPictureId(ArrayList<Integer> pictureId) {
        this.pictureId = pictureId;
    }

    public ArrayList<Integer> getDogId() {
        return dogId;
    }

    public void setDogId(ArrayList<Integer> dogId) {
        this.dogId = dogId;
    }
}
