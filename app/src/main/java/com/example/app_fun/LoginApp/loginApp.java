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

import com.example.app_fun.MainActivity;
import com.example.app_fun.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.jetbrains.annotations.NotNull;

public class loginApp extends AppCompatActivity implements View.OnClickListener {
    private TextView txtRegisterUser ,txtforgorPass ;
    private EditText edtemaillogin , edtpasslogin;
    private Button btnlogin;
    private FirebaseAuth mAuth ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_app);
        Anhxa();
    }

    private void Anhxa() {
        txtRegisterUser = findViewById(R.id.txtRegisterUser);
        txtRegisterUser.setOnClickListener(this);
        txtforgorPass = findViewById(R.id.txtforgorPass);
        txtforgorPass.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
        edtemaillogin = findViewById(R.id.edtemaillogin);
        edtpasslogin = findViewById(R.id.edtpasslogin) ;
        btnlogin = findViewById(R.id.btnLogin);
        btnlogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txtRegisterUser:
                startActivity(new Intent(loginApp.this,Register_User.class));
                break;
            case R.id.txtforgorPass:

                break;
            case  R.id.btnLogin:
                userLogin();
        }
    }

    private void userLogin() {
        String email = edtemaillogin.getText().toString().trim();
        String pass = edtpasslogin.getText().toString().trim();
        if (email.isEmpty()){
            edtemaillogin.setError("Email is required");
            edtemaillogin.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            edtemaillogin.setError("Please enter a valid email!");
            edtemaillogin.requestFocus();
            return;
        }
        if (pass.isEmpty()){
            edtpasslogin.setError("PassWorld is required");
            edtpasslogin.requestFocus();
            return;
        }
        if (pass.length()<6){
            edtpasslogin.setError("Min PassWorld length is 6 charaters!");
            edtpasslogin.requestFocus();
            return;
        }
        mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user.isEmailVerified()){
                        startActivity(new Intent(loginApp.this, MainActivity.class));
                    }else {
                        user.sendEmailVerification();
                        Toast.makeText(loginApp.this, "check your email to verify you account! " , Toast.LENGTH_LONG).show();
                    }

                }else {
                    Toast.makeText(loginApp.this,"Failed to login ! Please check your credentials",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}