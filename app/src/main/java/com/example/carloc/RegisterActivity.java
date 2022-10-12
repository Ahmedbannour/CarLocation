package com.example.carloc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegisterActivity extends AppCompatActivity {

    Button btn_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_page);
        getWindow().setStatusBarColor(ContextCompat.getColor(RegisterActivity.this , R.color.black));
        btn_login = findViewById(R.id.loginPageUrl);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLoginPage();
            }
        });
    }
    public void openLoginPage (){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}