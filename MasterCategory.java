package com.example.battleofvernacular;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MasterCategory extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    String [] thecategories;
    myDbAdapter helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_category);
        DisplayMessageActivity categories = new DisplayMessageActivity();
        thecategories = categories.getTheCategories();
        setCategories();
    }
    public void setCategories(){

        helper = new myDbAdapter(this);
        String mastered = helper.getMastered();
        String [] masteredcats = mastered.split(":");
        //int x = masteredcats.length;
        TextView cat1 = findViewById(R.id.cat1);
        cat1.setText(thecategories[0]);

        TextView cat2 = findViewById(R.id.cat2);
        cat2.setText(thecategories[1]);

        TextView cat3 = findViewById(R.id.cat3);
        cat3.setText(thecategories[2]);

        TextView cat4 = findViewById(R.id.cat4);
        cat4.setText(thecategories[3]);

        TextView cat5 = findViewById(R.id.cat5);
        cat5.setText(thecategories[4]);

        TextView cat6 = findViewById(R.id.cat6);
        cat6.setText(thecategories[5]);

            for (int x = 0; x < masteredcats.length; x++) {
                if (masteredcats[x] == null)
                {}
                else if (thecategories[0].equals(masteredcats[x])) {
                    cat1.setEnabled(false);
                }
                else if (thecategories[1].equals(masteredcats[x])) {
                    cat2.setEnabled(false);
                }
                else if (thecategories[2].equals(masteredcats[x])) {
                    cat3.setEnabled(false);
                }
                else if (thecategories[3].equals(masteredcats[x])) {
                    cat4.setEnabled(false);
                }
                else if (thecategories[4].equals(masteredcats[x])) {
                    cat5.setEnabled(false);
                }
                else if (thecategories[5].equals(masteredcats[x])) {
                    cat6.setEnabled(false);
                }
            }

    }
    public void clickedcat1 (View view){
        Intent intent = new Intent(this, VocabQuestion.class);
        intent.putExtra(EXTRA_MESSAGE, thecategories[0]);
        startActivity(intent);
    }
    public void clickedcat2 (View view){
        Intent intent = new Intent(this, VocabQuestion.class);
        intent.putExtra(EXTRA_MESSAGE, thecategories[1]);
        startActivity(intent);
    }
    public void clickedcat3 (View view){
        Intent intent = new Intent(this, VocabQuestion.class);
        intent.putExtra(EXTRA_MESSAGE, thecategories[2]);
        startActivity(intent);
    }
    public void clickedcat4 (View view){
        Intent intent = new Intent(this, VocabQuestion.class);
        intent.putExtra(EXTRA_MESSAGE, thecategories[3]);
        startActivity(intent);
    }
    public void clickedcat5 (View view){
        Intent intent = new Intent(this, VocabQuestion.class);
        intent.putExtra(EXTRA_MESSAGE, thecategories[4]);
        startActivity(intent);
    }
    public void clickedcat6 (View view){
        Intent intent = new Intent(this, VocabQuestion.class);
        intent.putExtra(EXTRA_MESSAGE, thecategories[5]);
        startActivity(intent);
    }
    public void disabletheCat(int x)
    {
        if (x==0)
        {
            TextView cat1 = findViewById(R.id.cat1);
            cat1.setEnabled(false);
        }
        else if (x ==1)
        {
            TextView cat2 = findViewById(R.id.cat2);
            cat2.setEnabled(false);
        }
        else if (x ==2)
        {
            TextView cat3 = findViewById(R.id.cat3);
            cat3.setEnabled(false);
        }
        else if (x ==3)
        {
            TextView cat4 = findViewById(R.id.cat4);
            cat4.setEnabled(false);
        }
        else if (x ==4)
        {
            TextView cat5 = findViewById(R.id.cat5);
            cat5.setEnabled(false);
        }
        else
        {
            TextView cat6 = findViewById(R.id.cat6);
            cat6.setEnabled(false);
        }

    }
}
