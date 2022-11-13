package com.dogsy.application.service;

import com.dogsy.domain.model.Dog;
import com.dogsy.domain.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;

public class ProfileService {

    public static final String USERS_COLLECTION = "users";

    // Singleton UserService: only one service instance possible in the app
    public static final ProfileService instance = new ProfileService();

    private final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private final FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    // User of the app
    private User currentUser = null;

    private ProfileService() {
    }

    public User getCurrentUser() {
        System.out.println(firebaseAuth.getCurrentUser());
        System.out.println(currentUser);
        return currentUser;
    }

    public void signInUser(String email, String password) {
        System.out.println("Signing in user '" + email + "' with password " + password);
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult -> fetchCurrentUserFromDB());
    }

    public void registerUser(String userMail, String userPassword, String userName, String userBirthday, String userGender, String userBio, String userHometown, String userLocation, String userPark) {
        signOut(); // TODO: Remove, add sign-out button in presentation layer.
        firebaseAuth.createUserWithEmailAndPassword(userMail, userPassword)
                .addOnSuccessListener(authResult -> {
                    // After successful authentication with Firebase Auth, the user document is created in the DB.
                    try {
                        currentUser = new User(
                                firebaseAuth.getUid(),
                                userName.trim(),
                                new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH).parse(userBirthday), // Can throw a parse exception.
                                User.Gender.valueOf(userGender),
                                userHometown.trim(),
                                userLocation.trim(),
                                userBio.trim(),
                                userPark.trim(),
                                new ArrayList<>(),
                                new ArrayList<>()
                        );
                        // User is stored in DB with uid from Firebase Auth
                        pushCurrentUserToDB();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                });
    }

    public void signOut() {
        firebaseAuth.signOut();
        currentUser = null;
    }

    public void addDog(String dogName, int dogAge, String dogGender, boolean isNeutered, String dogSize, String dogBreed, String dogBio, Set<String> dogPersonalities) {
        Dog dog = new Dog(
                null,
                dogName.trim(),
                dogAge,
                Dog.DogGender.valueOf(dogGender.trim()),
                isNeutered,
                Dog.DogSize.valueOf(dogSize.trim()),
                dogBreed.trim(),
                dogBio.trim(),
                dogPersonalities
                        .stream()
                        .map(String::trim)
                        .map(Dog.Personality::valueOf)
                        .collect(toList())
        );
        this.currentUser.getDogs().add(dog);
        pushCurrentUserToDB();
    }

    private void pushCurrentUserToDB() {
        System.out.println("Pushing current user " + currentUser + " to DB!");
        firebaseFirestore
                .collection(USERS_COLLECTION)
                .document(requireNonNull(firebaseAuth.getUid()))
                .set(this.currentUser, SetOptions.merge());
    }

    // TODO: Consider snapshot listener
    private void fetchCurrentUserFromDB() {
        firebaseFirestore
                .collection(USERS_COLLECTION)
                .document(requireNonNull(firebaseAuth.getUid()))
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    this.currentUser = documentSnapshot.toObject(User.class);
                    System.out.println("Fetched current user " + currentUser + " from DB!");
                });
    }
}
