package com.example.ydi1_feelsbook;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

// below class HistoryList is used to view pass emotions and optional comment user inputted at specific time.
public class HistoryList extends AppCompatActivity {
    private static final String FILENAME = "file.sav";
    private static final Integer MAX_CHARS = 100;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> tweetList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_list);
    }

    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        historyList();
    }

    // we load the list of information from the file.sav to tweetList.
    // set an adapter match the strings in tweetList and adjust positions.
    public void historyList() {
        ListView listView = (ListView) findViewById(R.id.listview);
        tweetList = loadFromFile();
        SortDate sortDate = new SortDate();
        Collections.sort(tweetList, sortDate);
        adapter = new ArrayAdapter<String>(HistoryList.this, R.layout.items, tweetList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Editbox(tweetList.get(position), position);
            }
        });
    }

    // Use dialog interface for editting and deleting feelings
    // check the match characters between strings
    public void Editbox(String oldItem, final int index) {
        final Dialog dialog = new Dialog(HistoryList.this);
        dialog.setTitle("editbox");
        dialog.setContentView(R.layout.edit);
        final EditText editText = (EditText) dialog.findViewById(R.id.edittext);
        editText.setText(oldItem);
        Button editButton = (Button) dialog.findViewById(R.id.editbutton);
        Button deleteButton = (Button) dialog.findViewById(R.id.deletebutton);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((editText.getText().toString().length() < MAX_CHARS )&&(editText.getText().toString().matches("^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2} \\| (love|sadness|joy|fear|anger|surprise) \\| .*?$"))) {
                    tweetList.set(index, editText.getText().toString());
                    saveInFile(tweetList);
                    adapter.notifyDataSetChanged();
                    dialog.dismiss();
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Unidentifiable optional comment!\nPlease input your optional comment as following format:\n2018-10-01T9:17:32 | joy | comment", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
                historyList();
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tweetList.remove(index);
                saveInFile(tweetList);
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
        dialog.show();
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

    // Below function is used to save information into a file
    // these code are from https://github.com/joshua2ua/lonelyTwitter by Joshua Charles Campbell
    private void saveInFile(ArrayList<String> list) {
        deleteFile(FILENAME);
        try {
            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_APPEND);
            for (String text : list) {
                fos.write(new String(text + "\r\n").getBytes());
            }
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}