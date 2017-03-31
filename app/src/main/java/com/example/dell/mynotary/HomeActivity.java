package com.example.dell.mynotary;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.dell.mynotary.CaseDetails.CaseFragment;
import com.example.dell.mynotary.Dictionary.DictionaryFragment;
import com.example.dell.mynotary.Helpers.Const;
import com.example.dell.mynotary.Helpers.Utils;
import com.example.dell.mynotary.Login.LoginActivity;
import com.example.dell.mynotary.Material.MaterialActivity;
import com.example.dell.mynotary.Schedule.NewScheduleActivity;
import com.example.dell.mynotary.Schedule.ScheduleFragment;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ListView listView;
    int role = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // init
        role = Utils.getRole(this);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        // checked first
        Menu navMenu = navigationView.getMenu();
        navMenu.getItem(0).setChecked(true);
        //handling menu
        MenuItem caseDetails = navMenu.findItem(R.id.nav_Case_Details);
        MenuItem lectSchedule = navMenu.findItem(R.id.nav_Lecture_Schedule);
        MenuItem lawDict = navMenu.findItem(R.id.nav_Law_Dictionary);
        MenuItem material = navMenu.findItem(R.id.nav_Material);
        if(role == Const.ROLE_CLIENT){
            material.setVisible(false);
            lectSchedule.setVisible(false);
        }else if(role == Const.ROLE_UNIVERSITY){
            caseDetails.setVisible(false);
        }else if(role == Const.ROLE_STUDENT){
            caseDetails.setVisible(false);
            lectSchedule.setVisible(false);
        }
        if(role == Const.ROLE_LAWYER || role == Const.ROLE_CLIENT) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.content_home, new CaseFragment(), CaseFragment.class.getSimpleName());
            transaction.addToBackStack(CaseFragment.class.getSimpleName());
            transaction.commit();
        }else if(role == Const.ROLE_UNIVERSITY){

        }else if (role == Const.ROLE_STUDENT){
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.content_home, new DictionaryFragment(), DialogFragment.class.getSimpleName());
            transaction.addToBackStack(CaseFragment.class.getSimpleName());
            transaction.commit();
        }
        // handled list view
//        listView = (ListView) findViewById(R.id.home_list_view);
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
//        ObjetHolder.caseDetailsList = new ArrayList<>();
//        // create request string
//
//        String jsonRequest = Utils.createJsonRequest(new String[]{"mode"},new String[]{"CaseDetails"});
//        new WebserviceCall(this, Const.DETAILS_URL, jsonRequest, "getting details...", true, new AsyncResponse() {
//            @Override
//            public void onCallback(String response) {
////                decodeJsonData(jsonData);
//                CaseDetailsModel model = new Gson().fromJson(response,CaseDetailsModel.class);
//                if(model.getSuccess() == 1) {
//                    Toast.makeText(HomeActivity.this, model.getMessage(), Toast.LENGTH_SHORT).show();
//                    MyCaseDetailsAdapter adapter = new MyCaseDetailsAdapter(HomeActivity.this, model.getData());
//                    listView.setAdapter(adapter);
//                }else{
//                    Toast.makeText(HomeActivity.this, model.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            }
//
////            @Override
////            public void onFailure(String message) {
////
////            }
//        }).execute();
    }

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
//        getMenuInflater().inflate(R.menu.home, menu);
//        Fragment fragment  = getSupportFragmentManager().findFragmentByTag(ScheduleFragment.class.getSimpleName());
//        if(fragment != null && fragment.isVisible()){
//            getMenuInflater().inflate(R.menu.menu_add,menu);
//        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Fragment fragment  = getSupportFragmentManager().findFragmentByTag(ScheduleFragment.class.getSimpleName());
        if(fragment.isVisible()){
            if(id == R.id.menu_add_icon){
                if(role == Const.ROLE_LAWYER || role == Const.ROLE_UNIVERSITY) {
                    Intent intent = new Intent(this, NewScheduleActivity.class);
                    startActivity(intent);
                }
            }
        }
        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

       if (id == R.id.nav_Case_Details) {
           FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
           transaction.replace(R.id.content_home, new CaseFragment(), CaseFragment.class.getSimpleName());
           transaction.addToBackStack(CaseFragment.class.getSimpleName());
           transaction.commit();
           // Handle the camera action
        } else if(id==R.id.nav_Law_Dictionary)
       {
           FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
           transaction.replace(R.id.content_home, new DictionaryFragment(), DictionaryFragment.class.getSimpleName());
           transaction.addToBackStack(DialogFragment.class.getSimpleName());
           transaction.commit();
        } else if (id == R.id.nav_Material) {
            Intent intent = new Intent(HomeActivity.this, MaterialActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_Lecture_Schedule) {
           FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
           transaction.replace(R.id.content_home, new ScheduleFragment(), ScheduleFragment.class.getSimpleName());
           transaction.addToBackStack(ScheduleFragment.class.getSimpleName());
           transaction.commit();
        } /*else if (id == R.id.nav_share) {

        }*/ else if (id == R.id.nav_Logout) {
                getSharedPreferences(Const.SHAREDPREFERENCE_NAME,MODE_PRIVATE).edit().clear().apply();
               Intent intent = new Intent(HomeActivity.this,LoginActivity.class);
               intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
               startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

//    @Override
//    public boolean onPrepareOptionsMenu(Menu menu) {
//        menu.getItem(0).setChecked(true);
//        //handling menu
//        MenuItem caseDetails = menu.findItem(R.id.nav_Case_Details);
//        MenuItem lectSchedule = menu.findItem(R.id.nav_Lecture_Schedule);
//        MenuItem lawDict = menu.findItem(R.id.nav_Law_Dictionary);
//        MenuItem materialItem = menu.findItem(R.id.nav_Material);
//        Log.d("myapp","Role : "+role);
//        if(role == Const.ROLE_CLIENT){
//            materialItem.setVisible(false);
//            lawDict.setVisible(false);
//            caseDetails.setVisible(true);
//            lectSchedule.setVisible(true);
//        }else if(role == Const.ROLE_UNIVERSITY){
//            caseDetails.setVisible(false);
//        }else if(role == Const.ROLE_STUDENT){
//            caseDetails.setVisible(false);
//            lectSchedule.setVisible(false);
//        }else{
//
//        }
//        return true;
//    }
}
