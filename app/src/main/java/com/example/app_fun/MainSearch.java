package com.example.app_fun;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.app_fun.Adapter.TruyenAdapter;
import com.example.app_fun.Database.DatabaseSendTruyen;
import com.example.app_fun.Model.Truyen;

import java.util.ArrayList;

public class MainSearch extends AppCompatActivity {
    EditText timkiem ;
    ListView lisviewtimkiem ;
    ArrayList<Truyen> truyenArrayList;
    ArrayList<Truyen> arrayList ;
    TruyenAdapter truyenAdapter ;
    DatabaseSendTruyen databaseSendTruyen ;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_search);
        toolbar = findViewById(R.id.toolbarmanhinhsearch);
        lisviewtimkiem = findViewById(R.id.lisviewtimkiem);
        timkiem = findViewById(R.id.timkiem);
        AcctionBar();
        initList();
        lisviewtimkiem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainSearch.this,MainReadbook.class);
                String tent = arrayList.get(position).getTenTruyen();
                String noidungt = arrayList.get(position).getNoiDung();
                intent.putExtra("tentruyen",tent);
                intent.putExtra("noidung",noidungt);
                startActivity(intent);
            }
        });
        timkiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
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
    //Search
    private void  filter(String text){
        // xóa dữ liệu mảng
        arrayList.clear();
        ArrayList<Truyen> filtedList = new ArrayList<>();
        for (Truyen item : truyenArrayList){
            if (item.getTenTruyen().toLowerCase().contains(text.toLowerCase())){
                // thêm item vào filterList
                filtedList.add(item);
                // thêm vào mảng
                arrayList.add(item);

            }
        }
        truyenAdapter.filterList(filtedList);
    }

    private void initList() {
        truyenArrayList = new ArrayList<>();
        arrayList = new ArrayList<>() ;
        databaseSendTruyen = new DatabaseSendTruyen(this);
        Cursor cursor = databaseSendTruyen.getData2();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String tentruyen = cursor.getString(1);
            String noidung = cursor.getString(2);
            String anh = cursor.getString(3) ;
            int id_tk = cursor.getInt(4);
            truyenArrayList.add(new Truyen(id,tentruyen,noidung,anh,id_tk));

            arrayList.add(new Truyen(id,tentruyen,noidung,anh,id_tk));

            truyenAdapter = new TruyenAdapter(getApplicationContext(),truyenArrayList);
            lisviewtimkiem.setAdapter(truyenAdapter);
        }
        cursor.moveToFirst();
        cursor.close();
    }
}