package com.example.battleofvernacular;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;


public class VocabQuestion extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    int correctindex = 0;
    int correctindex2;
    String thecategory;
    String correctenglish;
    myDbAdapter helper;
    String datareturned;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocab_question);
        Intent intent = getIntent();
        thecategory = intent.getStringExtra(DisplayMessageActivity.EXTRA_MESSAGE);
        String message = "The Category is: " + thecategory;
        TextView category = findViewById(R.id.category);
        category.setText(message);

        helper = new myDbAdapter(this);
        datareturned = helper.getData();

        generateOptions();
    }
    public void choiceOne(View view){
        int choice = 1;
        disableChoices();
        checkAnswer(choice);
    }
    public void choiceTwo(View view){
        int choice = 2;
        disableChoices();
        checkAnswer(choice);
    }
    public void choiceThree(View view){
        int choice = 3;
        disableChoices();
        checkAnswer(choice);
    }
    public void choiceFour(View view){
        int choice = 4;
        disableChoices();
        checkAnswer(choice);
    }
    public void continueGame(View view){
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        Button contgame = (Button)findViewById(R.id.continuegame);
        contgame.setVisibility(View.INVISIBLE);
        //intent.putExtra(EXTRA_MESSAGE);
        startActivity(intent);
    }
    public void disableChoices(){
        TextView option1 = findViewById(R.id.option1);
        option1.setEnabled(false);
        TextView option2 = findViewById(R.id.option2);
        option2.setEnabled(false);
        TextView option3 = findViewById(R.id.option3);
        option3.setEnabled(false);
        TextView option4 = findViewById(R.id.option4);
        option4.setEnabled(false);
    }



    public void checkAnswer(int choice) {
        if ((choice-1) == correctindex2){

            updateScore();
            Button contgame = (Button)findViewById(R.id.continuegame);
            contgame.setVisibility(View.VISIBLE); //SHOW the button

        }
        else{
            TextView textView8 = findViewById(R.id.correctOrNot);
            //String incorrect = "Whammy! That is not correct/n The correct English word is: " +correctenglish;
            String incorrect = "Womp womp that is incorrect";
            textView8.setText(incorrect);
            Button contgame = (Button)findViewById(R.id.continuegame);
            contgame.setVisibility(View.VISIBLE); //SHOW the button
        }
    }
    public void updateScore()
    {

        String [] thename = datareturned.split(":");
        int oldscore = Integer.parseInt(thename[2]);

        if(oldscore>=3)
        {
            int newscore = 0;
            helper.updateScore(oldscore, newscore);
            helper.updateMasterCat(thecategory+":");
            TextView textView8 = findViewById(R.id.correctOrNot);
            String correct = "You mastered the category!";
            textView8.setText(correct);

        }
        else {
            int newscore = oldscore+1;
            helper.updateScore(oldscore, newscore);
            TextView textView8 = findViewById(R.id.correctOrNot);
            String correct = "That is correct!";
            textView8.setText(correct);
        }


    }


    public void generateOptions(){
        try{

            //Accesses the dictionary xml file and prepares to pull the words
            InputStream src =getResources().openRawResource(R.raw.dictionary);
            DocumentBuilderFactory docb = DocumentBuilderFactory.newInstance();
            DocumentBuilder doc = docb.newDocumentBuilder();
            Document d = doc.parse(src);
            Element element = d.getDocumentElement();
            element.normalize();

            // gets how many possible choices there are to pull from to limit random number generator
            int length = d.getElementsByTagName(thecategory).getLength();

            //pulls the correct english and german words, the index number corresponds to the row number starting with 0
            Random rand = new Random();
            correctindex = rand.nextInt(length);
            String targetwords = d.getElementsByTagName(thecategory).item(correctindex).getTextContent();
            String[] bothtargets = targetwords.split(":");
            String correctenglish = bothtargets[0];
            String correctgerman = bothtargets[1];


            //sets the text box to the german word to be translated correctly
            TextView tobetranslated = findViewById(R.id.ToBeTranslated);
            String message = correctgerman;
            tobetranslated.setText(message);

            //create an arraylist of indexes to pull english words from, include the correctindex in the array list
            int index;
            List<Integer> indexes = new ArrayList<Integer>();
            indexes.add(correctindex);
            for (int i =0; i<3; ) {
                index = rand.nextInt(length);
                if (indexes.contains(index)) {

                }
                else {
                    indexes.add(index);
                    i++;
                }
            }
            //choose randomly one of the indexes, see if its the correctindex, then set the textview to the indexed english word and remove that index number

            index = rand.nextInt(indexes.size());
            if (indexes.get(index) == correctindex)
            {correctindex2 = 0;}
            TextView option1 = findViewById(R.id.option1);
            message = d.getElementsByTagName(thecategory).item(indexes.get(index)).getTextContent();
            String[] bothwords = message.split(":");
            option1.setText(bothwords[0]);
            indexes.remove(index);

            index = rand.nextInt(indexes.size());
            if (indexes.get(index) == correctindex)
            {correctindex2 = 1;}
            TextView option2 = findViewById(R.id.option2);
            message = d.getElementsByTagName(thecategory).item(indexes.get(index)).getTextContent();
            bothwords = message.split(":");
            option2.setText(bothwords[0]);
            indexes.remove(index);

            index = rand.nextInt(indexes.size());
            if (indexes.get(index) == correctindex)
            {correctindex2 = 2;}
            TextView option3 = findViewById(R.id.option3);
            message = d.getElementsByTagName(thecategory).item(indexes.get(index)).getTextContent();
            bothwords = message.split(":");
            option3.setText(bothwords[0]);
            indexes.remove(index);

            index = rand.nextInt(indexes.size());
            if (indexes.get(index) == correctindex)
            {correctindex2 = 3;}
            TextView option4 = findViewById(R.id.option4);
            message = d.getElementsByTagName(thecategory).item(indexes.get(index)).getTextContent();
            bothwords = message.split(":");
            option4.setText(bothwords[0]);
            indexes.remove(index);


        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
