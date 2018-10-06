package com.example.ydi1_feelsbook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {
    private static final String FILENAME = "file.sav";
    private static final Integer MAX_CHARS = 100;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> tweetList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    protected void onStart() {
        //TODO Auto-generated method stub
        super.onStart();
        update();
    }

    // Below function is used to save information into a file
    // these code are from https://github.com/joshua2ua/lonelyTwitter by Joshua Charles Campbell
    private void saveInFile(String text, String date) {
        try {
            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_APPEND);
            fos.write(new String(date.toString() + " | " + text + "\r\n").getBytes());
            fos.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Below function is used to load information from a file
    // these code are from https://github.com/joshua2ua/lonelyTwitter by Joshua Charles Campbell
    private ArrayList<String> loadFromFile() {
        tweetList = new ArrayList<String>();
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            String line = null;
            while ((line = in.readLine()) != null) {
                tweetList.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tweetList;
    }

    // Get the date and time from local when the user click the emotion buttons
    // following code are from Stackoverflow: https://stackoverflow.com/questions/2201925/converting-iso-8601-compliant-string-to-java-util-date Apr 8, 2014 at 14:20 by d.danailov
    public String getDate() {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        dateFormat.setTimeZone(tz);
        return dateFormat.format(new Date());
    }

    // this function is used to set a intent to activate HistoryList class
    public void historyList(View view) {
        Intent intent = new Intent(this, HistoryList.class);
        intent.putExtra("test", "");
        startActivity(intent);
    }

    // below function update used to update the emotion counter which is the text view that displays the numbers of every emotion been click
    public void update() {
        ArrayList<String> emotions = new ArrayList<String>();
        emotions.add("love");
        emotions.add("joy");
        emotions.add("surprise");
        emotions.add("anger");
        emotions.add("sadness");
        emotions.add("fear");
        EmotionCounter counter = new EmotionCounter();
        tweetList = loadFromFile();
        for (String emotion : emotions) {
            counter.getCount(emotion, tweetList);
        }
        TextView counterView = (TextView) findViewById(R.id.counterview);
        counterView.setText("love: " + counter.getCountLove() + "\n" + "joy: " + counter.getCountJoy() + "\n" + "surprise: " + counter.getCountSurprise() + "\n" + "anger: " + counter.getCountAnger() + "\n" + "sadness: " + counter.getCountSadness() + "\n" + "fear: " + counter.getCountFear());
    }

    // Below 6 functions are the emotion function, which add the optional comment the user input with the local date and time under the specific emotion user selected
    // and save the string into file.sav
    public void addLove(View view) throws OptionalCommentTooLongException {
        EditText optionalComment = (EditText) findViewById(R.id.oComment);
        String emotion = "love";
        String oComment = optionalComment.getText().toString();
        if (oComment.length() >= this.MAX_CHARS) {
            throw new OptionalCommentTooLongException();
        }
        else {
            String content = emotion + " | " + oComment;
            setResult(RESULT_OK);
            String date = getDate();
            saveInFile(content, date);
            update();
            optionalComment.setText("");
        }
    }

    public void addJoy(View view) throws OptionalCommentTooLongException {
        EditText optionalComment = (EditText) findViewById(R.id.oComment);
        String emotion = "joy";
        String oComment = optionalComment.getText().toString();
        if (oComment.length() >= this.MAX_CHARS) {
            throw new OptionalCommentTooLongException();
        }
        else {
            String content = emotion + " | " + oComment;
            setResult(RESULT_OK);
            String date = getDate();
            saveInFile(content, date);
            update();
            optionalComment.setText("");
        }
    }

    public void addSuprise(View view) throws OptionalCommentTooLongException {
        EditText optionalComment = (EditText) findViewById(R.id.oComment);
        String emotion = "suprise";
        String oComment = optionalComment.getText().toString();
        if (oComment.length() >= this.MAX_CHARS) {
            throw new OptionalCommentTooLongException();
        }
        else {
            String content = emotion + " | " + oComment;
            setResult(RESULT_OK);
            String date = getDate();
            saveInFile(content, date);
            update();
            optionalComment.setText("");
        }
    }

    public void addAnger(View view) throws OptionalCommentTooLongException {
        EditText optionalComment = (EditText) findViewById(R.id.oComment);
        String emotion = "anger";
        String oComment = optionalComment.getText().toString();
        if (oComment.length() >= this.MAX_CHARS) {
            throw new OptionalCommentTooLongException();
        }
        else {
            String content = emotion + " | " + oComment;
            setResult(RESULT_OK);
            String date = getDate();
            saveInFile(content, date);
            update();
            optionalComment.setText("");
        }
    }

    public void addsadness(View view) throws OptionalCommentTooLongException {
        EditText optionalComment = (EditText) findViewById(R.id.oComment);
        String emotion = "sadness";
        String oComment = optionalComment.getText().toString();
        if (oComment.length() >= this.MAX_CHARS) {
            throw new OptionalCommentTooLongException();
        }
        else {
            String content = emotion + " | " + oComment;
            setResult(RESULT_OK);
            String date = getDate();
            saveInFile(content, date);
            update();
            optionalComment.setText("");
        }
    }

    public void addFear(View view) throws OptionalCommentTooLongException {
        EditText optionalComment = (EditText) findViewById(R.id.oComment);
        String emotion = "fear";
        String oComment = optionalComment.getText().toString();
        if (oComment.length() >= this.MAX_CHARS) {
            throw new OptionalCommentTooLongException();
        }
        else {
            String content = emotion + " | " + oComment;
            setResult(RESULT_OK);
            String date = getDate();
            saveInFile(content, date);
            update();
            optionalComment.setText("");
        }
    }
}
