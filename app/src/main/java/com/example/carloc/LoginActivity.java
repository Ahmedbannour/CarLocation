package com.example.carloc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    Button btn_register,btn_accueil,btn_login;
    TextInputEditText   email , password ;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.login_page);
        btn_register = findViewById(R.id.registerLink);
        btn_login = findViewById(R.id.btn_login);
        btn_accueil = findViewById(R.id.Accueil);
        email = findViewById(R.id.email_login);
        password = findViewById(R.id.password_login);

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
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String addr = email.getText().toString().trim();
                String pwd = password.getText().toString().trim();

                if(addr.isEmpty()){
                    email.setError("email is required !");
                    email.requestFocus();
                    return ;
                }else if(!Patterns.EMAIL_ADDRESS.matcher(addr).matches()){
                    email.setError("email is invalid !");
                    email.requestFocus();
                    return ;
                }

                if(pwd.isEmpty()){
                    password.setError("password is required !");
                    password.requestFocus();
                    return ;
                }

                mAuth.signInWithEmailAndPassword(addr , pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(LoginActivity.this , "login with success!" , Toast.LENGTH_LONG).show();
                            startActivity(new Intent(LoginActivity.this , HomeActivity.class));
                            finish();
                        }else {
                            Toast.makeText(LoginActivity.this , "Failed to login ! try again !" , Toast.LENGTH_LONG).show();
                        }
                    }
                });
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