package com.dogsy.domain.model;

import com.google.firebase.firestore.DocumentId;

import java.util.List;

public class Dog {
    @DocumentId private String id;
    private String name;
    private int age;
    private DogGender gender;
    private boolean isNeutered; // added functionality
    private DogSize size;
    private String breed;
    private String biography;
    private List<Personality> personalities;
    // TODO: Add pictures

    public Dog() {
    }

    public Dog(String id, String name, int age, DogGender gender, boolean isNeutered, DogSize size, String breed, String biography, List<Personality> personalities) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.isNeutered = isNeutered;
        this.size = size;
        this.breed = breed;
        this.biography = biography;
        this.personalities = personalities;
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

    public boolean isNeutered() {
        return isNeutered;
    }

    public void setNeutered(boolean neutered) {
        isNeutered = neutered;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dog dog = (Dog) o;

        if (age != dog.age) return false;
        if (isNeutered != dog.isNeutered) return false;
        if (!id.equals(dog.id)) return false;
        if (!name.equals(dog.name)) return false;
        if (gender != dog.gender) return false;
        if (size != dog.size) return false;
        if (!breed.equals(dog.breed)) return false;
        if (!biography.equals(dog.biography)) return false;
        return personalities.equals(dog.personalities);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + age;
        result = 31 * result + gender.hashCode();
        result = 31 * result + (isNeutered ? 1 : 0);
        result = 31 * result + size.hashCode();
        result = 31 * result + breed.hashCode();
        result = 31 * result + biography.hashCode();
        result = 31 * result + personalities.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", isNeutered=" + isNeutered +
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
        LARGE;
    }

    public enum Personality {
        ACTIVE,
        DOMINANT,
        PATIENT,
        PLAYFUL,
        KID_FRIENDLY,
        COURAGEOUS,
        AFFECTIONATE;
    }
}
