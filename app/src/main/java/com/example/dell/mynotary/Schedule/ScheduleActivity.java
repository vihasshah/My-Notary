package com.example.dell.mynotary.Schedule;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.dell.mynotary.AsyncTasks.AsyncResponse;
import com.example.dell.mynotary.AsyncTasks.WebserviceCall;
import com.example.dell.mynotary.Const;
import com.example.dell.mynotary.ObjetHolder;
import com.example.dell.mynotary.R;
import com.example.dell.mynotary.Utils;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class ScheduleActivity extends AppCompatActivity {


  // int position;
    ListView listView;
    String Day[];
    String Time[];
    String Subject[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedulelist);

       getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        // get position
     //  position = getIntent().getIntExtra("position",-1);
        // init
        Day=getResources().getStringArray(R.array.day);
        Time=getResources().getStringArray(R.array.time);
        Subject=getResources().getStringArray(R.array.subject);


      //  if(ObjetHolder.scheduleModels != null && position != -1){
            // handled list view
        listView = (ListView) findViewById(R.id.schedule_list_view);

       // }
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

    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()== android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
    private void decodeJsonData(JSONArray jsonData) {
        int count = jsonData.length();
        for (int i = 0; i < count; i++) {
            ScheduleModel model = new ScheduleModel();
            try {
                model.setDate(jsonData.getJSONObject(i).getString("date"));
                model.setSubject(jsonData.getJSONObject(i).getString("subject"));
                model.setTime(jsonData.getJSONObject(i).getString("time"));
                model.setUniversity(jsonData.getJSONObject(i).getString("university"));
                ObjetHolder.scheduleModels.add(model);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }
}


