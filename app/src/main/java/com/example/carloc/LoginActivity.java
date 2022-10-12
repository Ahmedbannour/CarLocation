package com.example.carloc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        btn = findViewById(R.id.registerLink);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRegisterPage();
            }
        });
        getWindow().setStatusBarColor(ContextCompat.getColor(LoginActivity.this , R.color.black));
    }
    public void openRegisterPage (){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}