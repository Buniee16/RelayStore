package com.example.relaystore;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.relaystore.Session.SessionManager;

public class SplashScreen extends AppCompatActivity {
    ImageView iv;
    RelativeLayout linear;
    Thread SplashThread;

    SessionManager session;



    /* Duration of wait */
    private final int SPLASH_DISPLAY_LENGTH = 1000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        session = new SessionManager(getApplicationContext());
        iv=findViewById(R.id.splashscreen);
        linear=findViewById(R.id.linear);
        startAnimations();
    }

    private void startAnimations() {

        //   Animation rotate = AnimationUtils.loadAnimation(this, R.anim.rotate);
        // Animation translate = AnimationUtils.loadAnimation(this, R.anim.translate);

//rotate.reset();
// translate.reset();
        linear.clearAnimation();

// iv.startAnimation(rotate);
// tvSplash.startAnimation(translate);
        SplashThread = new Thread(){
            @Override
            public void run() {
                super.run();
                int waited = 0;
                while (waited < 3500) {
                    try {
                        sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    waited += 100;
                }
                session.checkLogin();
//                Intent intent = new Intent(SplashScreen.this, Choose_payment_method.class);
//intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                startActivity(intent);
                SplashScreen.this.finish();
            }
        };
        SplashThread.start();
    }
}