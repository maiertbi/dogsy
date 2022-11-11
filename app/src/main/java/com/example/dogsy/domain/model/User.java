package com.example.dogsy.domain.model;

import java.util.ArrayList;
import java.util.Date;


public class User {
    //better with backendservice: get all users from database? private static ArrayList<User> users = new ArrayList<>();


    private String id;
    private String firstName;
    //was not in plan private String lastName;
    // change to what?? TODO: change dataype Date
    private Date birthday;
    private String gender;
    private String email;

    //why? private String password;
    private String city;
    private String country;
    private String biography;
    private ArrayList<Integer> pictureId;
    private ArrayList<Integer> dogId;
    private String park;

    public User() {
    }


    public User(String id, String firstName, /*String lastName,*/ Date birthday, String gender, String email, /*String password,*/ String city, String country, String biography, ArrayList<Integer> pictureId, ArrayList<Integer> dogId, String park) {
        this.id = id;
        this.firstName = firstName;
        //this.lastName = lastName;
        this.birthday = birthday;
        this.gender = gender;
        this.email = email;

        //why? this.password = password;
        this.city = city;
        this.country = country;
        this.biography = biography;
        this.pictureId = pictureId;
        this.dogId = dogId;
        this.park = park;
    }

/*    public User(int id, String firstName, String lastName, Date birthday, String gender, String email, String phone, String password, String city, String country, String biography, ArrayList<Integer> pictureId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.gender = gender;
        this.email = email;

        //why? this.password = password;
        this.city = city;
        this.country = country;
        this.biography = biography;
        this.pictureId = pictureId;
    }*/

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

    /*public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
*/
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    /* public String getPassword() {
         return password;
     }

     public void setPassword(String password) {
         this.password = password;
     }*/
    public String getPark() {
        return park;
    }

    public void setPark(String park) {
        this.park = park;
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

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", birthday=" + birthday +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", biography='" + biography + '\'' +
                ", pictureId=" + pictureId +
                ", dogId=" + dogId +
                ", park='" + park + '\'' +
                '}';
    }




    /*public static ArrayList<User> getUsers() {
        return users;
    }

    public static void setUsers(ArrayList<User> users) {
        User.users = users;
    }

    public static boolean addUser(User user) {
        return users.add(user);
    }
*/
    /*// TODO: create own Exceptions
    public static boolean userExists(String email) {
        for (User user: users) {
            if (user.getEmail().equals(email)) return true;
        }
        return false;
    }*/

    /* comes with firebase UserService:
    public static boolean checkPassword(String email, String password) {
        // has to be checked if userExists
        for (User user: users) {
            if (user.getEmail().equals(email)) {
                return (user.getPassword().equals(password));
            }
        }
        return false;
    }*/
/*
    public static boolean createDummyUsers() {
        users.add(new User(0, "Paul", "White", new Date(), 'm',
                "paul.white@gmail.com", "004366456743", "thisisThePassword",
                "Vienna", "Austria", "This is my biograpy",
                new ArrayList<>(Arrays.asList(1, 2, 3)), new ArrayList<>(Arrays.asList(1, 2))));
        users.add(new User(1, "Clara", "Black", new Date(), 'f',
                "Clara.Black@gmail.com", "00353675839", "claraPassword1",
                "Dublin", "Ireland", "This is my biograpy2",
                new ArrayList<>(Collections.singletonList(4)), new ArrayList<>(Collections.emptyList())));
        users.add(new User(2, "Logan", "Paul", new Date(), 'm',
                "logan.paul@gmail.com", "004965839020", "loganPaul23",
                "Dublin", "Ireland", "This is my biograpy3",
                new ArrayList<>(Arrays.asList(5, 6)), new ArrayList<>(Collections.singletonList(3))));
        return true;
    }*/
}
