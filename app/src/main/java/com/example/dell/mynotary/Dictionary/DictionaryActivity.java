package com.example.dell.mynotary.Dictionary;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.dell.mynotary.AsyncTasks.AsyncResponse;
import com.example.dell.mynotary.AsyncTasks.WebserviceCall;
import com.example.dell.mynotary.Helpers.Const;
import com.example.dell.mynotary.Helpers.ObjetHolder;
import com.example.dell.mynotary.Helpers.Utils;
import com.example.dell.mynotary.R;

import org.json.JSONArray;

import java.util.ArrayList;

public class DictionaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }
    @Override
    protected void onResume() {
        super.onResume();
        ObjetHolder.scheduleModels = new ArrayList<>();
        String jsonRequest = Utils.createJsonRequest(new String[]{"mode"},new String[]{"Schedule"});
        new WebserviceCall(this, Const.DETAILS_URL, jsonRequest, "getting schedules....", true, new AsyncResponse() {
            @Override
            public void onSuccess(String message, JSONArray jsonData) {
                decodeJsonData(jsonData);
                ScheduleAdapter scheduleAdapter = new ScheduleAdapter(ScheduleActivity.this,ObjetHolder.scheduleModels);
                listView.setAdapter(scheduleAdapter);

            }

            @Override
            public void onFailure(String message) {

            }
        }).execute();

    }

}
