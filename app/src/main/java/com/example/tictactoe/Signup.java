package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Signup extends AppCompatActivity {
    private EditText newEmail, password, repassword;
    Button signup;
    private DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        newEmail = findViewById(R.id.signup_email);
        password = findViewById(R.id.signup_password);
        repassword = findViewById(R.id.signup_password2);
        signup = findViewById(R.id.signup_signupbtn);
        db = new DatabaseHandler(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = newEmail.getText().toString().trim();
                String Password = password.getText().toString().trim();
                String Repassword = repassword.getText().toString().trim();

                if (TextUtils.isEmpty(Email)) {
                    Toast.makeText(Signup.this, R.string.please_enter_email, Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(Password) || TextUtils.isEmpty(Repassword)) {
                    Toast.makeText(Signup.this, R.string.password_field_is_empty, Toast.LENGTH_SHORT).show();
                } else if (!Password.equals(Repassword)) {
                    Toast.makeText(Signup.this, R.string.passwords_do_not_match, Toast.LENGTH_SHORT).show();
                } else {
                    if (db.insertUser(new User(Email, Password))) {
                        Toast.makeText(Signup.this, R.string.account_created_successfully, Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Signup.this, Login.class);
                        startActivity(intent);
                        finish(); // Optional: To close the Signup activity
                    } else {
                        Toast.makeText(Signup.this, R.string.this_email_already_has_an_account, Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}
