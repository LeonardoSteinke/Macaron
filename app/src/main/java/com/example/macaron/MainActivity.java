package com.example.macaron;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {

    Button btnSignUp;
    Button btnSignIn;
    EditText edtUsername;
    EditText edtPassword;
    ImageView imgLogo;

    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        initComponents();

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(i);
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(MainActivity.this, DashboardActivity.class);
                startActivity(i);
            }
        });

    }


    private void initComponents() {
        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignIn = findViewById(R.id.btnSignIn);
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        imgLogo = findViewById(R.id.imgLogo);
        imgLogo.setImageResource(R.mipmap.logo_foreground);

    }
}