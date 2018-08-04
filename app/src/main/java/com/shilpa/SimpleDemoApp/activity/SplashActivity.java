package com.shilpa.SimpleDemoApp.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


import com.shilpa.SimpleDemoApp.R;
import com.shilpa.SimpleDemoApp.utils.SharedPreferencesManager;

public class SplashActivity extends Activity {
    private static int SPLASH_TIME_OUT = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                    startActivity(new Intent(SplashActivity.this, HomeActivity.class));


                finish();
            }
        },SPLASH_TIME_OUT);
    }
}
