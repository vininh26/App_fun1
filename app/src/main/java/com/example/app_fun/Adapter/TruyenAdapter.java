package com.example.app_fun.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.app_fun.Model.Truyen;
import com.example.app_fun.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TruyenAdapter extends BaseAdapter {
    private Context context ;
    private ArrayList<Truyen> listtruyen;

    public TruyenAdapter(Context context, ArrayList<Truyen> listtruyen) {
        this.context = context;
        this.listtruyen = listtruyen;
    }
    @Override
    public int getCount() {
        return listtruyen.size();
    }

    @Override
    public Object getItem(int position) {
        return listtruyen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    // tìm kiếm
    public void filterList(ArrayList<Truyen> filtedList) {
        listtruyen = filtedList;
        notifyDataSetChanged();
    }

    public class  ViewHolder{
        TextView txtTentruyen ;
        ImageView imgTruyen ;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null ;
        viewHolder = new ViewHolder();

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.itemtruyen,null);

        viewHolder.txtTentruyen = convertView.findViewById(R.id.txtTentruyen);
        viewHolder.imgTruyen = convertView.findViewById(R.id.imageTruyen);
        convertView.setTag(viewHolder);

        Truyen truyen = (Truyen) getItem(position);
        viewHolder.txtTentruyen.setText(truyen.getTenTruyen());

        Picasso.get().load(truyen.getAnh()).placeholder(R.drawable.ic_baseline_cloud_download_24)
                .error(R.drawable.ic_baseline_image_not_supported_24).into(viewHolder.imgTruyen);

        return convertView;
    }
}
