package com.example.myapplication.ui.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityHistoryBinding;
import com.example.myapplication.ui.HomeActivity;
import com.example.myapplication.ui.adapter.HistoryAdapter;
import com.example.myapplication.ui.model.HistoryModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    ActivityHistoryBinding activityHistoryBinding;
    ArrayList<HistoryModel> arrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityHistoryBinding = ActivityHistoryBinding.inflate(getLayoutInflater());
        setContentView(activityHistoryBinding.getRoot());
        setSupportActionBar(activityHistoryBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("My History");
        activityHistoryBinding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            }
        });

        FirebaseFirestore.getInstance().collection("History")
                .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .collection("MyHistory")
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        for(DocumentSnapshot d : queryDocumentSnapshots){
                            String id= d.getId();
                            String title = d.getString("title");
                            String date = d.getString("date");
                            long time = d.getLong("time");
                            double point = d.getDouble("point");
                            arrayList.add(new HistoryModel(id,date,point,time,title));
                        }
                        HistoryAdapter historyAdapter = new HistoryAdapter(HistoryActivity.this,arrayList);
                        activityHistoryBinding.rcvHistory.setLayoutManager(new LinearLayoutManager(HistoryActivity.this));
                        activityHistoryBinding.rcvHistory.setAdapter(historyAdapter);

                    }
                });


    }
}