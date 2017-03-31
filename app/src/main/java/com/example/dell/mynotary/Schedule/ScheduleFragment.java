package com.example.dell.mynotary.Schedule;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dell.mynotary.AsyncTasks.AsyncResponse;
import com.example.dell.mynotary.AsyncTasks.WebserviceCall;
import com.example.dell.mynotary.Helpers.Const;
import com.example.dell.mynotary.Helpers.ObjetHolder;
import com.example.dell.mynotary.R;
import com.example.dell.mynotary.Helpers.Utils;
import com.google.gson.Gson;

import java.util.ArrayList;

public class ScheduleFragment extends Fragment {


  // int position;
    ListView listView;
    String Day[];
    String Time[];
    String Subject[];
    int role = 0;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_schedulelist,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // get position
        //  position = getIntent().getIntExtra("position",-1);
        // init
        role = Utils.getRole(getActivity()); //role
        Day=getResources().getStringArray(R.array.day);
        Time=getResources().getStringArray(R.array.time);
        Subject=getResources().getStringArray(R.array.subject);


        //  if(ObjetHolder.scheduleModels != null && position != -1){
        // handled list view
        listView = (ListView) getActivity().findViewById(R.id.schedule_list_view);
    }

    @Override
    public void onResume() {
        super.onResume();
        String userid = Utils.getUserId(getActivity());
        int roleId = Utils.getRole(getActivity());
        ObjetHolder.scheduleModels = new ArrayList<>();
        String jsonRequest = Utils.createJsonRequest(new String[]{"mode","userid","roleid"},new String[]{"Schedule",userid, String.valueOf(roleId)});
        new WebserviceCall(getActivity(), Const.DETAILS_URL, jsonRequest, "getting schedules....", true, new AsyncResponse() {
            @Override
            public void onCallback(String response) {
                ScheduleModel model = new Gson().fromJson(response,ScheduleModel.class);
                if(model.getSuccess() == 1) {
                    ScheduleAdapter scheduleAdapter = new ScheduleAdapter(getActivity(), model.getData());
                    listView.setAdapter(scheduleAdapter);
                }else{
                    Toast.makeText(getActivity(), model.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

        }).execute();

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_add,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    //    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        if(role == Const.ROLE_LAWYER || role == Const.ROLE_UNIVERSITY) {
//
//        }
//        return super.onCreateOptionsMenu(menu);
//    }
//
    public boolean onOptionsItemSelected(MenuItem item) {
//        if(item.getItemId()== android.R.id.home){
//            onBackPressed();
//        }
         if(item.getItemId() == R.id.menu_add_icon){
            if(role == Const.ROLE_LAWYER || role == Const.ROLE_UNIVERSITY) {
                Intent intent = new Intent(getActivity(), NewScheduleActivity.class);
                startActivity(intent);
            }
        }
        return super.onOptionsItemSelected(item);
    }
//    private void decodeJsonData(JSONArray jsonData) {
//        int count = jsonData.length();
//        for (int i = 0; i < count; i++) {
//            ScheduleModel model = new ScheduleModel();
//            try {
//                model.setDate(jsonData.getJSONObject(i).getString("date"));
//                model.setSubject(jsonData.getJSONObject(i).getString("subject"));
//                model.setTime(jsonData.getJSONObject(i).getString("time"));
//                model.setUniversity(jsonData.getJSONObject(i).getString("university"));
//                ObjetHolder.scheduleModels.add(model);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//
//    }
}


