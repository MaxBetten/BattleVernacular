package com.example.battleofvernacular;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class FinishedGame extends AppCompatActivity {
    myDbAdapter helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finished_game);
        TextView textView = findViewById(R.id.textView3);
        textView.setText("Congrats! You finished the game and mastered the categories!");
    }
    public void playAgain(View view)
    {
        helper = new myDbAdapter(this);
        helper.clearData();
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        startActivity(intent);
    }
}
