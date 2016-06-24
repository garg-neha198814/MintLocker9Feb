package com.bss.mintlocker.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bss.mintlocker.R;
import com.bss.mintlocker.model.ActivityModel;

import java.util.List;

/**
 * Created by bhawanisingh on 04/12/15.
 */
public class ActivityAdapter extends BaseAdapter {
    Context context;
    List<ActivityModel> rowItems;

//    public PostItemListenerCustom mListener;


//    public interface PostItemListenerCustom {
//        void onPostItemClicked(ActivityModel s);
//    }

    public ActivityAdapter(Context context, List<ActivityModel> items) {
        this.context = context;
        this.rowItems = items;
//        mListener = (PostItemListenerCustom) context;
    }

    /*private view holder class*/
    private class ViewHolder {

        LinearLayout mainframe;
        TextView txtTitleDate;
        TextView txtTitle;
        TextView txtLargeAmount;
        TextView txtSmallAmount;
        TextView txtSmallDate;
        ImageView download;


    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        convertView = null;
        LayoutInflater mInflater = (LayoutInflater)
                context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.adapter_activity_item, null);
            holder = new ViewHolder();
            holder.txtTitle = (TextView) convertView.findViewById(R.id.activity_header);
            holder.txtTitleDate = (TextView) convertView.findViewById(R.id.activity_date);
            holder.txtLargeAmount = (TextView) convertView.findViewById(R.id.activity_amount_large);
            holder.txtSmallAmount = (TextView) convertView.findViewById(R.id.activity_amount_small);
            holder.txtSmallDate = (TextView) convertView.findViewById(R.id.activity_date_item);
            holder.download = (ImageView) convertView.findViewById(R.id.activity_download);
            holder.mainframe = (LinearLayout) convertView.findViewById(R.id.activity_header_main);


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final ActivityModel rowItem = (ActivityModel) getItem(position);

        if (rowItem.getShowDownload().equalsIgnoreCase("0")) {
            holder.download.setVisibility(View.INVISIBLE);
        } else {
            holder.download.setVisibility(View.VISIBLE);
        }

        if (rowItem.getShowdate().equalsIgnoreCase("0")) {
            holder.mainframe.setVisibility(View.GONE);
        } else {
            holder.mainframe.setVisibility(View.VISIBLE);
        }

        holder.txtTitle.setText(rowItem.getHeading());
        holder.txtTitleDate.setText(rowItem.getDateMain());
        holder.txtLargeAmount.setText("$ " + rowItem.getLargeAmount());
        holder.txtSmallAmount.setText("$ " + rowItem.getSmallAmount());

        holder.txtSmallDate.setText("" + rowItem.getSmalldate());


        return convertView;
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
}