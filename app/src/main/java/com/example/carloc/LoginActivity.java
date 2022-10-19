package com.example.carloc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    Button btn_register,btn_accueil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        btn_register = findViewById(R.id.registerLink);
        btn_accueil = findViewById(R.id.Accueil);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRegisterPage();
            }
        });
        btn_accueil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAccueilPage();
            }
        });
        getWindow().setStatusBarColor(ContextCompat.getColor(LoginActivity.this , R.color.black));
    }
    public void openRegisterPage (){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void openAccueilPage (){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}