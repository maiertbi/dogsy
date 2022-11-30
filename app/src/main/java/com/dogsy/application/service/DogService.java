package com.dogsy.application.service;

import com.dogsy.domain.model.Dog;
import com.dogsy.domain.model.User;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.firestore.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static com.dogsy.application.service.UserService.USERS_COLLECTION;
import static java.util.Objects.requireNonNull;

public class DogService {

    public static final String DOGS_COLLECTION = "dogs";

    public static final DogService instance = new DogService();

    private final FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();


    private DogService() {
    }

    public void addDog(String dogName, int dogAge, Dog.DogGender dogGender, Dog.DogSize dogSize, String dogBreed, String dogBio, List<Dog.Personality> dogPersonalities, List<byte[]> dogPictures) {
        Dog dog = new Dog(
                null,
                dogName.trim(),
                dogAge,
                dogGender,
                dogSize,
                dogBreed.trim(),
                dogBio.trim(),
                new ArrayList<>(dogPersonalities),
                dogPictures
        );
        pushDogToDB(dog);
    }

    public Optional<Dog> getDogById(String idUser,String idDog) {
        if (idUser.isEmpty() || idDog.isEmpty()) {
            return Optional.empty();
        } else {
            try {
                return Optional.of(fetchDogFromDB(idUser,idDog).get());
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
                return Optional.empty();
            }
        }
    }

    private void pushDogToDB(Dog dog) {
        System.out.println("Pushing dog " + dog + " to DB!");
        firebaseFirestore.runTransaction((Transaction.Function<Void>) transaction -> {
            // Only a reference (object representing the path in the DB) to the user document
            DocumentReference userDocumentRef = firebaseFirestore
                    .collection(USERS_COLLECTION)
                    .document(UserService.instance
                            .getCurrentUser()
                            .orElseThrow(UserService.UnauthenticatedException::new)
                            .getId());
            DocumentReference dogDocumentRef = dog.getId() == null
                    // If a new Dog object is passed to this function, it will not have an id.
                    // Therefore, a new document is created in the DB.
                    // If the passed Dog object already has an id, the corresponding document in the database is selected.
                    ? userDocumentRef.collection(DOGS_COLLECTION).document()
                    : userDocumentRef.collection(DOGS_COLLECTION).document(dog.getId());
            // Transaction performs synchronous get call
            User user = requireNonNull(transaction.get(userDocumentRef).toObject(User.class));
            user.getDogIds().add(dogDocumentRef.getId());
            transaction.set(userDocumentRef, user, SetOptions.merge());
            transaction.set(dogDocumentRef, dog);
            return null;
        }).addOnSuccessListener(s -> {
            System.out.println("Adding dog transaction successful");
            PictureService.instance.pushPictures(dog.getDogPictures(), PictureService.PictureFolder.DOG_PICTURES);
        });
    }

    CompletableFuture<Dog> fetchDogFromDB(String uidUser, String uidDog) {
        // Used as a asynchronous wrapper for the dog object.
        // The future is returned immediately, and is populated with the server response when finished.
        CompletableFuture<Dog> dogCompletableFuture = new CompletableFuture<>();
        firebaseFirestore
                .collection(USERS_COLLECTION)
                .document(requireNonNull(uidUser))
                .collection(DOGS_COLLECTION)
                .document(requireNonNull(uidDog))
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    Dog dog = requireNonNull(documentSnapshot.toObject(Dog.class));
                    System.out.println("Fetched user " + dog + " from DB!");
                    dog.setDogPictures(PictureService.instance.fetchPictures(uidDog, PictureService.PictureFolder.DOG_PICTURES));
                    dogCompletableFuture.complete(dog);
                });
        return dogCompletableFuture;
    }


}
