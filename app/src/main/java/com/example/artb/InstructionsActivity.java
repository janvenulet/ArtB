package com.example.artb;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.ContactsContract;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class InstructionsActivity extends AppCompatActivity {


    TextView instructionText;
    TextView blankText;
    ImageView imageView;

    Button buttonBack;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        buttonBack = findViewById(R.id.backToMenu_btn);
        blankText = findViewById(R.id.score);
        buttonBack = findViewById(R.id.backToMenu_btn);
        instructionText= findViewById(R.id.gameTitle);


        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(InstructionsActivity.this, MainActivity.class);
                startActivity(mainIntent);
            }
        });

        blankText.setText("");
        instructionText.setTextSize(20);
        instructionText.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
        instructionText.setGravity(Gravity.LEFT);
        instructionText.setPadding(20,0,0,0);
        instructionText.setText("Purpose of the game ArtB: Artist of Business is to make as much money on stock market in a given period of time. Earning money is based upon old polish rule \"Buy cheap, sell expensive\". Just remember to sell your stock before they loose their value or you will run out of quarters. Have fun! ");
    }

}
