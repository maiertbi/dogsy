package com.dogsy.presentation.start;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

import com.dogsy.R;
import com.dogsy.application.service.DogService;
import com.dogsy.domain.model.Dog;
import com.dogsy.presentation.MatchingActivity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class RegisterDog extends AppCompatActivity {
    private String dogName;
    private int dogAge;
    private String dogBreed;
    private Dog.DogGender dogGender;
    private String dogBio;
    private Dog.DogSize dogSize;

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

    private int countPersonality = 0;
    private boolean patient = false;
    private boolean playful = false;
    private boolean active = false;
    private boolean dominant = false;
    private boolean kidfriendly = false;
    private boolean affectionate = false;
    private boolean courageous = false;

    List <Dog.Personality> personalities = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_dog);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        ImageButton ibAddDog = findViewById(R.id.ib_adddog);

        EditText et_dogname = findViewById(R.id.et_dogname);
        EditText et_dogage = findViewById(R.id.et_age);
        EditText et_dogbreed = findViewById(R.id.et_breed);
        EditText et_dogbio = findViewById(R.id.et_bio);
        
        ImageButton ib_dogPic1 = findViewById(R.id.ib_dogpic1);
        ImageButton ib_dogPic2 = findViewById(R.id.ib_dogpic2);
        ImageButton ib_dogPic3 = findViewById(R.id.ib_dogpic3);
        
        ImageButton ib_dogsmall = findViewById(R.id.ib_dogsize1);
        ImageButton ib_dogmedium = findViewById(R.id.ib_dogsize2);
        ImageButton ib_doglarge = findViewById(R.id.ib_dogsize3);
        
        RadioButton rb_male = findViewById(R.id.rb_male);
        RadioButton rb_female = findViewById(R.id.rb_female);

        Button btn_patient = findViewById(R.id.button_patient);
        Button btn_playful = findViewById(R.id.button_playful);
        Button btn_active = findViewById(R.id.button_active);
        Button btn_dominant = findViewById(R.id.button_dominant);
        Button btn_kidfriendly = findViewById(R.id.button_kidfriendly);
        Button btn_affectionate = findViewById(R.id.button_affectionate);
        Button btn_courageous = findViewById(R.id.button_courageous);

        int colorSelected = Color.argb(255, 0, 0, 0); // #000000
        int colorUnselected = Color.argb(255, 169, 169, 169); // #A9A9A9

        btn_patient.setOnClickListener(view -> {
            patient = setPersonality(btn_patient, patient);
        });

        btn_playful.setOnClickListener(view -> {
            playful = setPersonality(btn_playful, playful);
        });

        btn_active.setOnClickListener(view -> {
            active = setPersonality(btn_active, active);
        });

        btn_dominant.setOnClickListener(view -> {
            dominant = setPersonality(btn_dominant, dominant);
        });

        btn_kidfriendly.setOnClickListener(view -> {
            kidfriendly = setPersonality(btn_kidfriendly, kidfriendly);
        });

        btn_affectionate.setOnClickListener(view -> {
            affectionate = setPersonality(btn_affectionate, affectionate);
        });

        btn_courageous.setOnClickListener(view -> {
            courageous = setPersonality(btn_courageous, courageous);
        });

        //define dogsize, change image tints
        ib_dogsmall.setOnClickListener(view -> {
            ib_dogsmall.setColorFilter(colorSelected);
            ib_dogmedium.setColorFilter(colorUnselected);
            ib_doglarge.setColorFilter(colorUnselected);
            dogSize = Dog.DogSize.SMALL;
        });

        ib_dogmedium.setOnClickListener(view -> {
            ib_dogsmall.setColorFilter(colorUnselected);
            ib_dogmedium.setColorFilter(colorSelected);
            ib_doglarge.setColorFilter(colorUnselected);
            dogSize = Dog.DogSize.MEDIUM;
        });

        ib_doglarge.setOnClickListener(view -> {
            ib_dogsmall.setColorFilter(colorUnselected);
            ib_dogmedium.setColorFilter(colorUnselected);
            ib_doglarge.setColorFilter(colorSelected);
            dogSize = Dog.DogSize.LARGE;
        });

        rb_male.setOnClickListener(v -> {
            if(rb_female.isChecked())
                rb_female.setChecked(false);
            dogGender = Dog.DogGender.MALE;
        });

        rb_female.setOnClickListener(v -> {
            if(rb_male.isChecked())
                rb_male.setChecked(false);
            dogGender = Dog.DogGender.FEMALE;
        });

        //from https://www.geeksforgeeks.org/how-to-select-an-image-from-gallery-in-android/
        ib_dogPic1.setOnClickListener(v -> {
            Intent i = new Intent();
            i.setType("image/*");
            i.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE1);
        });

        ib_dogPic2.setOnClickListener(v -> {
            Intent i = new Intent();
            i.setType("image/*");
            i.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE2);
        });

        ib_dogPic3.setOnClickListener(v -> {
            Intent i = new Intent();
            i.setType("image/*");
            i.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE3);
        });
        // for adding another dog
        ibAddDog.setOnClickListener(view -> {
            listPersonalities();
            addDog(et_dogname.getText().toString(),
                    Integer.parseInt(et_dogage.getText().toString()),
                    et_dogbreed.getText().toString(),
                    et_dogbio.getText().toString());

            Intent intent = new Intent(RegisterDog.this, RegisterDog.class);
            startActivity(intent);
            finish();
        });

        //Finish process and continue to app
        ImageButton ibSubmit = findViewById(R.id.ib_submit);
        ibSubmit.setOnClickListener(view -> {

            listPersonalities();//inserts checked button to a List<Personalitys>

            addDog(et_dogname.getText().toString(),
                    Integer.parseInt(et_dogage.getText().toString()),
                    et_dogbreed.getText().toString(),
                    et_dogbio.getText().toString());

            Intent intent = new Intent(RegisterDog.this, MatchingActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            finish();
        });
    }

    private void listPersonalities(){
        if(patient)
            personalities.add(Dog.Personality.PATIENT);
        if(playful)
            personalities.add(Dog.Personality.PLAYFUL);
        if(active)
            personalities.add(Dog.Personality.ACTIVE);
        if(dominant)
            personalities.add(Dog.Personality.DOMINANT);
        if(kidfriendly)
            personalities.add(Dog.Personality.KID_FRIENDLY);
        if(affectionate)
            personalities.add(Dog.Personality.AFFECTIONATE);
        if(courageous)
            personalities.add(Dog.Personality.COURAGEOUS);
    }

    private boolean setPersonality(Button btn, boolean p) {
        if(countPersonality >= 3 && !p){
            return false;
        }
        else if(!p) {
            btn.setBackground(getResources().getDrawable(R.drawable.rounded_corner3));
            countPersonality++;
            return true;
        }
        else{
            btn.setBackground(getResources().getDrawable(R.drawable.rounded_corner));
            countPersonality--;
            return false;
        }
    }


    //from https://www.geeksforgeeks.org/how-to-select-an-image-from-gallery-in-android/
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_OK) return;

        Uri selectedImageUri = data.getData();
        if (selectedImageUri == null) return;

        if (requestCode == SELECT_PICTURE1) {
            ImageButton dogpic1 = findViewById(R.id.ib_dogpic1);
            dogpic1.setImageURI(selectedImageUri);
            try {
                //convert bitmap to byte array to save in db, need to be tested
                //https://stackoverflow.com/questions/9357668/how-to-store-image-in-sqlite-database#:~:text=Inorder%20to%20store%20images%20to,to%20set%20it%20to%20imageview.
                pictureBitmap1 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                pictureBitmap1.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                pictureArray1 = stream.toByteArray();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (requestCode == SELECT_PICTURE2) {
            ImageButton dogpic2 = findViewById(R.id.ib_dogpic2);
            dogpic2.setImageURI(selectedImageUri);
            try {
                //convert bitmap to byte array to save in db, need to be tested
                //https://stackoverflow.com/questions/9357668/how-to-store-image-in-sqlite-database#:~:text=Inorder%20to%20store%20images%20to,to%20set%20it%20to%20imageview.
                pictureBitmap2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                pictureBitmap2.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                pictureArray2 = stream.toByteArray();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (requestCode == SELECT_PICTURE3) {
            ImageButton dogpic3 = findViewById(R.id.ib_dogpic3);
            dogpic3.setImageURI(selectedImageUri);
            try {
                //convert bitmap to byte array to save in db, need to be tested
                //https://stackoverflow.com/questions/9357668/how-to-store-image-in-sqlite-database#:~:text=Inorder%20to%20store%20images%20to,to%20set%20it%20to%20imageview.
                pictureBitmap3 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                pictureBitmap3.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                pictureArray3 = stream.toByteArray();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    private void addDog(String dogName, Integer dogAge, String dogBreed, String dogBio) {
        // TODO: DB - add dog to db - userID missing?
        //  error handling if someting is wrong or missing? (could be ignored theoretically...)
        // Code to add dog to db
        DogService.instance.addDog(
                dogName,
                dogAge,
                dogGender,
                dogSize,
                dogBreed,
                dogBio,
                personalities,
                List.of(pictureArray1,pictureArray2,pictureArray3)
        );
    }

    // handles back button
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }
}
