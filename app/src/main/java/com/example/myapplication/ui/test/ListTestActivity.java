package com.example.myapplication.ui.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityListTestBinding;
import com.example.myapplication.ui.HomeActivity;
import com.example.myapplication.ui.adapter.TestAdapter;
import com.example.myapplication.ui.model.TestModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.List;

public class ListTestActivity extends AppCompatActivity {

    ActivityListTestBinding activityListTestBinding;
    ArrayList<TestModel> arrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityListTestBinding= ActivityListTestBinding.inflate(getLayoutInflater());
        setContentView(activityListTestBinding.getRoot());
        setSupportActionBar(activityListTestBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Test List");
        activityListTestBinding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        arrayList = new ArrayList<>();

        FirebaseFirestore.getInstance().collection("ListTest")
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for(DocumentSnapshot d :queryDocumentSnapshots){
                            String answer=d.getString("answer");
                            String test=d.getString("test");
                            String correct=d.getString("correct");
                            String title=d.getString("title");
                            long time=d.getLong("time");
                            arrayList.add(new TestModel(d.getId(),title,test,answer,time,correct));
                        }
                        TestAdapter testAdapter = new TestAdapter(ListTestActivity.this,arrayList);
                        activityListTestBinding.rcvLuyenThi.setLayoutManager(new LinearLayoutManager(ListTestActivity.this));
                        activityListTestBinding.rcvLuyenThi.setAdapter(testAdapter);
                    }
                });


    }
}