package com.icafebussiness.cafeapps;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.icafebussiness.cafeapps.R;

public class Login extends AppCompatActivity {
    Button btn_signin,btn_signup;
    TextView txt_slogan;

    //Nama : Alvin Satria Nugraha
    //Nim : 10116063

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        btn_signin = findViewById(R.id.btn_signin);
        btn_signup = findViewById(R.id.btn_signup);

        txt_slogan = findViewById(R.id.txt_slogan);


        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signUp = new Intent(Login.this, SignUp.class);
                startActivity(signUp);


            }
        });

        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signIn = new Intent(Login.this, Menu.class);
                startActivity(signIn);
            }
        });


    }
}
