package com.example.aiproject;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

public class Splashactivitity extends AppCompatActivity {

   private LottieAnimationView  l1;
    int j1=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashactivitity);

          l1=findViewById(R.id.lotti1ani);


        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                // This method will be executed once the timer is over
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                overridePendingTransition(R.anim.push_right_in,R.anim.push_left_in);
                startActivity(i);
                finish();
            }
        }, 2500);


    }
}
