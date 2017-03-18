package com.example.dell.mynotary.Dictionary;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.dell.mynotary.Helpers.Const;
import com.example.dell.mynotary.R;

public class DictionaryDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //show back button
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
        //get serialize class from the intent
        DictionaryModel.DataBean bean = (DictionaryModel.DataBean) getIntent().getSerializableExtra(Const.INTENT_POSITION_CLASS);

        // init
        TextView titleTV = (TextView) findViewById(R.id.dict_details_title);
        TextView descTV = (TextView) findViewById(R.id.dict_details_desc);

        // set values
        titleTV.setText(bean.getWord());
        descTV.setText(bean.getDescription());

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()== android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

}
