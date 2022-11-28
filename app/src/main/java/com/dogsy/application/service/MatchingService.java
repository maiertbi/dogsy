package com.dogsy.application.service;

import static com.dogsy.application.service.UserService.USERS_COLLECTION;

import static java.util.Objects.requireNonNull;

import com.dogsy.domain.model.User;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.firestore.Transaction;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class MatchingService {

    public static final MatchingService instance = new MatchingService();

    public static final String USERS_COLLECTION = "users";

    private final FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();


    public User fetchNext() {
        CompletableFuture<User> userCompletableFuture = new CompletableFuture<>();
        firebaseFirestore.runTransaction((Transaction.Function<Void>) transaction -> {
            // Only a reference (object representing the path in the DB) to the user document
            DocumentReference userDocumentRef = firebaseFirestore
                    .collection(USERS_COLLECTION)
                    .document(UserService.instance
                            .getCurrentUser()
                            .orElseThrow(UserService.UnauthenticatedException::new)
                            .getId());
            User user = requireNonNull(transaction.get(userDocumentRef).toObject(User.class));
            DocumentReference userNextRef;
            User userNext;
            do {
                userNextRef = firebaseFirestore
                        .collection(USERS_COLLECTION)
                        .document(UserService.instance
                                .getRandomUser()
                                .orElseThrow(UserService.UnauthenticatedException::new)
                                .getId());
                userNext = requireNonNull(transaction.get(userNextRef).toObject(User.class));
            } while (user.getLikedUsers().contains(userNext.getId()) || user.getDislikedUsers().contains(userNext.getId()) || userNext.equals(user)); // TODO: 25/11/2022  add dislikes here as well
            userCompletableFuture.complete(userNext);
            return null;

        });
        try{
            return userCompletableFuture.get();
        }catch(ExecutionException | InterruptedException e){
            e.printStackTrace();
            return null;
        }



    }
    
    public void swipe(User userSwipe, boolean like){
        firebaseFirestore.runTransaction((Transaction.Function<Void>) transaction -> {
            // Only a reference (object representing the path in the DB) to the user document
            DocumentReference userDocumentRef = firebaseFirestore
                    .collection(USERS_COLLECTION)
                    .document(UserService.instance
                            .getCurrentUser()
                            .orElseThrow(UserService.UnauthenticatedException::new)
                            .getId());
            DocumentReference userSwipeDocumentRef = firebaseFirestore
                    .collection(USERS_COLLECTION)
                    .document(UserService.instance
                            .getUserById(userSwipe.getId())
                            .orElseThrow(UserService.UnauthenticatedException::new)
                            .getId());
            User user = requireNonNull(transaction.get(userDocumentRef).toObject(User.class));
            if (like) {
                user.getLikedUsers().add(userSwipe.getId());
                if(userSwipe.getLikedUsers().contains(user.getId())){
                    userSwipe.getMatchedUsers().add(user.getId());
                    user.getMatchedUsers().add(userSwipe.getId());
                    transaction.set(userSwipeDocumentRef,userSwipe,SetOptions.merge());
                }
            } else {
                user.getDislikedUsers().add(userSwipe.getId());
            }
            // Transaction performs synchronous get call
            transaction.set(userDocumentRef, user, SetOptions.merge());
            return null;
        });
    }
    
}
