package com.example.mycompanion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText username,password;
    Button signin,back;
    DBHelper DB;
    //commetn
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        DB = new DBHelper(this);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        signin = findViewById(R.id.signin);
        back = findViewById(R.id.back);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if(user.equals("")||pass.equals(""))
                {
                    Toast.makeText(LoginActivity.this,"Please enter all the fields",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Boolean checkuserpass = DB.checkusernamepassword(user,pass);
                    if(checkuserpass==true) {
                        Toast.makeText(LoginActivity.this, "Sign In Successfull", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(LoginActivity.this,"Inavlid Credentials",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AppActivity.class);
                startActivity(intent);
            }
        });
    }
}