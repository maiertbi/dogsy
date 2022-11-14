package com.dogsy.domain.model;

import com.google.firebase.firestore.DocumentId;

import java.util.Date;
import java.util.List;

public class User {
    @DocumentId private String id;
    private String firstName;
    private Date birthday;
    private Gender gender;
    private String hometown;
    private String location;
    private String biography;
    private String park;
    private List<Dog> dogs;
    private List<User> likedUsers;
    // TODO: Add pictures

    public User() {
    }

    public User(String id, String firstName, Date birthday, Gender gender, String hometown, String location, String biography, String park, List<Dog> dogs, List<User> likedUsers) {
        this.id = id;
        this.firstName = firstName;
        this.birthday = birthday;
        this.gender = gender;
        this.hometown = hometown;
        this.location = location;
        this.biography = biography;
        this.park = park;
        this.dogs = dogs;
        this.likedUsers = likedUsers;
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

    public List<Dog> getDogs() {
        return dogs;
    }

    public void setDogs(List<Dog> dogs) {
        this.dogs = dogs;
    }

    public List<User> getLikedUsers() {
        return likedUsers;
    }

    public void setLikedUsers(List<User> likedUsers) {
        this.likedUsers = likedUsers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!id.equals(user.id)) return false;
        if (!firstName.equals(user.firstName)) return false;
        if (!birthday.equals(user.birthday)) return false;
        if (gender != user.gender) return false;
        if (!hometown.equals(user.hometown)) return false;
        if (!location.equals(user.location)) return false;
        if (!biography.equals(user.biography)) return false;
        if (!park.equals(user.park)) return false;
        if (!dogs.equals(user.dogs)) return false;
        return likedUsers.equals(user.likedUsers);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + birthday.hashCode();
        result = 31 * result + gender.hashCode();
        result = 31 * result + hometown.hashCode();
        result = 31 * result + location.hashCode();
        result = 31 * result + biography.hashCode();
        result = 31 * result + park.hashCode();
        result = 31 * result + dogs.hashCode();
        result = 31 * result + likedUsers.hashCode();
        return result;
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
                ", dogs=" + dogs +
                ", likedUsers=" + likedUsers +
                '}';
    }

    public enum Gender {
        MALE,
        FEMALE,
        NON_BINARY;
    }
}
