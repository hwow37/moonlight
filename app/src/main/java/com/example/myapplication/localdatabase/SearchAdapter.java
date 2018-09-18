package com.example.myapplication.localdatabase;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.ArrayList;


public class SearchAdapter extends BaseAdapter {
    // Declare Variables
    private ArrayList<InfoDB> arrayList;
    private LayoutInflater inflater;
    private ViewHolder viewHolder;

    public SearchAdapter(Context c, ArrayList<InfoDB> array) {
        inflater = LayoutInflater.from(c);
        arrayList = array;
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

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.search_row, null);
            viewHolder.label = (TextView) convertView.findViewById(R.id.label);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Set the results into TextViews
        viewHolder.label.setText(arrayList.get(position).name);

        return convertView;
    }

    class ViewHolder {
        private TextView label;
    }

    public String getText(int position){
        return arrayList.get(position).name;
    }

    public void setArrayList(ArrayList<InfoDB> arrays){
        this.arrayList = arrays;
    }

    public ArrayList<InfoDB> getArrayList(){
        return arrayList;
    }
}