package com.example.dell.mynotary.Schedule;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dell.mynotary.Helpers.Const;
import com.example.dell.mynotary.R;
import com.github.ivbaranov.mli.MaterialLetterIcon;

import java.util.ArrayList;

/**
 * Created by DELL on 2/21/2017.
 */


public class ScheduleAdapter extends BaseAdapter {
    Context context;
    ArrayList<ScheduleModel> arrayList;

    public ScheduleAdapter(Context context, ArrayList arrayList) {
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
        TextView universityTV,daysTv,timeTv,subjectTv;
        MaterialLetterIcon icon;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.single_row_schedule,parent,false);
            holder.subjectTv = (TextView) convertView.findViewById(R.id.row_schedule_subject);
            holder.universityTV = (TextView) convertView.findViewById(R.id.row_schedule_university);
            holder.daysTv=(TextView)convertView.findViewById(R.id.row_schedule_Day);
            holder.timeTv=(TextView)convertView.findViewById(R.id.row_schedule_Time);
            holder.icon = (MaterialLetterIcon) convertView.findViewById(R.id.row_schedule_image);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        //set icon
        String subject = arrayList.get(position).getSubject();
        holder.icon.setLetter(String.valueOf(subject.charAt(0)));
        holder.subjectTv.setText(subject);
        holder.universityTV.setText(arrayList.get(position).getUniversity());
        holder.daysTv.setText(arrayList.get(position).getDate());
        holder.timeTv.setText(arrayList.get(position).getTime());
        // handle convertview onclick
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // handling on click event
                // pass position to details activity to show details
                Intent intent = new Intent(context,ScheduleDetailActivity.class);
                intent.putExtra(Const.INTENT_POSITION,position);
                context.startActivity(intent);
            }
        });
        return convertView;
    }
}

