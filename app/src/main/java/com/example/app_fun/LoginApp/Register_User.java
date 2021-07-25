package com.example.app_fun.LoginApp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app_fun.Model.User;
import com.example.app_fun.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

public class Register_User extends AppCompatActivity implements View.OnClickListener {
    private TextView baner ;
    private EditText edtfullname ,edtemail,edtphone,edtage,edtpass,edtschools,edtsothich;
    private Button btnregister ;
    private FirebaseAuth mAth ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        Anhxa() ;
    }

    private void Anhxa() {
        mAth = FirebaseAuth.getInstance();
        edtfullname = findViewById(R.id.edtfullname);
        edtemail = findViewById(R.id.edtemail);
        edtphone = findViewById(R.id.edtphone);
        edtage = findViewById(R.id.edtage);
        edtpass = findViewById(R.id.edtpass) ;
        edtschools = findViewById(R.id.edtschools);
        edtsothich = findViewById(R.id.edtsothich);
        btnregister = findViewById(R.id.btnregister);
        btnregister.setOnClickListener(this);
        baner = findViewById(R.id.baner);
        baner.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.baner:
                startActivity(new Intent(Register_User.this,loginApp.class));
                break;
            case R.id.btnregister:
                registerUser();
                break;
        }
    }

    private void registerUser() {
        String fullname = edtfullname.getText().toString().trim();
        String email = edtemail.getText().toString().trim();
        String phone = edtphone.getText().toString().trim();
        String age = edtage.getText().toString().trim();
        String pass = edtpass.getText().toString().trim();
        String schools = edtschools.getText().toString().trim();
        String sothich = edtsothich.getText().toString().trim();
        if (fullname.isEmpty()){
            edtfullname.setError("full name is register");
            edtfullname.requestFocus();
            return;
        }
        if (age.isEmpty()){
            edtage.setError("Age is register");
            edtage.requestFocus();
            return;
        }
        if (email.isEmpty()){
            edtemail.setError("Email is register");
            edtemail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            edtemail.setError("Please provide valid email! ");
            edtemail.requestFocus();
            return;
        }
        if (phone.isEmpty()){
            edtphone.setError("PassWorld is register");
            edtphone.requestFocus();
            return;
        }
//        if (phone.length()>=10) {
//            edtphone.setError("Min passworld length should be 10 characters!");
//            edtphone.requestFocus();
//            return;
//        }
        if (pass.isEmpty()){
            edtpass.setError("PassWorld is register");
            edtpass.requestFocus();
            return;
        }
        if (pass.length()<6) {
            edtpass.setError("Min passworld length should be 6 characters!");
            edtpass.requestFocus();
            return;
        }
        if (schools.isEmpty()){
            edtschools.setError("Schools is register");
            edtschools.requestFocus();
            return;
        }
        if (sothich.isEmpty()){
            edtsothich.setError("Sothich is register");
            edtsothich.requestFocus();
            return;
        }
        mAth.createUserWithEmailAndPassword(email ,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    User user = new User(fullname,email,phone,age,pass,sothich,schools);
                    FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<Void> task) {
                            if (task.isSuccessful()){
                                startActivity(new Intent(Register_User.this,loginApp.class));
                                Toast.makeText(Register_User.this,"User has been register successfully! " ,Toast.LENGTH_LONG).show();
                            }else {
                                Toast.makeText(Register_User.this,"Failed to register Try again !" ,Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });

    }
}