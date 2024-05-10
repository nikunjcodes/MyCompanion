package com.example.mycompanion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AppActivity extends AppCompatActivity {
    EditText user, pass, number, name;
    TextView loginRedirect;
    Button signup;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);

        TextView loginRedirect = findViewById(R.id.login_redirect);
        user = findViewById(R.id.user);
        pass = findViewById(R.id.pass);
        number = findViewById(R.id.number);
        name = findViewById(R.id.name);
        signup = findViewById(R.id.signup);

        DB = new DBHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String us = user.getText().toString();
                String ps = pass.getText().toString();
                String nu = number.getText().toString();
                String nm = name.getText().toString();

                if (us.isEmpty() || ps.isEmpty() || nu.isEmpty() || nm.isEmpty()) {
                    Toast.makeText(AppActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                } else if (!isValidPhoneNumber(nu)) {
                    Toast.makeText(AppActivity.this, "Insert a correct number", Toast.LENGTH_SHORT).show();
                } else if (!isValidPassword(ps)) {
                    Toast.makeText(AppActivity.this, "Password Length should be between 6 to 20 characters", Toast.LENGTH_SHORT).show();
                } else if (!isValidName(nm)) {
                    Toast.makeText(AppActivity.this, "Name should not contain numbers", Toast.LENGTH_SHORT).show();
                } else {
                    if (!DB.checkusername(us)) {
                        if (DB.insertData(us, ps, nm, nu)) {
                            Toast.makeText(AppActivity.this, "Sign Up Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(AppActivity.this, "Sign Up Failed", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(AppActivity.this, "User already exists", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        loginRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent LOGIN = new Intent(AppActivity.this, LoginActivity.class);
                startActivity(LOGIN);
            }
        });

    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        Pattern pattern = Pattern.compile("^\\d{10}$");
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    private boolean isValidPassword(String password) {
        return password.length() >= 6 && password.length() <= 20;
    }

    private boolean isValidName(String name) {
        Pattern pattern = Pattern.compile("^[a-zA-Z ]+$");
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }
}
