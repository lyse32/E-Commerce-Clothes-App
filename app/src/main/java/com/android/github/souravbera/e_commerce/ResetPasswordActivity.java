package com.android.github.souravbera.e_commerce;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.github.souravbera.e_commerce.Prevalent.Prevalent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class ResetPasswordActivity extends AppCompatActivity {

    private String check="";

    private TextView PageTitle,titleQuestion;
    private EditText PhoneNumber, question1, question2;
    private Button VerifyBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        PageTitle= findViewById(R.id.page_title);
        titleQuestion= findViewById(R.id.question_title);
        question1= findViewById(R.id.question_1);
        question2= findViewById(R.id.question_2);
        PhoneNumber= findViewById(R.id.find_phone_number);
        VerifyBtn= findViewById(R.id.verify_btn);


        VerifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        check= getIntent().getStringExtra("check");
    }

    @Override
    protected void onStart() {
        super.onStart();

        PhoneNumber.setVisibility(View.GONE);
        displayPreviousAnswers();
        if(check.equals("settings"))
        {
            PageTitle.setText("Set Questions");
            titleQuestion.setText("Please set the Answers for the following Security Questions");
            PhoneNumber.setVisibility(View.GONE);
            VerifyBtn.setText("set");

            VerifyBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setAnswers();

                }
            });

        }
        else if(check.equals("login"))
        {
            PhoneNumber.setVisibility(View.VISIBLE);
        }
    }

    private void setAnswers(){
        String answer1= question1.getText().toString().toLowerCase();
        String answer2= question1.getText().toString().toLowerCase();

        if(answer1.equals("") && answer2.equals(""))
        {
            Toast.makeText(ResetPasswordActivity.this, "Please answer the questions first",Toast.LENGTH_SHORT).show();
        }
        else{

            DatabaseReference ref= FirebaseDatabase.getInstance().getReference()
                    .child("Users").child(Prevalent.currentOnlineUser.getPhone());

            HashMap<String, Object> userdataMap= new HashMap<>();
            userdataMap.put("answer1",answer1);
            userdataMap.put("answer2",answer2);

            ref.child("Security Questions").updateChildren(userdataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(ResetPasswordActivity.this, "You have answered the security questions successfully!",Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(ResetPasswordActivity.this, HomeActivity.class);
                        startActivity(intent);
                    }
                }
            });

        }
    }

    private void displayPreviousAnswers(){
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference()
                .child("Users")
                .child(Prevalent.currentOnlineUser.getPhone());

        ref.child("Security Questions").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    String ans1= snapshot.child("answer1").getValue().toString();
                    String ans2= snapshot.child("answer2").getValue().toString();

                    question1.setText(ans1);
                    question2.setText(ans2);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
