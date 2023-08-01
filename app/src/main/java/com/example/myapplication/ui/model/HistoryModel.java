package com.example.myapplication.ui.model;

public class HistoryModel {
    private  String id;
    private  String date;
    private  double point;
    private  long time;
    private  String title;

    public HistoryModel(String id, String date, double point, long time, String title) {
        this.id = id;
        this.date = date;
        this.point = point;
        this.time = time;
        this.title = title;
    }

    public HistoryModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getPoint() {
        return point;
    }

    public void setPoint(double point) {
        this.point = point;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
