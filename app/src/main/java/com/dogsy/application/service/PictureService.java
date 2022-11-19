package com.dogsy.application.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.IntStream;

import static com.dogsy.application.service.UserService.USERS_COLLECTION;
import static java.util.stream.Collectors.toList;

public class PictureService {

    public static final PictureService instance = new PictureService();

    private final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private final FirebaseStorage storage = FirebaseStorage.getInstance();

    private PictureService() {
    }

    void pushPictures(List<byte[]> pictures, PictureFolder pictureFolder) {
        StorageReference userPicturesRef = storage
                .getReference()
                .child(USERS_COLLECTION + "/" + firebaseAuth.getUid() + "/" + pictureFolder.folderName);
        IntStream.range(0, pictures.size())
                .forEach(i -> userPicturesRef.child(String.valueOf(i)).putBytes(pictures.get(i)));
    }

    List<byte[]> fetchPictures(String uid, PictureFolder pictureFolder) {
        List<CompletableFuture<byte[]>> picturesFutures = new ArrayList<>();
        // Create a storage reference
        StorageReference picturesRef = storage
                .getReference()
                .child(USERS_COLLECTION + "/" + uid + "/" + pictureFolder.folderName);
        picturesRef.listAll()
                .addOnSuccessListener(listResult -> listResult.getItems()
                        .stream()
                        .map(picture -> picture.getBytes(10000000)) // Picture size must be lower than 10 MB
                        .forEach(task -> {
                            CompletableFuture<byte[]> future = new CompletableFuture<>();
                            picturesFutures.add(future);
                            task.addOnSuccessListener(future::complete);
                        }));
        return picturesFutures.stream()
                .map(pictureFuture -> {
                    try {
                        return pictureFuture.get();
                    } catch (ExecutionException | InterruptedException e) {
                        e.printStackTrace();
                        return null;
                    }
                })
                .collect(toList());
    }

    enum PictureFolder {
        USER_PICTURES("user_pictures"),
        DOG_PICTURES("dog_pictures");

        public final String folderName;

        PictureFolder(String folderName) {
            this.folderName = folderName;
        }
    }

}
