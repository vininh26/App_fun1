package com.example.app_fun;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.app_fun.Adapter.PhotoAdapter;
import com.example.app_fun.Adapter.TruyenAdapter;
import com.example.app_fun.Database.DatabaseSendTruyen;
import com.example.app_fun.Model.Photo;
import com.example.app_fun.Model.Truyen;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager ;
    private CircleIndicator circleIndicator ;
    private PhotoAdapter photoAdapter ;
    private TruyenAdapter truyenAdapter ;
    private ArrayList<Truyen> truyenArrayList;
    private DatabaseSendTruyen databaseSendTruyen ;
    private ListView lvListTruyen;
    private Toolbar toolbar;

    private List<Photo> mlist;
    private Timer mtimer ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseSendTruyen = new DatabaseSendTruyen(this);
        Anhxa();
        AcctionBar();
        autoSlide();
        lvListTruyen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this,MainReadbook.class);

                String tent = truyenArrayList.get(position).getTenTruyen();
                String noidungt = truyenArrayList.get(position).getNoiDung();
                intent.putExtra("tentruyen",tent);
                intent.putExtra("noidung",noidungt);
                startActivity(intent);
            }
        });

    }

    private void AcctionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

    }

    private void Anhxa() {
        toolbar = findViewById(R.id.toolbarmanhinhchinh);
        viewPager = findViewById(R.id.viewpager);
        circleIndicator = findViewById(R.id.circle);
        mlist = getListphoto();
        photoAdapter = new PhotoAdapter(this , (ArrayList<Photo>) getListphoto());
        viewPager.setAdapter(photoAdapter);
        circleIndicator.setViewPager(viewPager);
        photoAdapter.registerDataSetObserver(circleIndicator.getDataSetObserver());
        lvListTruyen = findViewById(R.id.lvListTruyen);


        truyenArrayList = new ArrayList<>();
        Cursor cursor1 = databaseSendTruyen.getData1();
        while (cursor1.moveToNext()){
            int id = cursor1.getInt(0);
            String tentruyen = cursor1.getString(1);
            String noidung = cursor1.getString(2);
            String anh = cursor1.getString(3);
            int id_tk = cursor1.getInt(4);

            truyenArrayList.add(new Truyen(id,tentruyen,noidung,anh,id_tk));
            truyenAdapter = new TruyenAdapter(getApplicationContext(),truyenArrayList);
            lvListTruyen.setAdapter(truyenAdapter);

        }cursor1.moveToFirst();
        cursor1.close();
    }

    private List<Photo> getListphoto(){
        List<Photo> list = new ArrayList<>();
        list.add(new Photo(R.drawable.image1));
        list.add(new Photo(R.drawable.image2));
        list.add(new Photo(R.drawable.image3));
        list.add(new Photo(R.drawable.image4));
        return list;
    }
    private void autoSlide(){
        if (mlist == null || mlist.isEmpty() || viewPager == null){
            return;
        }
        if (mtimer == null){
            mtimer = new Timer();
        }
        mtimer.schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        int curen  = viewPager.getCurrentItem();
                        int tootla = mlist.size() -1 ;
                        if (curen <tootla){
                            curen ++;
                            viewPager.setCurrentItem(curen);
                        }else {
                            viewPager.setCurrentItem(0);
                        }
                    }
                });
            }
        },500,3000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mtimer != null){
            mtimer.cancel();
            mtimer = null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu1:
                startActivity(new Intent(MainActivity.this,MainSearch.class));
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}