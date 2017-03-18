package com.example.dell.mynotary.Schedule;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.dell.mynotary.Helpers.Const;
import com.example.dell.mynotary.Helpers.ObjetHolder;
import com.example.dell.mynotary.R;

public class ScheduleDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //show back button
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
        // init
        TextView titleTV = (TextView) findViewById(R.id.schedule_details_title);
        TextView venueTV = (TextView) findViewById(R.id.schedule_details_tv_venue);
        TextView dateTV = (TextView) findViewById(R.id.schedule_details_tv_date);
        TextView timeTV = (TextView) findViewById(R.id.schedule_details_tv_time);


        // get position from then intent
        ScheduleModel.DataBean model = (ScheduleModel.DataBean) getIntent().getSerializableExtra(Const.INTENT_POSITION_CLASS);
        // check for default value
//        if(position != -1){
            // setting values to textviews
            // values are taken from then static arraylist which is declaired public in ObjectHolder class
            titleTV.setText(model.getSubject());
            venueTV.setText(model.getUniversity());
            dateTV.setText(model.getDate());
            timeTV.setText(model.getTime());
//        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //handle back button
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
