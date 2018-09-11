package com.icyzy.niceoppai;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    public static String[] monthsArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            monthsArray = new ServiceHandler().execute().get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        ListView monthListView = findViewById(R.id.months_list);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, monthsArray);
        monthListView.setAdapter(arrayAdapter);
    }
}
