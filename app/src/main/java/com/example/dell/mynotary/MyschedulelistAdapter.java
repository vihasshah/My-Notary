package com.example.dell.mynotary;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by DELL on 2/21/2017.
 */


public class MyschedulelistAdapter extends BaseAdapter {
    Context context;
    ArrayList<SchedulelistModel> arrayList;

    public MyschedulelistAdapter(Context context, ArrayList arrayList) {
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

    static class ViewHolder{
        TextView lecturescheduleTV;
        TextView DaysTv;
        TextView TimeTv;

    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.single_row_schedulelist_,parent,false);
            holder.lecturescheduleTV = (TextView) convertView.findViewById(R.id.row_schedule_lectureschedule);
            holder.DaysTv=(TextView)convertView.findViewById(R.id.row_schedule_Day);
            holder.TimeTv=(TextView)convertView.findViewById(R.id.row_schedule_Time);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.DaysTv.setText(arrayList.get(position).getDay());
        holder.TimeTv.setText(arrayList.get(position).getTime());
        // handle convertview onclick
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // go to details activity
                Intent intent = new Intent(context,SchedulelistActivity.class);
                intent.putExtra("position",position);
                context.startActivity(intent);
            }
        });
        return convertView;
    }
}

