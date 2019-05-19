package com.example.zutshidk.bmi_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
ImageView ivLogo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView ivLogo=findViewById(R.id.ivLogo);
        Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade);
        ivLogo.startAnimation(animation);

        Thread timer= new Thread(){
            @Override
            public void run() {
                try {
                    sleep(3000);
                    Intent intent=new Intent(getApplicationContext(),Personal_details.class);
                    startActivity(intent);
                    finish();
                    super.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        timer.start();


    }
}
