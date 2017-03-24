package com.example.dell.mynotary.CaseDetails;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dell.mynotary.Helpers.Const;
import com.example.dell.mynotary.Helpers.Utils;
import com.example.dell.mynotary.R;
import com.github.ivbaranov.mli.MaterialLetterIcon;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 2/17/2017.
 */

public class MyCaseDetailsAdapter extends BaseAdapter {
    Context context;
    List<CaseDetailsModel.DataBean> arrayList;

    public MyCaseDetailsAdapter(Context context, List<CaseDetailsModel.DataBean> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder{
        MaterialLetterIcon icon;
        TextView caseNameTV;
        TextView clientNameTV;
        TextView detailsTV;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.single_row_casedetails,parent,false);
            holder.caseNameTV = (TextView) convertView.findViewById(R.id.row_casedetails_casename);
            holder.clientNameTV=(TextView)convertView.findViewById(R.id.row_casedetails_clientname );
            holder.detailsTV=(TextView)convertView.findViewById(R.id.row_casedetails_details);
            holder.icon = (MaterialLetterIcon) convertView.findViewById(R.id.row_casedetails_image);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        // set material letter
        String caseName = arrayList.get(position).getTitle();
        String firstChar = String.valueOf(caseName.charAt(0));
        holder.icon.setLetter(firstChar);

        // set rest values
        holder.caseNameTV.setText(caseName);
        if(Utils.getRole(context) == Const.ROLE_CLIENT){
            holder.clientNameTV.setVisibility(View.GONE);
        }else{
            holder.clientNameTV.setText(arrayList.get(position).getClient_name());
        }
        holder.detailsTV.setText(arrayList.get(position).getDetails());
        // handle convertview onclick
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // go to details activity
                Intent intent = new Intent(context,CaseDetailsActivity.class);
                intent.putExtra(Const.INTENT_POSITION_CLASS,arrayList.get(position));
                context.startActivity(intent);
            }
        });
        return convertView;
    }
}
