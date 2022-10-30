package com.example.carloc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.carloc.Models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity {


    Button btn_login;
    TextInputEditText birthday;
    String[] cities = {"Ariana", "Béja","Ben Arous","Bizerte", "Gabès", "Gafsa", "Jendouba", "Kairouan", "Kasserine", "Kébili", "Kef", "Mahdia", "Manouba", "Médenine", "Monastir","Nabeul", "Sfax", "Sidi Bouzid", "Siliana", "Sousse", "Tataouine", "Tozeur", "Tunis", "Zaghouan"};
    AutoCompleteTextView autoComepleteCities;
    TextInputEditText username , email , password , cpassword;
    ArrayAdapter<String> adapterItems;
    Button register;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_page);
        mAuth = FirebaseAuth.getInstance();
        btn_login = findViewById(R.id.loginPageUrl);
        birthday = findViewById(R.id.Birthday);
        autoComepleteCities = findViewById(R.id.country_text);
        register = findViewById(R.id.registerButton);
        username =findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        cpassword = findViewById(R.id.confirmPassword);

        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        adapterItems = new ArrayAdapter<String>(this , R.layout.dropdown_item_layout , cities);
        autoComepleteCities.setAdapter(adapterItems);

        register.setOnClickListener(new View.OnClickListener() {
            boolean result;
            @Override
            public void onClick(View view) {
                String name = username.getText().toString().trim();
                String mail = email.getText().toString().trim();
                String pwd = password.getText().toString().trim();
                String cpwd = cpassword.getText().toString().trim();
                String birth = birthday.getText().toString().trim();
                if(name.isEmpty()){
                    username.setError("username is required !");
                    username.requestFocus();
                    result = false;
                    return ;
                }
                if(mail.isEmpty()){
                    email.setError("email is required !");
                    email.requestFocus();
                    result = false;
                    return ;
                }else if(!Patterns.EMAIL_ADDRESS.matcher(mail).matches()){
                    email.setError("email is invalid !");
                    email.requestFocus();
                    result = false;
                    return ;
                }

                if(pwd.isEmpty()){
                    password.setError("password is required !");
                    password.requestFocus();
                    result = false;
                    return ;
                }
                else if(checkString(pwd) == false){
                    password.setError("password most contains 1+ capital 1+ number !");
                    password.requestFocus();
                    result = false;
                    return ;
                }else if (pwd.length()<8){
                    password.setError("password most contains +8 caractere !");
                    password.requestFocus();
                    result = false;
                    return ;
                }
                if (cpwd.isEmpty()){
                    cpassword.setError("confirm password is required !");
                    cpassword.requestFocus();
                    result = false;
                    return ;
                }else if(!pwd.equals(cpwd)){
                    cpassword.setError("incorrect confirmation password !");
                    cpassword.requestFocus();
                    result = false;
                    return ;
                }
                if(birth.isEmpty()){
                    birthday.setError("password is required !");
                    birthday.requestFocus();
                    result = false;
                    return ;
                }

                mAuth.createUserWithEmailAndPassword(mail,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User user = new User(1 ,name , mail , pwd , autoComepleteCities.getText().toString() , birth );
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                Toast.makeText(RegisterActivity.this , "user has been registered successfuly !" , Toast.LENGTH_LONG).show();

                                            }else{
                                                Toast.makeText(RegisterActivity.this , "Failed to register ! try again !" , Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });
                        }else{
                            Toast.makeText(RegisterActivity.this , "Failed to register ! try again !" , Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        autoComepleteCities.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(getApplicationContext(),"City : "+item , Toast.LENGTH_LONG).show();
            }
        });

        birthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("fdssdf", "onFocusChange: ");
                DatePickerDialog dialog = new DatePickerDialog(RegisterActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month=month+1;
                        String date = day+"/"+month+"/"+year;
                        birthday.setText(date);
                    }
                },year , month , day);
                dialog.show();
            }
        });

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

    private static boolean checkString(String str) {
        char ch;
        boolean capitalFlag = false;
        boolean numberFlag = false;
        for(int i=0;i < str.length();i++) {
            ch = str.charAt(i);
            if( Character.isDigit(ch)) {
                numberFlag = true;
            }
            else if (Character.isUpperCase(ch)) {
                capitalFlag = true;
            }
            if(numberFlag && capitalFlag)
                return true;
        }
        return false;
    }

}