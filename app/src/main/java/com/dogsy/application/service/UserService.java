package com.dogsy.application.service;

import com.dogsy.domain.model.User;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static java.util.Objects.requireNonNull;

public class UserService {
    public static final String USERS_COLLECTION = "users";

    // Singleton UserService: only one service instance possible in the app
    public static final UserService instance = new UserService();

    private final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private final FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    private UserService() {
    }

    public Optional<User> getCurrentUser() {
        System.out.println("test1");
        if (firebaseAuth.getCurrentUser() == null) {
            System.out.println("test2");
            return Optional.empty();
        } else {
            System.out.println("test3");
            try {
                return Optional.of(fetchUserFromDB(firebaseAuth.getUid()).get());
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
                return Optional.empty();
            }
        }
    }

    public Optional<User> getUserById(String id) {
        if (id.isEmpty()) {
            return Optional.empty();
        } else {
            try {
                return Optional.of(fetchUserFromDB(id).get());
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
                return Optional.empty();
            }
        }
    }

    public Optional<User> getRandomUser() {
        try {
            List<User> users = getListUsers();
            if(users.isEmpty())
                return Optional.empty();
            else{
                int random = (int)(Math.random()*users.size());
                return Optional.of(users.get(random));
            }
        }catch(ExecutionException | InterruptedException e){
            e.printStackTrace();
        }

        return Optional.empty();
    }

    public List<User> getListUsers() throws ExecutionException, InterruptedException {
        CompletableFuture<List<User>> usersCompletableFuture = new CompletableFuture<>();
        firebaseFirestore
                .collection(USERS_COLLECTION)
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    List<User> users = requireNonNull(documentSnapshot.toObjects(User.class));
                    System.out.println("List users " + users + " from DB!");
                    for (User user: users) {
                        user.setPictures(PictureService.instance.fetchPictures(user.getId(), PictureService.PictureFolder.USER_PICTURES));
                    }
                    usersCompletableFuture.complete(users);


                });

        return usersCompletableFuture.get();
    }

    public void signInUser(String email, String password) {
        System.out.println("Signing in user '" + email + "' with password " + password);
        System.out.println(firebaseAuth.signInWithEmailAndPassword(email, password).isSuccessful());
    }

    public void registerUser(String userMail, String userPassword, String userName, String userBirthday, String userGender, String userBio, String userHometown, String userLocation, String userPark, List<byte[]> userPictures) {
        signOut(); // TODO: Remove, add sign-out button in presentation layer.
        firebaseAuth.createUserWithEmailAndPassword(userMail, userPassword)
                .addOnSuccessListener(authResult -> {
                    // After successful authentication with Firebase Auth, the user document is created in the DB.
                    try {
                        User user = new User(
                                firebaseAuth.getUid(),
                                userName.trim(),
                                new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH).parse(userBirthday), // Can throw a parse exception.
                                User.Gender.valueOf(userGender),
                                userHometown.trim(),
                                userLocation.trim(),
                                userBio.trim(),
                                userPark.trim(),
                                new ArrayList<>(), // TODO: check if list.empty() works
                                new ArrayList<>(), // TODO: check if list.empty() works
                                new ArrayList<>(),
                                new ArrayList<>(),
                                userPictures);
                        // User is stored in DB with uid from Firebase Auth
                        pushUserToDB(user);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                });
    }

    public void signOut() {
        firebaseAuth.signOut();
    }

    public void pushUserToDB(User currentUser) {
        System.out.println("Pushing current user " + currentUser + " to DB!");
        if (!currentUser.getId().equals(firebaseAuth.getUid())) {
            // Must be assured by the back-end as well.
            throw new UnauthenticatedException("Write to other user in DB not allowed.");
        }
        firebaseFirestore
                .collection(USERS_COLLECTION)
                .document(requireNonNull(firebaseAuth.getUid()))
                .set(currentUser, SetOptions.merge())
                .addOnSuccessListener(s -> {
                    System.out.println("Pushed user " + currentUser + " to DB!");
                    PictureService.instance.pushPictures(currentUser.getPictures(), PictureService.PictureFolder.USER_PICTURES);
                });
    }

    CompletableFuture<User> fetchUserFromDB(String uid) {
        // Used as a asynchronous wrapper for the user object.
        // The future is returned immediately, and is populated with the server response when finished.
        System.out.println("test4");
        CompletableFuture<User> userCompletableFuture = new CompletableFuture<>();
        System.out.println("test5");
        firebaseFirestore
                .collection(USERS_COLLECTION)
                .document(requireNonNull(uid))
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    User user = requireNonNull(documentSnapshot.toObject(User.class));
                    System.out.println("Fetched user " + user + " from DB!");
                    user.setPictures(PictureService.instance.fetchPictures(uid, PictureService.PictureFolder.USER_PICTURES));
                    userCompletableFuture.complete(user);
                });
        System.out.println("test6");
        return userCompletableFuture;
    }

    static class UnauthenticatedException extends RuntimeException {
        UnauthenticatedException() {
            super("User is not authenticated");
        }
        UnauthenticatedException(String message) {
            super("User is not authenticated: " + message);
        }
    }
}