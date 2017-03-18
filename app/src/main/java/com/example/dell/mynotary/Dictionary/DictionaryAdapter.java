package com.example.dell.mynotary.Dictionary;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dell.mynotary.Helpers.Const;
import com.example.dell.mynotary.Schedule.ScheduleDetailActivity;

import java.util.List;

/**
 * Created by DELL on 3/14/2017.
 */

public class DictionaryAdapter extends BaseAdapter {
    Context context;
    List<DictionaryModel.DataBean> arrayList;

    public DictionaryAdapter(Context context, List<DictionaryModel.DataBean> arrayList) {
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
        TextView wordTV;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1,parent,false);
            holder.wordTV = (TextView) convertView.findViewById(android.R.id.text1);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        //set icon
        String subject = arrayList.get(position).getWord();
        holder.wordTV.setText(subject);
        // handle convertview onclick
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // handling on click event
                // pass position to details activity to show details
                Intent intent = new Intent(context,DictionaryDetailsActivity.class);
                intent.putExtra(Const.INTENT_POSITION_CLASS,arrayList.get(position));
                context.startActivity(intent);
            }
        });

        return convertView;
    }



}
