package com.example.dell.mynotary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class SchedulelistActivity extends AppCompatActivity {
    int position;
    String lectureschedulelist;
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedulelist);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        // get position
        position = getIntent().getIntExtra("position",-1);
        // init



        if(ObjetHolder.schedulelistModels != null && position != -1){
            // set values
             lectureschedulelist = ObjetHolder.schedulelistModels.get(position).getLectureschedulelist();
            ObjetHolder.schedulelistModels = new ArrayList<>();
            SchedulelistModel model1=new SchedulelistModel();
            model1.setLectureschedulelist("lectureschedulelist");

            ObjetHolder.schedulelistModels.add(model1);
            // handled list view
            listview = (ListView) findViewById(R.id.home_list_view);
            MyschedulelistAdapter myschedulelistAdapter = new MyschedulelistAdapter(this,ObjetHolder.schedulelistModels);
            listview.setAdapter(myschedulelistAdapter);








        }
    }
}


