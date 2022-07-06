package com.example.battleofvernacular;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    myDbAdapter helper;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper = new myDbAdapter(this);
        checkifUserExists();
        //displayPreviousUser();


    }
    public void checkifUserExists(){
        String datareturned = helper.getData();
        if (datareturned.equals("")){}
        else{startGame();}
    }

    public void startGame(View view){
        //creates the first text box, gets the string from it
        EditText editText = (EditText) findViewById(R.id.editText);
        username = editText.getText().toString();
        int score = 0;
        String cats = "";
        helper.insertData(username, score, cats);
        startGame();
    }

    public void startGame(){

        Intent intent = new Intent(this, DisplayMessageActivity.class);
        startActivity(intent);
    }

}
