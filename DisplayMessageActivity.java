package com.example.battleofvernacular;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.*;

public class DisplayMessageActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    int categorynumber;
    String categorychosen;
    String username;
    myDbAdapter helper;
    String [] theCategories = {"Food", "Drink", "Animals", "Objects", "Places", "Family"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        // Get the Intent that started this activity and extract the string
        //Intent intent = getIntent();
        //username = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        helper = new myDbAdapter(this);

        //Setting username and score
        String datareturned = helper.getData();
        String [] nameandscore = datareturned.split(":");
        String mastered = helper.getMastered();
        String [] masteredcats = mastered.split(":");
        String mascats = "";
        for (int x = 0; x<masteredcats.length;x++)
        {mascats = mascats + masteredcats[x] +" ";}
        TextView textView = findViewById(R.id.category);
        textView.setText(nameandscore[1]+" Correct:"+nameandscore[2]+"/3"+" Mastered Categories:" + mascats);
        checkScore(nameandscore[2]);
    }
    public String [] getTheCategories(){
        return theCategories;
    }
    public void checkScore(String theScore){
        int score = Integer.parseInt(theScore);
        if(score>=3)
        {
            Intent intent = new Intent(this, MasterCategory.class);
            startActivity(intent);
        }
        String mastered = helper.getMastered();
        String [] masteredcats = mastered.split(":");
        if (masteredcats.length >=6)
        {
            Intent intent = new Intent(this, FinishedGame.class);
            startActivity(intent);
        }
    }
    public void randNumberButton(View view){
        Button thewheel = findViewById(R.id.button);
        thewheel.setEnabled(false);
        randNumber();
    }
    public void randNumber(){
        //Generate random number to choose category
        Random rand = new Random();
        int rand_int1 = rand.nextInt(6000);
        categorySelect(rand_int1);
    }
    public void categorySelect(int number){
        //Food 0-1000, Drink 1001-2000, Animals 2001-3000, Objects 3001 -4000, Places 4001 -5000, Family 5000+
        if (number<=1000){
            categorychosen = theCategories[0];
        }
        else if (number<=2000){
            categorychosen = theCategories[1];
        }
        else if (number<=3000){
            categorychosen = theCategories[2];
        }
        else if (number<=4000){
            categorychosen = theCategories[3];
        }
        else if (number<=5000){
            categorychosen = theCategories[4];
        }
        else{
            categorychosen = theCategories[5];
        }
        TextView textView2 = findViewById(R.id.textView2);
        String message = "The Category is: " + categorychosen;
        textView2.setText(message);
    }
    public void gotoQuestion(View view){
        Intent intent = new Intent(this, VocabQuestion.class);
        intent.putExtra(EXTRA_MESSAGE, categorychosen);
        startActivity(intent);
    }
}
