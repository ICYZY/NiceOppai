package com.icyzy.niceoppai;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    public static String[] monthsArray;
    private ListView monthListView;
    private ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            monthsArray = new ServiceHandler().execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        monthListView = findViewById(R.id.months_list);
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, monthsArray);
        monthListView.setAdapter(arrayAdapter);
    }
}
