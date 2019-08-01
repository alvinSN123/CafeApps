package com.icafebussiness.cafeapps;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.icafebussiness.cafeapps.R;

public class SplashScreen extends AppCompatActivity {

    public static int TIME_OUT = 2000;

    //Nama : Alvin Satria Nugraha
    //Nim : 10116063

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, Login.class);
                startActivity(intent);
                finish();

            }
        },TIME_OUT);
    }
}
