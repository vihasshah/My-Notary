package com.example.dell.mynotary.CaseDetails;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dell.mynotary.AsyncTasks.AsyncResponse;
import com.example.dell.mynotary.AsyncTasks.WebserviceCall;
import com.example.dell.mynotary.Helpers.Const;
import com.example.dell.mynotary.Helpers.ObjetHolder;
import com.example.dell.mynotary.Helpers.Utils;
import com.example.dell.mynotary.HomeActivity;
import com.example.dell.mynotary.R;
import com.google.gson.Gson;
import com.squareup.okhttp.internal.Util;

import java.util.ArrayList;

/**
 * Created by Vihas on 24-03-2017.
 */

public class CaseFragment extends Fragment {
    ListView listView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_case, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listView = (ListView) getActivity().findViewById(R.id.case_list_view);
    }

    @Override
    public void onResume() {
        super.onResume();
        String userid = Utils.getUserId(getActivity());
        int roleId = Utils.getRole(getActivity());

        ObjetHolder.caseDetailsList = new ArrayList<>();
        // create request string

        String jsonRequest = Utils.createJsonRequest(new String[]{"mode","userid","roleid"},new String[]{"CaseDetails",userid, String.valueOf(roleId)});
        Log.d("myapp","casedetails:"+jsonRequest);
        new WebserviceCall(getActivity(), Const.DETAILS_URL, jsonRequest, "getting details...", true, new AsyncResponse() {
            @Override
            public void onCallback(String response) {
//                decodeJsonData(jsonData);
                CaseDetailsModel model = new Gson().fromJson(response,CaseDetailsModel.class);
                if(model.getSuccess() == 1) {
                    Toast.makeText(getActivity(), model.getMessage(), Toast.LENGTH_SHORT).show();
                    MyCaseDetailsAdapter adapter = new MyCaseDetailsAdapter(getActivity(), model.getData());
                    listView.setAdapter(adapter);
                }else{
                    Toast.makeText(getActivity(), model.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

        }).execute();
    }
}
