package com.example.app_fun.LoginApp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app_fun.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class MainForgorPass extends AppCompatActivity {
    private Toolbar toolbar ;
    private EditText edtemailResetpass ;
    private Button btnresetpass ;
    private FirebaseAuth auth ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_forgor_pass);
        Anhxa();
        AcctionBar();
        btnresetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPass();
            }
        });
    }

    private void Anhxa() {
        toolbar = findViewById(R.id.toolbarmanresertPass);
        edtemailResetpass = findViewById(R.id.edtemailResetpass);
        btnresetpass = findViewById(R.id.btnresetpass);
        auth = FirebaseAuth.getInstance();
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
    private void resetPass() {
        String email = edtemailResetpass.getText().toString().trim();
        if (email.isEmpty()){
            edtemailResetpass.setError("Email is Required");
            edtemailResetpass.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            edtemailResetpass.setError("Please provide valid emaill! ");
            edtemailResetpass.requestFocus();
            return;
        }
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<Void> task) {
                if (task.isSuccessful()){
                    startActivity(new Intent(MainForgorPass.this,loginApp.class));
                    Toast.makeText(MainForgorPass.this,"Check email to reset your Passworld " ,Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(MainForgorPass.this , "try again! Some thing wrong happened!  " ,Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}