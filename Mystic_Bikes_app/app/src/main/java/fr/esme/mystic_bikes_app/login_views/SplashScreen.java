package fr.esme.mystic_bikes_app.login_views;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

import fr.esme.mystic_bikes_app.R;

import static fr.esme.mystic_bikes_app.Tools.waitPbar;

public class SplashScreen extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
              startActivity(new Intent(SplashScreen.this, MainActivity.class));
               //finish();
            }}

           , 10000);
       //


    }
}
