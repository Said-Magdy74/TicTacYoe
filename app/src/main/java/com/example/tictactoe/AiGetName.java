package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class AiGetName extends AppCompatActivity {

    private String playerName;
    private EditText playerNameTxt;
    private Button playerButton;
    private ImageView backBtn;
    private RadioButton X,O;
    private RadioGroup radioGroup;
    private  String choice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ai_get_name);

        playerNameTxt = findViewById(R.id.et_player_name);
        playerButton = findViewById(R.id.ai_player_name_btn);
        backBtn = findViewById(R.id.ai_player_names_back_btn);
        radioGroup = findViewById(R.id.radioGroup);


        choice = "X";
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.radioX) {
                choice = "X";
            } else if (checkedId == R.id.radioO) {
                choice = "O";
            }
        });


        playerButton.setOnClickListener(v -> {
            playerName = playerNameTxt.getText().toString();
            if (playerName.isEmpty()) {
                Toast.makeText(getBaseContext(), "Enter Name", Toast.LENGTH_LONG).show();
            } else {
                Intent intent = new Intent(AiGetName.this, PlayWithAi.class);
                intent.putExtra("p1", playerName);
                intent.putExtra("playerChoice", choice);
                startActivity(intent);
            }
        });

        backBtn.setOnClickListener(v -> {
            Intent i = new Intent(AiGetName.this, HomePage.class);
            startActivity(i);
        });
    }
}