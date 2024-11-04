package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    Spinner selectLanguage;
    public static final String[] languages = {"select language", "English", "العربية", "French", "Italian", "Turkish", "Spainish"};

    EditText email, password;
    Button login, signup;
    int ch = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.login_email);
        password = findViewById(R.id.login_password);
        login = findViewById(R.id.login_loginbtn);
        signup = findViewById(R.id.login_createemail);
        DatabaseHandler db = new DatabaseHandler(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = email.getText().toString();
                String Password = password.getText().toString();

                if (Email.isEmpty()) {
                    Toast.makeText(Login.this, R.string.please_enter_your_email, Toast.LENGTH_SHORT).show();
                } else if (Password.isEmpty()) {
                    Toast.makeText(Login.this, R.string.please_enter_your_password, Toast.LENGTH_SHORT).show();
                } else if (db.checkUser(Email, Password)) {
                    Intent intent = new Intent(Login.this, HomePage.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(Login.this, R.string.your_data_are_not_found, Toast.LENGTH_SHORT).show();
                }
            }
        });

        selectLanguage = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, languages);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectLanguage.setAdapter(adapter);
        selectLanguage.setSelection(0);

        selectLanguage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedlang = adapterView.getItemAtPosition(i).toString();
                String code = "en";

                if (!selectedlang.equals("select language")) {
                    switch (selectedlang) {
                        case "English":
                            code = "en";
                            break;
                        case "العربية":
                            code = "ar";
                            break;
                        case "French":
                            code = "fr";
                            break;
                        case "Italian":
                            code = "it";
                            break;
                        case "Turkish":
                            code = "tr";
                            break;
                        case "Spainish":
                            code = "es";
                            break;
                    }

                    LocaleHelper.setLocale(Login.this, code);
                    recreate();
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Signup.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
