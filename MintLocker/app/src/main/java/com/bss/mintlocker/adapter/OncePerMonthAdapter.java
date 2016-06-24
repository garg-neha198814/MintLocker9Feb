package com.bss.mintlocker.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bss.mintlocker.R;
import com.bss.mintlocker.model.OncePerMonthModel;

import java.util.List;

/**
 * Created by bhawanisingh on 19/11/15.
 */
public class OncePerMonthAdapter extends BaseAdapter {
    private Context mContext;
    List<OncePerMonthModel> rowItems;
    public datelistener mListener;

    public interface datelistener {
        void ondateItemClicked(OncePerMonthModel s);
    }
    public OncePerMonthAdapter(Context c, List<OncePerMonthModel> items) {
        mContext = c;
        this.rowItems = items;
        mListener = (datelistener) mContext;
    }

    @Override
    public int getCount() {
        return rowItems.size();
    }

    @Override
    public Object getItem(int position) {
        return rowItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return rowItems.indexOf(getItem(position));
    }


    /*private view holder class*/
    private class ViewHolder {

        TextView noofdays;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder holder = null;

        LayoutInflater mInflater = (LayoutInflater)
                mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.adapter_once_per_month_list_item, null);
            holder = new ViewHolder();

            holder.noofdays = (TextView) convertView.findViewById(R.id.tv_once_per_month_number_of_day);


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final OncePerMonthModel rowItem = (OncePerMonthModel) getItem(position);


        holder.noofdays.setText(rowItem.getNoofday()+"");

        holder.noofdays.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.ondateItemClicked(rowItem);
            }
        });


        return convertView;
    }
}