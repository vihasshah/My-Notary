package com.example.dell.mynotary.Dictionary;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.dell.mynotary.AsyncTasks.AsyncResponse;
import com.example.dell.mynotary.AsyncTasks.WebserviceCall;
import com.example.dell.mynotary.Helpers.Const;
import com.example.dell.mynotary.Helpers.ObjetHolder;
import com.example.dell.mynotary.Helpers.Utils;
import com.example.dell.mynotary.R;
import com.google.gson.Gson;

import java.util.ArrayList;

public class DictionaryFragment extends Fragment {
    ListView listView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dictionary, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listView = (ListView) getActivity().findViewById(R.id.dictionary_listview);
    }
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_dictionary);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        //show back button
//        if(getSupportActionBar() != null) {
//            getActivity().getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//            getActivity().getSupportActionBar().setHomeButtonEnabled(true);
//        }
//
//        listView = (ListView) findViewById(R.id.dictionary_listview);
//
//
//
//    }
    @Override
    public void onResume() {
        super.onResume();
        ObjetHolder.dictionaryList = new ArrayList<>();
        String jsonRequest = Utils.createJsonRequest(new String[]{"mode"},new String[]{"Dictionary"});
        new WebserviceCall(getActivity(), Const.DETAILS_URL, jsonRequest, "getting dictionary....", true, new AsyncResponse() {
            @Override
            public void onCallback(String response) {
                DictionaryModel model = new Gson().fromJson(response,DictionaryModel.class);
                DictionaryAdapter dictionaryAdapter = new DictionaryAdapter(getActivity(),model.getData());
                listView.setAdapter(dictionaryAdapter);
            }
        }).execute();

    }

//    private void decodeJsonData(JSONArray jsonData) {
//        int count = jsonData.length();
//        for (int i = 0; i < count; i++) {
//            DictionaryModel model = new DictionaryModel();
//            try {
//                model.setWord(jsonData.getJSONObject(i).getString("word"));
//                model.setDescription(jsonData.getJSONObject(i).getString("description"));
//                ObjetHolder.dictionaryList.add(model);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if(item.getItemId()== android.R.id.home){
//            onBackPressed();
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
