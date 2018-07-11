package com.xinerji.xinerjidc.activity;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.xinerji.xinerjidc.R;
import com.xinerji.xinerjidc.utilities.PopupUtil;

public class SplashActivity extends AppCompatActivity {


    final PopupUtil popupUtil = new PopupUtil();
    CountDownTimer timer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        timer = new CountDownTimer(3000, 1000) {

            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {


                Intent intent = new Intent(SplashActivity.this, LogonActivity.class);
                startActivity(intent);

            }
        };

        timer.start();
    }


}
