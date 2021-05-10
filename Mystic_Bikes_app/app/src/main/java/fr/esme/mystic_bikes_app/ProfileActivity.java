package fr.esme.mystic_bikes_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import fr.esme.mystic_bikes_app.login_views.ForgotPassword;
import fr.esme.mystic_bikes_app.login_views.RegisterUser;
import fr.esme.mystic_bikes_app.map_views.MapActivity;

public class ProfileActivity extends AppCompatActivity {
    Button itin_button;
    Button param_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        itin_button = (Button) findViewById(R.id.itin_button);
        itin_button.setOnClickListener(v -> {startActivity(new Intent( this, MapActivity.class));});
        param_button = (Button) findViewById(R.id.param_button);
    }


}