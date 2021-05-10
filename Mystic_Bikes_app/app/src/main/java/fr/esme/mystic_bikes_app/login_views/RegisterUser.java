package fr.esme.mystic_bikes_app.login_views;

import androidx.annotation.NonNull;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import fr.esme.mystic_bikes_app.map_views.MapActivity;
import fr.esme.mystic_bikes_app.R;

public class RegisterUser extends AppCompatActivity implements View.OnClickListener {


    private TextView banner, registerUser;
    private EditText editTextFullName, editTextEmail, editTextPassword;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        mAuth = FirebaseAuth.getInstance();

        banner = (TextView) findViewById(R.id.banner);
        banner.setOnClickListener(this);

        registerUser = (Button) findViewById(R.id.registerUser);
        registerUser.setOnClickListener(this);

        editTextFullName = (EditText) findViewById(R.id.fullName);
        editTextEmail = (EditText) findViewById(R.id.email);
        editTextPassword = (EditText) findViewById(R.id.password);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);


        editTextPassword.setOnClickListener( l -> {
            editTextPassword.setText("");
            editTextPassword.setTransformationMethod(new PasswordTransformationMethod());
        });
        editTextEmail.setOnClickListener( l -> {
            editTextEmail.setText("");
        });

        editTextFullName.setOnClickListener( l -> {
            editTextFullName.setText("");
        });

    }

    public void onClick(View v) {
        boolean registerUser = false;
        switch (v.getId()) {
            case R.id.banner:
                startActivity(new Intent(this, MapActivity.class));
                break;
            case R.id.registerUser:
                if(registerUser()) startActivity(new Intent(this, MapActivity.class));
                break;
        }

    }

    private boolean registerUser() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String fullName = editTextFullName.getText().toString().trim();

        if (fullName.isEmpty()) {
            editTextFullName.setError("Full name is required");
            editTextFullName.requestFocus();
            return false;
        }
        if (email.isEmpty()) {
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Please provide valid email!");
            editTextEmail.requestFocus();
            return false;
        }

        if (password.isEmpty()) {
            editTextPassword.setError("Password is required!");
            editTextPassword.requestFocus();
            return false;
        }
        if (password.length() < 6) {
            editTextPassword.setError("Min password lenght should be 6 characters!");
            editTextPassword.requestFocus();
            return false;

        }


        //progressBar.setVisibility(View.VISIBLE);
        //Pas deux fois la même adresse mais on peut avoir deux fullname egaux
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            User user = new User(fullName, email);

                            FirebaseDatabase.getInstance().getReference("User")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {


                                    if (task.isSuccessful()){
                                        Toast.makeText(RegisterUser.this, "User has been registered successfully!", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.VISIBLE);

                                        //redirect to login layout!

                                    }else
                                        {
                                        Toast.makeText(RegisterUser.this, "Failed to register! Try again!", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }

                                }
                            });

                        }
                    }
                });
        return true;

        }
    }