package com.dogsy.presentation.start;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dogsy.R;
import com.dogsy.application.service.UserService;
import com.dogsy.domain.model.User;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class RegisterUser extends AppCompatActivity {
    //private int userId = 0;
    private String userMail;
    private String userPassword;
    private byte[] pictureArray1;
    private byte[] pictureArray2;
    private byte[] pictureArray3;
    //private ArrayList<Integer> pictureId;
    int SELECT_PICTURE1 = 100;
    int SELECT_PICTURE2 = 200;
    int SELECT_PICTURE3 = 300;
    private Bitmap pictureBitmap1;
    private Bitmap pictureBitmap2;
    private Bitmap pictureBitmap3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_user);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // TODO: DB - Remove next line. This is just for testing.
        UserService.instance.signOut();

        // get data from previous Intent
        Bundle bundle = getIntent().getExtras();
        userMail = bundle.getString("mail");
        userPassword = bundle.getString("password");

        EditText et_name = findViewById(R.id.et_fn);
        EditText et_dd = findViewById(R.id.et_dd);
        EditText et_mm = findViewById(R.id.et_mm);
        EditText et_yyyy = findViewById(R.id.et_yyyy);
        ImageButton ib_userPic1 = findViewById(R.id.ib_userpic1);
        ImageButton ib_userPic2 = findViewById(R.id.ib_userpic2);
        ImageButton ib_userPic3 = findViewById(R.id.ib_userpic3);
        EditText et_bio = findViewById(R.id.et_userbio);
        EditText et_hometown = findViewById(R.id.et_userhometown);
        EditText et_location = findViewById(R.id.et_userlocation);
        EditText et_park = findViewById(R.id.et_userpark);

        //set spinner values
        Spinner gender = findViewById(R.id.s_gender);

        List<String> genders = Arrays.stream(User.Gender.values())
                .map(Enum::toString)
                .map(String::toLowerCase)
                .collect(toList());

        ArrayAdapter<String> genderAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, genders);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(genderAdapter);


        //from https://www.geeksforgeeks.org/how-to-select-an-image-from-gallery-in-android/
        ib_userPic1.setOnClickListener(v -> {
            Intent i = new Intent();
            i.setType("image/*");
            i.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE1);
        });

        ib_userPic2.setOnClickListener(v -> {
            Intent i = new Intent();
            i.setType("image/*");
            i.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE2);
        });

        ib_userPic3.setOnClickListener(v -> {
            Intent i = new Intent();
            i.setType("image/*");
            i.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE3);
        });

        ImageButton ib = findViewById(R.id.ib_usersubmit);
        ib.setOnClickListener(view -> {
            //Toast.makeText(RegisterUser.this,"submit", Toast.LENGTH_SHORT).show();

            String userName = et_name.getText().toString();
            if(userName.matches("")) {
                Toast.makeText(RegisterUser.this,"Please enter your name", Toast.LENGTH_SHORT).show();
                return;
            }

            int day_num;
            int month_num;
            int year_num;

            String day = et_dd.getText().toString();
            if(day.matches("")) day_num = 0;
            else day_num = Integer.parseInt(day);

            String month = et_mm.getText().toString();
            if(day.matches("")) month_num = 0;
            else month_num = Integer.parseInt(month);

            String year = et_yyyy.getText().toString();
            if(day.matches("")) year_num = 0;
            else year_num = Integer.parseInt(year);

            String userBirthday;

            if(day.matches("")|month.matches("")|year.matches("")|(day_num>31)|(day_num==0)|(month_num>12)|(month_num==0)|(year_num<1900)|(year_num>2100)|(year_num==0)) {
                Toast.makeText(RegisterUser.this,"Please check your birthday", Toast.LENGTH_SHORT).show();
                return;
            }
            else userBirthday = day + "." + month + "." + year;

            String userGender = gender.getSelectedItem().toString().toUpperCase();
            String userBio = et_bio.getText().toString();
            String userHometown = et_hometown.getText().toString();
            String userLocation = et_location.getText().toString();
            String userPark = et_park.getText().toString();


            if(userBio.matches("")) {
                Toast.makeText(RegisterUser.this,"Please write about yourself", Toast.LENGTH_SHORT).show();
                return;
            }
            if(userHometown.matches("")) {
                Toast.makeText(RegisterUser.this,"Please enter your hometown", Toast.LENGTH_SHORT).show();
                return;
            }
            if(userLocation.matches("")) {
                Toast.makeText(RegisterUser.this,"Please enter your location", Toast.LENGTH_SHORT).show();
                return;
            }
            if(userPark.matches("")) {
                Toast.makeText(RegisterUser.this,"Please enter your park", Toast.LENGTH_SHORT).show();
                return;
            }

            // passing input data to UserService
            // TODO: DB - picture bytearrays must be passed to this function in a List.of(byteArray1, byteArray2, ...)
            //  i was not sure if the pictures get saved in the line below or in the function onActivityResult..? - Tobi
            if((pictureArray1==null)|(pictureArray2==null)|(pictureArray3==null)){
                Toast.makeText(RegisterUser.this,"Please upload at least 3 photos", Toast.LENGTH_SHORT).show();
                return;
            }
            UserService.instance.registerUser(userMail, userPassword, userName, userBirthday, userGender, userBio, userHometown, userLocation, userPark, List.of(pictureArray1,pictureArray2,pictureArray3));

            // UserService.instance.addDog("Doggo", 44, "MALE", true, "SMALL", "YES", "I am a dog.", Set.of("ACTIVE", "DOMINANT"));

            // TODO: DB - id of user as extra for intent? I think it will be needed for addDog - Tobi
            Intent intent = new Intent(getApplicationContext(), RegisterDog.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
        });
    }

    //from https://www.geeksforgeeks.org/how-to-select-an-image-from-gallery-in-android/
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_OK) return;

        Uri selectedImageUri = data.getData();
        if (selectedImageUri == null) return;

        if (requestCode == SELECT_PICTURE1) {
            ImageButton userpic1 = findViewById(R.id.ib_userpic1);
            userpic1.setImageURI(selectedImageUri);
            try {
                //convert bitmap to byte array to save in db, need to be tested
                //https://stackoverflow.com/questions/9357668/how-to-store-image-in-sqlite-database#:~:text=Inorder%20to%20store%20images%20to,to%20set%20it%20to%20imageview.
                pictureBitmap1 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                pictureBitmap1.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                pictureArray1 = stream.toByteArray();

                // TODO: DB - pictureArray3 needs to be saved in db as blob, which represents the picture as bytearray
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (requestCode == SELECT_PICTURE2) {
            ImageButton userpic2 = findViewById(R.id.ib_userpic2);
            userpic2.setImageURI(selectedImageUri);
            try {
                //convert bitmap to byte array to save in db, need to be tested
                //https://stackoverflow.com/questions/9357668/how-to-store-image-in-sqlite-database#:~:text=Inorder%20to%20store%20images%20to,to%20set%20it%20to%20imageview.
                pictureBitmap2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                pictureBitmap2.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                pictureArray2 = stream.toByteArray();

                // TODO: DB - pictureArray3 needs to be saved in db as blob, which represents the picture as bytearray
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (requestCode == SELECT_PICTURE3) {
            ImageButton userpic3 = findViewById(R.id.ib_userpic3);
            userpic3.setImageURI(selectedImageUri);
            try {
                //convert bitmap to byte array to save in db, need to be tested
                //https://stackoverflow.com/questions/9357668/how-to-store-image-in-sqlite-database#:~:text=Inorder%20to%20store%20images%20to,to%20set%20it%20to%20imageview.
                pictureBitmap3 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                pictureBitmap3.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                pictureArray3 = stream.toByteArray();

                // TODO: DB - pictureArray3 needs to be saved in db as blob, which represents the picture as bytearray
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    // handles back button
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }
}