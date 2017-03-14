package com.example.dell.mynotary.Dictionary;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by DELL on 3/14/2017.
 */

public class DictionaryAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> arrayList;

    public DictionaryAdapter(Context context, ArrayList<String> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
