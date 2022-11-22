package com.dogsy.domain.model;

import com.google.firebase.firestore.DocumentId;
import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.IgnoreExtraProperties;

import java.util.Date;
import java.util.List;

@IgnoreExtraProperties
public class User {
    @DocumentId private String id;
    private String firstName;
    private Date birthday;
    private Gender gender;
    private String hometown;
    private String location;
    private String biography;
    private String park;
    private List<String> dogIds;
    private List<String> likedUsers;
    @Exclude private List<byte[]> pictures;

    public User() {
    }

    public User(String id, String firstName, Date birthday, Gender gender, String hometown, String location, String biography, String park, List<String> dogIds, List<String> likedUsers, List<byte[]> pictures) {
        this.id = id;
        this.firstName = firstName;
        this.birthday = birthday;
        this.gender = gender;
        this.hometown = hometown;
        this.location = location;
        this.biography = biography;
        this.park = park;
        this.dogIds = dogIds;
        this.likedUsers = likedUsers;
        this.pictures = pictures;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getPark() {
        return park;
    }

    public void setPark(String park) {
        this.park = park;
    }

    public List<String> getDogIds() {
        return dogIds;
    }

    public void setDogIds(List<String> dogIds) {
        this.dogIds = dogIds;
    }

    public List<String> getLikedUsers() {
        return likedUsers;
    }

    public void setLikedUsers(List<String> likedUsers) {
        this.likedUsers = likedUsers;
    }

    @Exclude
    public List<byte[]> getPictures() {
        return pictures;
    }

    @Exclude
    public void setPictures(List<byte[]> pictures) {
        this.pictures = pictures;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", birthday=" + birthday +
                ", gender=" + gender +
                ", hometown='" + hometown + '\'' +
                ", location='" + location + '\'' +
                ", biography='" + biography + '\'' +
                ", park='" + park + '\'' +
                ", dogs=" + dogIds +
                ", likedUsers=" + likedUsers +
                '}';
    }

    public enum Gender {
        MALE,
        FEMALE,
        NON_BINARY;
    }
}
