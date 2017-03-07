package com.example.dell.mynotary.CaseDetails;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.dell.mynotary.ObjetHolder;
import com.example.dell.mynotary.R;

import java.security.PolicySpi;
import java.util.ArrayList;

public class CaseDetailsActivity extends AppCompatActivity {
    TextView caseNoTV,caseNameTV,clientNameTV,courtNameTV,dateTV,descTV;
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
        caseNoTV = (TextView) findViewById(R.id.case_details_case_number);
        caseNameTV = (TextView) findViewById(R.id.case_details_case_name);
        clientNameTV = (TextView) findViewById(R.id.case_details_case_client_name);
        courtNameTV = (TextView) findViewById(R.id.case_details_case_court_name);
        dateTV = (TextView) findViewById(R.id.case_details_case_date);
        descTV = (TextView) findViewById(R.id.case_details_case_description);


        if(ObjetHolder.caseDetailsList != null && position != -1){
            // set values
            String caseName = ObjetHolder.caseDetailsList.get(position).getCaseName();
            String clientName = ObjetHolder.caseDetailsList.get(position).getClientName();
            String courtName = ObjetHolder.caseDetailsList.get(position).getCourtName();
            String caseNo = ObjetHolder.caseDetailsList.get(position).getCaseNo();
            String date = ObjetHolder.caseDetailsList.get(position).getDate();
            String details = ObjetHolder.caseDetailsList.get(position).getDetails();

            //seting values to TextViews
            caseNameTV.setText(caseName);
            clientNameTV.setText(clientName);
            courtNameTV.setText(courtName);
            caseNoTV.setText(caseNo);
            dateTV.setText(date);
            descTV.setText(details);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()== android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
