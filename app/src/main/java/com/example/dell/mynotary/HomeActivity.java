package com.example.dell.mynotary;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dell.mynotary.AsyncTasks.AsyncResponse;
import com.example.dell.mynotary.AsyncTasks.WebserviceCall;
import com.example.dell.mynotary.CaseDetails.CaseDetailsModel;
import com.example.dell.mynotary.CaseDetails.MyCaseDetailsAdapter;
import com.example.dell.mynotary.Dictionary.DictionaryActivity;
import com.example.dell.mynotary.Helpers.Const;
import com.example.dell.mynotary.Helpers.ObjetHolder;
import com.example.dell.mynotary.Helpers.Utils;
import com.example.dell.mynotary.Login.LoginActivity;
import com.example.dell.mynotary.Schedule.ScheduleActivity;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        // checked first
        navigationView.getMenu().getItem(0).setChecked(true);
        // handled list view
        listView = (ListView) findViewById(R.id.home_list_view);


       /* ObjetHolder.scheduleModels = new ArrayList<>();
        ScheduleModel model1=new ScheduleModel();
        model1.setLectureschedulelist("lectureschedulelist");

        ObjetHolder.scheduleModels.add(model1);
        // handled list view
        listView = (ListView) findViewById(R.id.home_list_view);
        ScheduleAdapter myschedulelistAdapter = new ScheduleAdapter(this,ObjetHolder.scheduleModels);
        listView.setAdapter(myschedulelistAdapter);*/

    }

    @Override
    protected void onResume() {
        super.onResume();
        ObjetHolder.caseDetailsList = new ArrayList<>();
        // create request string

        String jsonRequest = Utils.createJsonRequest(new String[]{"mode"},new String[]{"CaseDetails"});
        new WebserviceCall(this, Const.DETAILS_URL, jsonRequest, "getting details...", true, new AsyncResponse() {
            @Override
            public void onCallback(String response) {
//                decodeJsonData(jsonData);
                CaseDetailsModel model = new Gson().fromJson(response,CaseDetailsModel.class);
                if(model.getSuccess() == 1) {
                    Toast.makeText(HomeActivity.this, model.getMessage(), Toast.LENGTH_SHORT).show();
                    MyCaseDetailsAdapter adapter = new MyCaseDetailsAdapter(HomeActivity.this, model.getData());
                    listView.setAdapter(adapter);
                }else{
                    Toast.makeText(HomeActivity.this, model.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

//            @Override
//            public void onFailure(String message) {
//
//            }
        }).execute();
    }

//    private void decodeJsonData(JSONArray jsonData) {
//        int count = jsonData.length();
//        for (int i = 0; i < count; i++) {
//            CaseDetailsModel model = new CaseDetailsModel();
//            try {
//                model.setCaseNo(jsonData.getJSONObject(i).getString("caseno"));
//                model.setCaseName(jsonData.getJSONObject(i).getString("title"));
//                model.setDetails(jsonData.getJSONObject(i).getString("details"));
//                model.setCourtName(jsonData.getJSONObject(i).getString("court_name"));
//                model.setDate(jsonData.getJSONObject(i).getString("date"));
//                model.setClientName(jsonData.getJSONObject(i).getString("client_name"));
//                ObjetHolder.caseDetailsList.add(model);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//
//    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            this.finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

       if (id == R.id.nav_Lecture_Schedule) {
           Intent intent = new Intent(HomeActivity.this, ScheduleActivity.class);
           startActivity(intent);

           // Handle the camera action
        } else if(id==R.id.nav_Law_Dictionary)
       {
           Intent intent = new Intent(HomeActivity.this, DictionaryActivity.class);
           startActivity(intent);
        }/* else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        }*/ else if (id == R.id.nav_Logout) {
                getSharedPreferences("testpref",MODE_PRIVATE).edit().clear().apply();
               Intent intent = new Intent(HomeActivity.this,LoginActivity.class);
               intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
               startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
