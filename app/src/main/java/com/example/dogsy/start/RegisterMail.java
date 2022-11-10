package com.example.dogsy.start;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dogsy.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterMail extends AppCompatActivity {
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_mail);

        Map<String,Object> users = new HashMap<>();
        users.put("name", "Hans");
        firestore.collection("emails").add(users).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getApplicationContext(), "Worked", Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_LONG).show();
            }
        });



        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        ImageButton ib = findViewById(R.id.ib_regusermail);
        EditText et = findViewById(R.id.et_regusermail);
        TextView tv = findViewById(R.id.tv_nomail);

        ib.setOnClickListener(view -> {
            String userMail = et.getText().toString();

            // TODO: add regex check to the if clause (https://www.w3schools.com/java/java_regex.asp)
            if (userMail.isEmpty()) {
                // TODO: change text-appeareance (color, position) to make it look like LoginActivity.java
                tv.setText("Please enter a correct email");
                return;
            }

            Intent passwordScreen = new Intent(getApplicationContext(), RegisterPassword.class);
            passwordScreen.putExtra("mail", userMail);
            startActivity(passwordScreen);


        });



    }
}
