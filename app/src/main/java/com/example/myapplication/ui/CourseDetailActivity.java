package com.example.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityCourseBinding;
import com.example.myapplication.ui.adapter.CourseAdapter;
import com.example.myapplication.ui.adapter.CourseChildAdapter;
import com.example.myapplication.ui.model.CourseChildModel;
import com.example.myapplication.ui.model.CoursesModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class CourseDetailActivity extends AppCompatActivity {
    ActivityCourseBinding activityCourseBinding;
    ArrayList<CourseChildModel> arrayList = new ArrayList<>();
    CoursesModel coursesModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityCourseBinding = ActivityCourseBinding.inflate(getLayoutInflater());
        setContentView(activityCourseBinding.getRoot());
        Intent intent = getIntent();
        coursesModel = (CoursesModel) intent.getSerializableExtra("Course");
        setSupportActionBar(activityCourseBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(coursesModel.getName());
        activityCourseBinding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        activityCourseBinding.bottomNavigation.setSelectedItemId(R.id.bottom_course);

        activityCourseBinding.bottomNavigation.setOnItemSelectedListener(this::onNavigationItemSelected);
        FirebaseFirestore.getInstance().collection("ChildCourse")
                .whereEqualTo("id",coursesModel.getId())
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for(DocumentSnapshot d : queryDocumentSnapshots){
                            String video = d.getString("video");
                            String name = d.getString("name");

                            arrayList.add(new CourseChildModel(d.getId(),name,video));

                        }
                        CourseChildAdapter courseAdapter = new CourseChildAdapter(CourseDetailActivity.this,arrayList);
                        activityCourseBinding.rcvCourses.setLayoutManager(new LinearLayoutManager(CourseDetailActivity.this));
                        activityCourseBinding.rcvCourses.setAdapter(courseAdapter);


                    }
                });
    }

    private boolean onNavigationItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.bottom_home) {
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            finish();
            return true;
        } else if (itemId == R.id.bottom_account) {
            startActivity(new Intent(getApplicationContext(), AccountActivity.class));
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            finish();
            return true;
        } else if (itemId == R.id.bottom_game) {
            startActivity(new Intent(getApplicationContext(), GameActivity.class));
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            finish();
            return true;
        } else if (itemId == R.id.bottom_tool) {
            startActivity(new Intent(getApplicationContext(), ToolActivity.class));
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            finish();
            return true;
        } else return itemId == R.id.bottom_course;
    }
}