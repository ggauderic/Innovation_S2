package fr.esme.mystic_bikes_app.login_views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mapbox.mapboxsdk.maps.Style;

import java.util.ArrayList;

import fr.esme.mystic_bikes_app.R;
import fr.esme.mystic_bikes_app.Tools;
import fr.esme.mystic_bikes_app.map_views.MapActivity;

import static fr.esme.mystic_bikes_app.Tools.waitPbar;

public class MainActivity extends AppCompatActivity {

    private TextView register, forgotPassword;
    private EditText editTextEmail, editTextPassword;
    private Button signIn;

    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    private boolean emailVerificated;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTheme(Tools.getTheme());
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        register = (TextView) findViewById(R.id.register);
        register.setOnClickListener(v -> {startActivity(new Intent(this, RegisterUser.class));});
        emailVerificated = true;

        signIn = (Button) findViewById(R.id.signIn);
        signIn.setOnClickListener(v -> {startActivity(new Intent(this, MapActivity.class));});

       editTextEmail = (EditText) findViewById(R.id.email);
        editTextPassword = (EditText) findViewById(R.id.password);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        mAuth = FirebaseAuth.getInstance();


        editTextPassword.setOnClickListener( l -> {
            editTextPassword.setText("");
            editTextPassword.setTransformationMethod(new PasswordTransformationMethod());
        });


        forgotPassword = (TextView) findViewById(R.id.forgotPassword);
        forgotPassword.setOnClickListener(v -> {startActivity(new Intent(this, ForgotPassword.class));});
       // setting();


    }

    private void setting() {

        ArrayList<View> v1 = new ArrayList<>();
        ArrayList<View> v2 = new ArrayList<>();
        ArrayList<View> v3 = new ArrayList<>();



        v1.add(findViewById(R.id.parent));
        v2.add(findViewById(R.id.signIn));
        Tools.setBackgroundColor(v1, v2, v3);
        ArrayList<TextView> v1t = new ArrayList<>();
        ArrayList<TextView> v2t = new ArrayList<>();
        ArrayList<TextView> v3t = new ArrayList<>();
        v2t.add(findViewById(R.id.register));
        v2t.add(findViewById(R.id.forgotPassword));
        v3t.add(findViewById(R.id.password));
        v3t.add(findViewById(R.id.email));
        Tools.setTextColor(v1t, v2t, v3t);

    }

    private boolean userLogin()  {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (email.isEmpty()) {
            editTextEmail.setError("Email is required");
            editTextPassword.requestFocus();
            return false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Please enter a valid email");
            editTextEmail.requestFocus();
            return false;
        }
        if (password.isEmpty()) {
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return false;
        }
        if (password.length() < 6) {
            editTextPassword.setError("Min password lenght is 6 characters");
            editTextPassword.requestFocus();
            return false;
        }

        progressBar.setVisibility(View.VISIBLE);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        waitPbar(2000, progressBar);

        if (!user.isEmailVerified()) {
            emailVerificated = false;
            user.sendEmailVerification();
            Toast.makeText(MainActivity.this, "Check your email to verify your account", Toast.LENGTH_LONG).show();

            return false;
        } else {
            emailVerificated = true;
        }
         return true;
    }
}