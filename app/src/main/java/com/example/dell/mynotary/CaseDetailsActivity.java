package com.example.dell.mynotary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class CaseDetailsActivity extends AppCompatActivity {
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        // get position
        position = getIntent().getIntExtra("position",-1);
        // init



        if(ObjetHolder.caseDetailsList != null && position != -1){
            // set values
            String caseName = ObjetHolder.caseDetailsList.get(position).getCaseName();
            String clientName = ObjetHolder.caseDetailsList.get(position).getClientName();






        }
    }
}
