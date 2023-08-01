package com.example.myapplication.ui.test;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentTestContentBinding;
import com.example.myapplication.ui.HomeActivity;
import com.example.myapplication.ui.model.TestModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class Fragment_test_content extends Fragment {

    View view;
    List<String> answerList =  new ArrayList<>();
    List<Double> scoreList =  new ArrayList<>();
    double score = 0;
    FragmentTestContentBinding fragmentTestContentBinding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_test_content,container,false);
        fragmentTestContentBinding = FragmentTestContentBinding.bind(view);
        TestModel testModel = (TestModel) getActivity().getIntent().getSerializableExtra("TEST");
        for(String s : testModel.getScore().toString().split(",")){
            answerList.add(s);
            scoreList.add(0.0);
        }

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                 if(TestActivity.checked==1){
                     showDialogResult(testModel);
                     TestActivity.checked=0;
                 }
                handler.postDelayed(this::run,1000);
            }
        },1000);

        initResult();
        return  view;
    }

    private void showDialogResult(TestModel testModel) {
        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_result);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        TextView txtPoint = dialog.findViewById(R.id.txtPoint);
        score = 0;
        for(double allSum  : scoreList){
            score +=allSum;
        }
        txtPoint.setText(String.format("%.2f", score));
        dialog.findViewById(R.id.btnHome).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ListTestActivity.class));
                getActivity().finish();
            }
        });
        dialog.findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                HashMap<String,Object> hashMap = new HashMap<>();
                hashMap.put("title",testModel.getTitle());
                hashMap.put("point", score);
                hashMap.put("time",testModel.getTime());
                hashMap.put("date",simpleDateFormat.format(Calendar.getInstance().getTime()));
                FirebaseFirestore.getInstance().collection("History")
                        .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .collection("MyHistory")
                        .add(hashMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentReference> task) {
                              if(task.isSuccessful()){
                                  startActivity(new Intent(getContext(),HomeActivity.class));
                                  getActivity().finish();
                              }
                            }
                        });
            }
        });
        
    }

    private void initResult() {
        fragmentTestContentBinding.r1A.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(answerList.get(0).equalsIgnoreCase("a")){
                        scoreList.set(0,0.5);
                    }else{
                        scoreList.set(0,0.0);
                    }
                }
            }
        });
        fragmentTestContentBinding.r1B.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(answerList.get(0).equalsIgnoreCase("b")){
                        scoreList.set(0,0.5);
                    }else{
                        scoreList.set(0,0.0);
                    }
                }
            }
        });
        fragmentTestContentBinding.r1C.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(answerList.get(0).equalsIgnoreCase("c")){
                        scoreList.set(0,0.5);
                    }else{
                        scoreList.set(0,0.0);
                    }
                }
            }
        });
        fragmentTestContentBinding.r1D.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(answerList.get(0).equalsIgnoreCase("d")){
                        scoreList.set(0,0.5);
                    }else{
                        scoreList.set(0,0.0);
                    }
                }
            }
        });

        fragmentTestContentBinding.r2A.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(answerList.get(1).equalsIgnoreCase("a")){
                        scoreList.set(1,0.5);
                    }else{
                        scoreList.set(1,0.0);
                    }
                }
            }
        });
        fragmentTestContentBinding.r2B.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(answerList.get(1).equalsIgnoreCase("b")){
                        scoreList.set(1,0.5);
                    }else{
                        scoreList.set(1,0.0);
                    }
                }
            }
        });
        fragmentTestContentBinding.r2C.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(answerList.get(1).equalsIgnoreCase("c")){
                        scoreList.set(1,0.5);
                    }else{
                        scoreList.set(1,0.0);
                    }
                }
            }
        });
        fragmentTestContentBinding.r2D.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(answerList.get(1).equalsIgnoreCase("d")){
                        scoreList.set(1,0.5);
                    }else{
                        scoreList.set(1,0.0);
                    }
                }
            }
        });

        fragmentTestContentBinding.r3A.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(answerList.get(2).equalsIgnoreCase("a")){
                        scoreList.set(2,0.5);
                    }else{
                        scoreList.set(2,0.0);
                    }
                }
            }
        });
        fragmentTestContentBinding.r3B.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(answerList.get(2).equalsIgnoreCase("b")){
                        scoreList.set(2,0.5);
                    }else{
                        scoreList.set(2,0.0);
                    }
                }
            }
        });
        fragmentTestContentBinding.r3C.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(answerList.get(2).equalsIgnoreCase("c")){
                        scoreList.set(2,0.5);
                    }else{
                        scoreList.set(2,0.0);
                    }
                }
            }
        });
        fragmentTestContentBinding.r3D.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(answerList.get(2).equalsIgnoreCase("d")){
                        scoreList.set(2,0.5);
                    }else{
                        scoreList.set(2,0.0);
                    }
                }
            }
        });

        fragmentTestContentBinding.r4A.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(answerList.get(3).equalsIgnoreCase("a")){
                        scoreList.set(3,0.5);
                    }else{
                        scoreList.set(3,0.0);
                    }
                }
            }
        });
        fragmentTestContentBinding.r4B.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(answerList.get(3).equalsIgnoreCase("b")){
                        scoreList.set(3,0.5);
                    }else{
                        scoreList.set(3,0.0);
                    }
                }
            }
        });
        fragmentTestContentBinding.r4C.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(answerList.get(3).equalsIgnoreCase("c")){
                        scoreList.set(3,0.5);
                    }else{
                        scoreList.set(3,0.0);
                    }
                }
            }
        });
        fragmentTestContentBinding.r4D.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(answerList.get(3).equalsIgnoreCase("d")){
                        scoreList.set(3,0.5);
                    }else{
                        scoreList.set(3,0.0);
                    }
                }
            }
        });

        fragmentTestContentBinding.r5A.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(answerList.get(4).equalsIgnoreCase("a")){
                        scoreList.set(4,0.5);
                    }else{
                        scoreList.set(4,0.0);
                    }
                }
            }
        });
        fragmentTestContentBinding.r5B.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(answerList.get(4).equalsIgnoreCase("b")){
                        scoreList.set(4,0.5);
                    }else{
                        scoreList.set(4,0.0);
                    }
                }
            }
        });
        fragmentTestContentBinding.r5C.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(answerList.get(4).equalsIgnoreCase("c")){
                        scoreList.set(4,0.5);
                    }else{
                        scoreList.set(4,0.0);
                    }
                }
            }
        });
        fragmentTestContentBinding.r5D.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(answerList.get(4).equalsIgnoreCase("d")){
                        scoreList.set(4,0.5);
                    }else{
                        scoreList.set(4,0.0);
                    }
                }
            }
        });

        fragmentTestContentBinding.r6A.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(answerList.get(5).equalsIgnoreCase("a")){
                        scoreList.set(5,0.5);
                    }else{
                        scoreList.set(5,0.0);
                    }
                }
            }
        });
        fragmentTestContentBinding.r6B.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(answerList.get(5).equalsIgnoreCase("b")){
                        scoreList.set(5,0.5);
                    }else{
                        scoreList.set(5,0.0);
                    }
                }
            }
        });
        fragmentTestContentBinding.r6C.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(answerList.get(5).equalsIgnoreCase("c")){
                        scoreList.set(5,0.5);
                    }else{
                        scoreList.set(5,0.0);
                    }
                }
            }
        });
        fragmentTestContentBinding.r6D.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(answerList.get(5).equalsIgnoreCase("d")){
                        scoreList.set(5,0.5);
                    }else{
                        scoreList.set(5,0.0);
                    }
                }
            }
        });

        fragmentTestContentBinding.r7A.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(answerList.get(6).equalsIgnoreCase("a")){
                        scoreList.set(6,0.5);
                    }else{
                        scoreList.set(6,0.0);
                    }
                }
            }
        });
        fragmentTestContentBinding.r7B.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(answerList.get(6).equalsIgnoreCase("b")){
                        scoreList.set(6,0.5);
                    }else{
                        scoreList.set(6,0.0);
                    }
                }
            }
        });
        fragmentTestContentBinding.r7C.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(answerList.get(6).equalsIgnoreCase("c")){
                        scoreList.set(6,0.5);
                    }else{
                        scoreList.set(6,0.0);
                    }
                }
            }
        });
        fragmentTestContentBinding.r7D.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(answerList.get(6).equalsIgnoreCase("d")){
                        scoreList.set(6,0.5);
                    }else{
                        scoreList.set(6,0.0);
                    }
                }
            }
        });

        fragmentTestContentBinding.r8A.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(answerList.get(7).equalsIgnoreCase("a")){
                        scoreList.set(7,0.5);
                    }else{
                        scoreList.set(7,0.0);
                    }
                }
            }
        });
        fragmentTestContentBinding.r8B.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(answerList.get(7).equalsIgnoreCase("b")){
                        scoreList.set(7,0.5);
                    }else{
                        scoreList.set(7,0.0);
                    }
                }
            }
        });
        fragmentTestContentBinding.r8C.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(answerList.get(7).equalsIgnoreCase("c")){
                        scoreList.set(7,0.5);
                    }else{
                        scoreList.set(7,0.0);
                    }
                }
            }
        });
        fragmentTestContentBinding.r8D.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(answerList.get(7).equalsIgnoreCase("d")){
                        scoreList.set(7,0.5);
                    }else{
                        scoreList.set(7,0.0);
                    }
                }
            }
        });

        fragmentTestContentBinding.r9A.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(answerList.get(8).equalsIgnoreCase("a")){
                        scoreList.set(8,0.5);
                    }else{
                        scoreList.set(8,0.0);
                    }
                }
            }
        });
        fragmentTestContentBinding.r9B.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(answerList.get(8).equalsIgnoreCase("b")){
                        scoreList.set(8,0.5);
                    }else{
                        scoreList.set(8,0.0);
                    }
                }
            }
        });
        fragmentTestContentBinding.r9C.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(answerList.get(8).equalsIgnoreCase("c")){
                        scoreList.set(8,0.5);
                    }else{
                        scoreList.set(8,0.0);
                    }
                }
            }
        });
        fragmentTestContentBinding.r9D.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(answerList.get(8).equalsIgnoreCase("d")){
                        scoreList.set(8,0.5);
                    }else{
                        scoreList.set(8,0.0);
                    }
                }
            }
        });

        fragmentTestContentBinding.r10A.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(answerList.get(9).equalsIgnoreCase("a")){
                        scoreList.set(9,0.5);
                    }else{
                        scoreList.set(9,0.0);
                    }
                }
            }
        });
        fragmentTestContentBinding.r10B.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(answerList.get(9).equalsIgnoreCase("b")){
                        scoreList.set(9,0.5);
                    }else{
                        scoreList.set(9,0.0);
                    }
                }
            }
        });
        fragmentTestContentBinding.r10C.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(answerList.get(9).equalsIgnoreCase("c")){
                        scoreList.set(9,0.5);
                    }else{
                        scoreList.set(9,0.0);
                    }
                }
            }
        });
        fragmentTestContentBinding.r10D.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(answerList.get(9).equalsIgnoreCase("d")){
                        scoreList.set(9,0.5);
                    }else{
                        scoreList.set(9,0.0);
                    }
                }
            }
        });

        fragmentTestContentBinding.r11A.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(answerList.get(10).equalsIgnoreCase("a")){
                        scoreList.set(10,0.5);
                    }else{
                        scoreList.set(10,0.0);
                    }
                }
            }
        });
        fragmentTestContentBinding.r11B.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(answerList.get(10).equalsIgnoreCase("b")){
                        scoreList.set(10,0.5);
                    }else{
                        scoreList.set(10,0.0);
                    }
                }
            }
        });
        fragmentTestContentBinding.r11C.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(answerList.get(10).equalsIgnoreCase("c")){
                        scoreList.set(10,0.5);
                    }else{
                        scoreList.set(10,0.0);
                    }
                }
            }
        });
        fragmentTestContentBinding.r11D.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(answerList.get(10).equalsIgnoreCase("d")){
                        scoreList.set(10,0.5);
                    }else{
                        scoreList.set(10,0.0);
                    }
                }
            }
        });

        fragmentTestContentBinding.r12A.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(answerList.get(11).equalsIgnoreCase("a")){
                        scoreList.set(11,0.5);
                    }else{
                        scoreList.set(11,0.0);
                    }
                }
            }
        });
        fragmentTestContentBinding.r12B.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(answerList.get(11).equalsIgnoreCase("b")){
                        scoreList.set(11,0.5);
                    }else{
                        scoreList.set(11,0.0);
                    }
                }
            }
        });
        fragmentTestContentBinding.r12C.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(answerList.get(11).equalsIgnoreCase("c")){
                        scoreList.set(11,0.5);
                    }else{
                        scoreList.set(11,0.0);
                    }
                }
            }
        });
        fragmentTestContentBinding.r12D.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(answerList.get(11).equalsIgnoreCase("d")){
                        scoreList.set(11,0.5);
                    }else{
                        scoreList.set(11,0.0);
                    }
                }
            }
        });

        fragmentTestContentBinding.r13A.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(answerList.get(12).equalsIgnoreCase("a")){
                        scoreList.set(12,0.5);
                    }else{
                        scoreList.set(12,0.0);
                    }
                }
            }
        });
        fragmentTestContentBinding.r13B.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(answerList.get(12).equalsIgnoreCase("b")){
                        scoreList.set(12,0.5);
                    }else{
                        scoreList.set(12,0.0);
                    }
                }
            }
        });
        fragmentTestContentBinding.r13C.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(answerList.get(12).equalsIgnoreCase("c")){
                        scoreList.set(12,0.5);
                    }else{
                        scoreList.set(12,0.0);
                    }
                }
            }
        });
        fragmentTestContentBinding.r13D.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(answerList.get(12).equalsIgnoreCase("d")){
                        scoreList.set(12,0.5);
                    }else{
                        scoreList.set(12,0.0);
                    }
                }
            }
        });

        fragmentTestContentBinding.r14A.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(answerList.get(13).equalsIgnoreCase("a")){
                        scoreList.set(13,0.5);
                    }else{
                        scoreList.set(13,0.0);
                    }
                }
            }
        });
        fragmentTestContentBinding.r14B.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(answerList.get(13).equalsIgnoreCase("b")){
                        scoreList.set(13,0.5);
                    }else{
                        scoreList.set(13,0.0);
                    }
                }
            }
        });
        fragmentTestContentBinding.r14C.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(answerList.get(13).equalsIgnoreCase("c")){
                        scoreList.set(13,0.5);
                    }else{
                        scoreList.set(13,0.0);
                    }
                }
            }
        });
        fragmentTestContentBinding.r14D.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(answerList.get(13).equalsIgnoreCase("d")){
                        scoreList.set(13,0.5);
                    }else{
                        scoreList.set(13,0.0);
                    }
                }
            }
        });

        fragmentTestContentBinding.r15A.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(answerList.get(14).equalsIgnoreCase("a")){
                        scoreList.set(14,0.5);
                    }else{
                        scoreList.set(14,0.0);
                    }
                }
            }
        });
        fragmentTestContentBinding.r15B.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(answerList.get(14).equalsIgnoreCase("b")){
                        scoreList.set(14,0.5);
                    }else{
                        scoreList.set(14,0.0);
                    }
                }
            }
        });
        fragmentTestContentBinding.r15C.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(answerList.get(14).equalsIgnoreCase("c")){
                        scoreList.set(14,0.5);
                    }else{
                        scoreList.set(14,0.0);
                    }
                }
            }
        });
        fragmentTestContentBinding.r15D.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(answerList.get(14).equalsIgnoreCase("d")){
                        scoreList.set(14,0.5);
                    }else{
                        scoreList.set(14,0.0);
                    }
                }
            }
        });

        fragmentTestContentBinding.r16A.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(answerList.get(15).equalsIgnoreCase("a")){
                        scoreList.set(15,0.5);
                    }else{
                        scoreList.set(15,0.0);
                    }
                }
            }
        });
        fragmentTestContentBinding.r16B.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(answerList.get(15).equalsIgnoreCase("b")){
                        scoreList.set(15,0.5);
                    }else{
                        scoreList.set(15,0.0);
                    }
                }
            }
        });
        fragmentTestContentBinding.r16C.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(answerList.get(15).equalsIgnoreCase("c")){
                        scoreList.set(15,0.5);
                    }else{
                        scoreList.set(15,0.0);
                    }
                }
            }
        });
        fragmentTestContentBinding.r16D.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(answerList.get(15).equalsIgnoreCase("d")){
                        scoreList.set(15,0.5);
                    }else{
                        scoreList.set(15,0.0);
                    }
                }
            }
        });

        fragmentTestContentBinding.r17A.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(answerList.get(16).equalsIgnoreCase("a")){
                        scoreList.set(16,0.5);
                    }else{
                        scoreList.set(16,0.0);
                    }
                }
            }
        });
        fragmentTestContentBinding.r17B.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(answerList.get(16).equalsIgnoreCase("b")){
                        scoreList.set(16,0.5);
                    }else{
                        scoreList.set(16,0.0);
                    }
                }
            }
        });
        fragmentTestContentBinding.r17C.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(answerList.get(16).equalsIgnoreCase("c")){
                        scoreList.set(16,0.5);
                    }else{
                        scoreList.set(16,0.0);
                    }
                }
            }
        });
        fragmentTestContentBinding.r17D.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(answerList.get(16).equalsIgnoreCase("d")){
                        scoreList.set(16,0.5);
                    }else{
                        scoreList.set(16,0.0);
                    }
                }
            }
        });

        fragmentTestContentBinding.r18A.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(answerList.get(17).equalsIgnoreCase("a")){
                        scoreList.set(17,0.5);
                    }else{
                        scoreList.set(17,0.0);
                    }
                }
            }
        });
        fragmentTestContentBinding.r18B.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(answerList.get(17).equalsIgnoreCase("b")){
                        scoreList.set(17,0.5);
                    }else{
                        scoreList.set(17,0.0);
                    }
                }
            }
        });
        fragmentTestContentBinding.r18C.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(answerList.get(17).equalsIgnoreCase("c")){
                        scoreList.set(17,0.5);
                    }else{
                        scoreList.set(17,0.0);
                    }
                }
            }
        });
        fragmentTestContentBinding.r18D.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(answerList.get(17).equalsIgnoreCase("d")){
                        scoreList.set(17,0.5);
                    }else{
                        scoreList.set(17,0.0);
                    }
                }
            }
        });

        fragmentTestContentBinding.r19A.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(answerList.get(18).equalsIgnoreCase("a")){
                        scoreList.set(18,0.5);
                    }else{
                        scoreList.set(18,0.0);
                    }
                }
            }
        });
        fragmentTestContentBinding.r19B.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(answerList.get(18).equalsIgnoreCase("b")){
                        scoreList.set(18,0.5);
                    }else{
                        scoreList.set(18,0.0);
                    }
                }
            }
        });
        fragmentTestContentBinding.r19C.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(answerList.get(18).equalsIgnoreCase("c")){
                        scoreList.set(18,0.5);
                    }else{
                        scoreList.set(18,0.0);
                    }
                }
            }
        });
        fragmentTestContentBinding.r19D.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(answerList.get(18).equalsIgnoreCase("d")){
                        scoreList.set(18,0.5);
                    }else{
                        scoreList.set(18,0.0);
                    }
                }
            }
        });

        fragmentTestContentBinding.r20A.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(answerList.get(19).equalsIgnoreCase("a")){
                        scoreList.set(19,0.5);
                    }else{
                        scoreList.set(19,0.0);
                    }
                }
            }
        });
        fragmentTestContentBinding.r20B.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(answerList.get(19).equalsIgnoreCase("b")){
                        scoreList.set(19,0.5);
                    }else{
                        scoreList.set(19,0.0);
                    }
                }
            }
        });
        fragmentTestContentBinding.r20C.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(answerList.get(19).equalsIgnoreCase("c")){
                        scoreList.set(19,0.5);
                    }else{
                        scoreList.set(19,0.0);
                    }
                }
            }
        });
        fragmentTestContentBinding.r20D.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(answerList.get(19).equalsIgnoreCase("d")){
                        scoreList.set(19,0.5);
                    }else{
                        scoreList.set(19,0.0);
                    }
                }
            }
        });


    }
}
