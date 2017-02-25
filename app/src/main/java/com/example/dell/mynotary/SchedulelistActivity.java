package com.example.dell.mynotary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class SchedulelistActivity extends AppCompatActivity {


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
        ObjetHolder.schedulelistModels = new ArrayList<>();

        SchedulelistModel model1 = new SchedulelistModel();
        model1.setDay("Day:Monday");
        model1.setTime("Time:1:10");
        model1.setSubject("Subject:Law");
        ObjetHolder.schedulelistModels.add(model1);


      //  if(ObjetHolder.schedulelistModels != null && position != -1){
            // handled list view
            listView = (ListView) findViewById(R.id.schedule_list_view);
            MyschedulelistAdapter myschedulelistAdapter = new MyschedulelistAdapter(this,ObjetHolder.schedulelistModels);
            listView.setAdapter(myschedulelistAdapter);
       // }
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()== android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}


