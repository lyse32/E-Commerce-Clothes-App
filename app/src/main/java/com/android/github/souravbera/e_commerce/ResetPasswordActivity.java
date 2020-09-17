package com.android.github.souravbera.e_commerce;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResetPasswordActivity extends AppCompatActivity {

    private String check="";

    private TextView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        check= getIntent().getStringExtra("check");
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(check.equals("settings"))
        {

        }
        else if(check.equals("login"))
        {

        }
    }
}
