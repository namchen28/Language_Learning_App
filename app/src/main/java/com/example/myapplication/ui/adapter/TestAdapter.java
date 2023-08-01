package com.example.myapplication.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.ui.interfaces.setOnItemClick;
import com.example.myapplication.ui.model.TestModel;
import com.example.myapplication.ui.test.TestActivity;

import java.util.ArrayList;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.ViewHolder>
{
     private Context context;
     private ArrayList<TestModel> categoryModels;

    public TestAdapter(Context context, ArrayList<TestModel> categoryModels) {
        this.context = context;
        this.categoryModels = categoryModels;
    }



    @NonNull
    @Override
    public TestAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(context).inflate(R.layout.stream_test,parent,false);

        return new ViewHolder(convertView);

    }

    @Override
    public void onBindViewHolder(@NonNull TestAdapter.ViewHolder holder, int position) {

        TestModel categoryModel = categoryModels.get(position);
        holder.txtName.setText(categoryModel.getTitle());
        holder.txtTime.setText(categoryModel.getTime()+" minutes");
        holder.setOnItemClicks(new setOnItemClick() {
            @Override
            public void setOnItemClick(View view, int pos) {
                Intent intent = new Intent(context, TestActivity.class);
                intent.putExtra("TEST",categoryModel);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public long getItemId(int position) {
        return  position;
    }

    @Override
    public int getItemCount() {
        return categoryModels.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {

        TextView txtName,txtTime;
        setOnItemClick setOnItemClick;
        public ViewHolder(@NonNull View convertView) {
            super(convertView);

             txtName = convertView.findViewById(R.id.txtTitle);
             txtTime = convertView.findViewById(R.id.txtTime);
             convertView.setOnClickListener(this);
        }
        public  void  setOnItemClicks(setOnItemClick setOnItemClick){
            this.setOnItemClick = setOnItemClick;
        }

        @Override
        public void onClick(View v) {
            setOnItemClick.setOnItemClick(v,getAdapterPosition());
        }
    }
}
