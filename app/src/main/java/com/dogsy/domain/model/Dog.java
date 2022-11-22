package com.dogsy.domain.model;

import com.google.firebase.firestore.DocumentId;
import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.IgnoreExtraProperties;

import java.util.List;

@IgnoreExtraProperties
public class Dog {
    @DocumentId private String id;
    private String name;
    private int age;
    private DogGender gender;
    private DogSize size;
    private String breed;
    private String biography;
    private List<Personality> personalities;
    @Exclude private List<byte[]> dogPictures;

    public Dog() {
    }

    public Dog(String id, String name, int age, DogGender gender, DogSize size, String breed, String biography, List<Personality> personalities, List<byte[]> dogPictures) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.size = size;
        this.breed = breed;
        this.biography = biography;
        this.personalities = personalities;
        this.dogPictures = dogPictures;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public DogGender getGender() {
        return gender;
    }

    public void setGender(DogGender gender) {
        this.gender = gender;
    }

    public DogSize getSize() {
        return size;
    }

    public void setSize(DogSize size) {
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

    public List<Personality> getPersonalities() {
        return personalities;
    }

    public void setPersonalities(List<Personality> personalities) {
        this.personalities = personalities;
    }

    @Exclude public List<byte[]> getDogPictures() {
        return dogPictures;
    }

    @Exclude public void setDogPictures(List<byte[]> dogPictures) {
        this.dogPictures = dogPictures;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dog dog = (Dog) o;

        return id.equals(dog.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Dog{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", size=" + size +
                ", breed='" + breed + '\'' +
                ", biography='" + biography + '\'' +
                ", personalities=" + personalities +
                '}';
    }

    public enum DogGender {
        MALE,
        FEMALE;
    }

    public enum DogSize {
        SMALL,
        MEDIUM,
        LARGE
    }

    public enum Personality {
        ACTIVE,
        DOMINANT,
        PATIENT,
        PLAYFUL,
        KID_FRIENDLY,
        COURAGEOUS,
        AFFECTIONATE
    }
}
