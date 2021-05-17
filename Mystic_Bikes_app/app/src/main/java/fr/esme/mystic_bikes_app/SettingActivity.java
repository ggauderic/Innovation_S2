package fr.esme.mystic_bikes_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Switch;

import fr.esme.mystic_bikes_app.map_views.MapActivity;

public class SettingActivity extends AppCompatActivity {
    Switch darkMode_switch;
    Switch lightMode_switch;
    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        backButton = (Button) findViewById(R.id.back_button);
        backButton.setOnClickListener(v -> {startActivity(new Intent(this, MapActivity.class));});
        darkMode_switch = (Switch) findViewById(R.id.dark_switch);
        darkMode_switch.setOnCheckedChangeListener((t,s) -> {switchMode(darkMode_switch, lightMode_switch); });
        lightMode_switch = (Switch) findViewById(R.id.light_switch);
        lightMode_switch.setOnCheckedChangeListener((t,s) -> {switchMode(lightMode_switch, darkMode_switch); });
    }


    private void switchMode(Switch firstSwitch, Switch secondSwitch){
        if(firstSwitch.isChecked()){

            secondSwitch.setChecked(false);
        }else {
            secondSwitch.setChecked(true);
        }

    }
    private void changeTheme(String mode){
        int th;
        if(mode == "dark"){
            th = R.style.Theme_Mystic_Bikes_app_dark;
        } else {
            th = R.style.Theme_Mystic_Bikes_app;
        }
        Tools.setTheme(th);
        Tools.setMode(mode);
    }


}