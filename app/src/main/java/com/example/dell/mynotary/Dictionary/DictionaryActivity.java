package com.example.dell.mynotary.Dictionary;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.example.dell.mynotary.AsyncTasks.AsyncResponse;
import com.example.dell.mynotary.AsyncTasks.WebserviceCall;
import com.example.dell.mynotary.Helpers.Const;
import com.example.dell.mynotary.Helpers.ObjetHolder;
import com.example.dell.mynotary.Helpers.Utils;
import com.example.dell.mynotary.R;
import com.example.dell.mynotary.Schedule.ScheduleActivity;
import com.example.dell.mynotary.Schedule.ScheduleAdapter;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class DictionaryActivity extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = (ListView) findViewById(R.id.dictionary_listview);



    }
    @Override
    protected void onResume() {
        super.onResume();
        ObjetHolder.dictionaryList = new ArrayList<>();
        String jsonRequest = Utils.createJsonRequest(new String[]{"mode"},new String[]{"Dictionary"});
        new WebserviceCall(this, Const.DETAILS_URL, jsonRequest, "getting schedules....", true, new AsyncResponse() {
            @Override
            public void onSuccess(String message, JSONArray jsonData) {
                decodeJsonData(jsonData);
                DictionaryAdapter dictionaryAdapter = new DictionaryAdapter(DictionaryActivity.this,ObjetHolder.dictionaryList);
                listView.setAdapter(dictionaryAdapter);

            }

            @Override
            public void onFailure(String message) {

            }
        }).execute();

    }

    private void decodeJsonData(JSONArray jsonData) {
        int count = jsonData.length();
        for (int i = 0; i < count; i++) {
            DictionaryModel model = new DictionaryModel();
            try {
                model.setWord(jsonData.getJSONObject(i).getString("word"));
                model.setDescription(jsonData.getJSONObject(i).getString("description"));
                ObjetHolder.dictionaryList.add(model);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }
}
