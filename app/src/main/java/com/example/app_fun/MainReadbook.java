package com.example.app_fun;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

public class MainReadbook extends AppCompatActivity {
   private TextView Tentruyen,noiDung ;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_readbook);
        toolbar = findViewById(R.id.toolbarmanhinhread);
        AcctionBar();
        Tentruyen = findViewById(R.id.TenTruyen);
        noiDung = findViewById(R.id.NoiDung);
        // lấy dữ liệu
        Intent intent = getIntent();
        String tentruyen = intent.getStringExtra("tentruyen");
        String noidung = intent.getStringExtra("noidung");
        Tentruyen.setText(tentruyen);
        noiDung.setText(noidung);
        // cho phép cuộn nội dung truyện
        noiDung.setMovementMethod(new ScrollingMovementMethod());
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
}