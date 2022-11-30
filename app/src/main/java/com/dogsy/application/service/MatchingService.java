package com.dogsy.application.service;

import com.dogsy.domain.model.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class MatchingService {

    public static final MatchingService instance = new MatchingService();

    public static final String USERS_COLLECTION = "users";

    private final FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    public Task<QuerySnapshot> fetchNext() {
        return firebaseFirestore
                .collection(USERS_COLLECTION)
                .whereNotIn(FieldPath.documentId(), List.of("JBK7oA0WK5N2OJucrtzKCWMOnAC2"))
                .limit(1)
                .get();
    }

    public void swipe(User user, boolean hasLike) {
        User currentUser = UserService.instance
                .getCurrentUser()
                .orElseThrow(UserService.UnauthenticatedException::new);

        currentUser.getShownUsers().add(user.getId());

        if (hasLike) {
            currentUser.getLikedUsers().add(user.getId());
        }

        if (hasLike && user.getLikedUsers().contains(currentUser.getId())) {
            currentUser.getMatches().add(user.getId());
            user.getMatches().add(currentUser.getId());
            UserService.instance.pushUserToDB(user);
        }

        UserService.instance.pushUserToDB(currentUser);
    }
    
    /*public void swipe(User userSwipe, boolean like){
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
    }*/
    
}
