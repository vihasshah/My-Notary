package com.example.dell.mynotary.CaseDetails;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.dell.mynotary.Helpers.Const;
import com.example.dell.mynotary.Helpers.ObjetHolder;
import com.example.dell.mynotary.R;

public class CaseDetailsActivity extends AppCompatActivity {
    TextView caseNoTV,caseNameTV,clientNameTV,courtNameTV,dateTV,descTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        // get position
        CaseDetailsModel.DataBean model = (CaseDetailsModel.DataBean) getIntent().getSerializableExtra(Const.INTENT_POSITION_CLASS);
        // init
        caseNoTV = (TextView) findViewById(R.id.case_details_case_number);
        caseNameTV = (TextView) findViewById(R.id.case_details_case_name);
        clientNameTV = (TextView) findViewById(R.id.case_details_case_client_name);
        courtNameTV = (TextView) findViewById(R.id.case_details_case_court_name);
        dateTV = (TextView) findViewById(R.id.case_details_case_date);
        descTV = (TextView) findViewById(R.id.case_details_case_description);


        //seting values to TextViews
        caseNameTV.setText(model.getTitle());
        clientNameTV.setText(model.getClient_name());
        courtNameTV.setText(model.getCourt_name());
        caseNoTV.setText(model.getCaseno());
        dateTV.setText(model.getDate());
        descTV.setText(model.getDetails());

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()== android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
