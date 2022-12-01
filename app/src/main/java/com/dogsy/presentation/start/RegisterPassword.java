package com.dogsy.presentation.start;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dogsy.R;

import java.util.regex.Pattern;

public class RegisterPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_password);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        ImageButton ib = findViewById(R.id.ib_reguserpassword);
        EditText et1 = findViewById(R.id.et_reguserpassword1);
        EditText et2 = findViewById(R.id.et_reguserpassword2);
        TextView tv1 = findViewById(R.id.tv_wrongPassword);
        TextView tv2 = findViewById(R.id.t2_regPw);


        ib.setOnClickListener(view -> {
            String userPassword1 = et1.getText().toString();
            String userPassword2 = et2.getText().toString();

            // TODO: FABIAN - add regex-check to the if clause (https://www.w3schools.com/java/java_regex.asp)
            if(userPassword1.isEmpty()){
                tv1.setText("Please enter the password.");
                //Toast.makeText(RegisterPassword.this,"1",Toast.LENGTH_SHORT).show();
                tv2.setTextColor(Color.parseColor("#FFFFFF"));
                return;
            }
            if(userPassword2.isEmpty()){
                tv1.setText("Please confirm the password.");
                tv2.setTextColor(Color.parseColor("#FFFFFF"));
                //Toast.makeText(RegisterPassword.this,"2",Toast.LENGTH_SHORT).show();
                return;
            }
            if (!userPassword1.equals(userPassword2)){
                tv1.setText("Please make sure your passwords matching");
                tv2.setTextColor(Color.parseColor("#FFFFFF"));
                //Toast.makeText(RegisterPassword.this,"3",Toast.LENGTH_SHORT).show();
                return;
            }
            if(!Pattern.matches("^(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[a-zA-Z]).{8,20}$", userPassword1))
            {
                tv2.setTextColor(Color.parseColor("#FF3838"));
                tv1.setText(" ");
                //Toast.makeText(RegisterPassword.this,"4",Toast.LENGTH_SHORT).show();
                return;
            }


            //Toast.makeText(RegisterPassword.this,"pass",Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(getApplicationContext(), RegisterUser.class);
            Bundle bundle = new Bundle();
            bundle.putString("mail", getIntent().getStringExtra("mail"));
            bundle.putString("password", userPassword1);

            intent.putExtras(bundle);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);



        });
    }

    // handles back button
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }
}
