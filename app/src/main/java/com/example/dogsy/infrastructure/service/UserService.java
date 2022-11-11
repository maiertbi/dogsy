package com.example.dogsy.infrastructure.service;

import com.example.dogsy.domain.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Objects;

public class UserService {

    //Singleton UserService instance: only one Service instance possible in the app
    public final static UserService instance = new UserService();


    private final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private final FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    //user of the app
    private User currentUser = null;

    private UserService() {}

    public User getCurrentUser() {
        System.out.println(firebaseAuth.getCurrentUser());
        return currentUser;
    }

    public void signInUser(String email, String password) {
        System.out.println("Signing in user " + email + " " + password);
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult -> this.currentUser = firebaseFirestore
                        .collection("users")
                        .document(Objects.requireNonNull(firebaseAuth.getUid()))
                        .get()
                        .getResult()
                        .toObject(User.class))
                .addOnFailureListener(System.out::println);
    }

    public void registerUser(
            String userMail,
            String userPassword,
            String userName,
            String userBirthday,
            String userGender,
            String userBio,
            String userHometown,
            String userLocation,
            String userPark
    ) throws ParseException {
        signOut();
        firebaseAuth.createUserWithEmailAndPassword(userMail, userPassword)
                .addOnSuccessListener(authResult -> {
                    //after successful Auth with FireAuth user is created
                    try {
                        User user = new User(
                                firebaseAuth.getUid(),
                                userName,
                                new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH).parse(userBirthday),
                                userGender,
                                userMail,
                                userHometown,
                                userLocation,
                                userBio,
                                null,
                                null,
                                userPark
                        );
                        //user is stored in database with UID from firebaseAuth
                        firebaseFirestore
                                .collection("users")
                                .document(Objects.requireNonNull(firebaseAuth.getUid()))
                                .set(user);
                        //gets Uid, loginDate,
                        this.currentUser = user;
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                })
                .addOnFailureListener(System.out::println);
    }

    public void signOut() {
        currentUser = null;
        firebaseAuth.signOut();
    }

}
