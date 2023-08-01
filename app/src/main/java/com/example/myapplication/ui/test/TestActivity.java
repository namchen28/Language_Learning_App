package com.example.myapplication.ui.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;

import com.example.myapplication.databinding.ActivityTestBinding;
import com.example.myapplication.ui.adapter.ViewpagerAdapter;
import com.example.myapplication.ui.model.TestModel;

import java.util.concurrent.TimeUnit;

public class TestActivity extends AppCompatActivity {
    ActivityTestBinding activityTestBinding;
    long minutes = 0;
   static CountDownTimer countDownTimer;
   static  int checked = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityTestBinding= ActivityTestBinding.inflate(getLayoutInflater());
        setContentView(activityTestBinding.getRoot());

        setSupportActionBar(activityTestBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        activityTestBinding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        activityTestBinding.btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checked=1;

            }
        });
        ViewpagerAdapter viewpagerAdapter = new ViewpagerAdapter(getSupportFragmentManager());
        viewpagerAdapter.addFragment(new Fragment_test(),"Test");
        viewpagerAdapter.addFragment(new Fragment_test_content(),"Choose Correct");
        activityTestBinding.viewpager.setAdapter(viewpagerAdapter);
        activityTestBinding.tapLayout.setupWithViewPager(activityTestBinding.viewpager);
        Intent intent= getIntent();
        TestModel testModel = (TestModel) intent.getSerializableExtra("TEST");
         minutes = testModel.getTime() * 1000*60;

         countDownTimer = new CountDownTimer(minutes,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                activityTestBinding.txtTime.setText(""+String.format("%d:%d",
                        TimeUnit.MILLISECONDS.toMinutes( millisUntilFinished),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));

            }

            @Override
            public void onFinish() {
                checked = 1;
            }
        }.start();
         activityTestBinding.btnFinish.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 checked = 1;
             }
         });



    }

}