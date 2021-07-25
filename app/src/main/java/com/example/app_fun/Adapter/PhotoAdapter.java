package com.example.app_fun.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.app_fun.Model.Photo;
import com.example.app_fun.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class PhotoAdapter extends PagerAdapter {
    private Context context ;
    private ArrayList<Photo> mlistphoto ;

    public PhotoAdapter(Context context, ArrayList<Photo> mlistphoto) {
        this.context = context;
        this.mlistphoto = mlistphoto;
    }

    @NonNull
    @NotNull
    @Override
    public Object instantiateItem(@NonNull @NotNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.itemlayout,container,false);
        ImageView imageView = view.findViewById(R.id.imgphoto);
        Photo photo = mlistphoto.get(position);
        if (photo != null){
            Glide.with(context).load(photo.getResourId()).into(imageView);
        }
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        if (mlistphoto != null){
            return mlistphoto.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull @NotNull View view, @NonNull @NotNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull @NotNull ViewGroup container, int position, @NonNull @NotNull Object object) {
        container.removeView((View) object ) ;
    }
}
