package com.example.dell.mynotary.Schedule;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.dell.mynotary.AsyncTasks.AsyncResponse;
import com.example.dell.mynotary.AsyncTasks.WebserviceCall;
import com.example.dell.mynotary.Helpers.Const;
import com.example.dell.mynotary.Helpers.Utils;
import com.example.dell.mynotary.R;

import org.json.JSONArray;

import java.util.Calendar;

public class NewScheduleActivity extends AppCompatActivity {
    CalendarView calendarView;
    EditText titleET,descriptionET,venueET;
    TimePickerDialog pickerDialog;
    String selectedDate;
    TextView timeTV;
    String selectedTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_schedule);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        titleET = (EditText) findViewById(R.id.new_schedule_title);
        timeTV = (TextView) findViewById(R.id.new_schedule_time);
        descriptionET = (EditText) findViewById(R.id.new_schedule_description);
        venueET = (EditText) findViewById(R.id.new_schedule_venue);
        calendarView = (CalendarView) findViewById(R.id.new_schedule_calander);
        // time picker dialog
        // handle date change events
        Calendar mcurrentTime = Calendar.getInstance();
        final int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        final int minute = mcurrentTime.get(Calendar.MINUTE);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                selectedDate = dayOfMonth+"/"+month+"/"+year;


                pickerDialog = new TimePickerDialog(NewScheduleActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        selectedTime = ""+selectedHour + ":" + selectedMinute;
                        timeTV.setText(selectedTime);
                    }
                }, hour, minute,false);
                pickerDialog.setTitle("Set Time");
                pickerDialog.show();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            onBackPressed();
        }
        else if(id == R.id.menu_save_icon){
            String descStr = descriptionET.getText().toString();
            String venueStr = venueET.getText().toString();
            String titleStr = titleET.getText().toString();
            String keys[] = new String[]{"mode","university","subject","date","time","description"};
            String values[] = new String[]{"Schedule",venueStr,titleStr,selectedDate,selectedTime,descStr};
            String jsonRequest = Utils.createJsonRequest(keys,values);
            new WebserviceCall(this, Const.INSERT_URL, jsonRequest, "Saving...", true, Const.RESPONSE_MODE,new AsyncResponse() {
                @Override
                public void onSuccess(String message, JSONArray jsonData) {
                    Toast.makeText(NewScheduleActivity.this, message, Toast.LENGTH_SHORT).show();
//                    decodeJsonData(jsonData);
                    onBackPressed();
                }

                @Override
                public void onFailure(String message) {
                    Toast.makeText(NewScheduleActivity.this, message, Toast.LENGTH_SHORT).show();
                }
            }).execute();
        }
        return super.onOptionsItemSelected(item);
    }
}
