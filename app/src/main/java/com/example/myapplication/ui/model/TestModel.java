package com.example.myapplication.ui.model;

import java.io.Serializable;

public class TestModel implements Serializable {
    private  String id;
    private  String title;
    private  String linktest;
    private  String linkanswer;
    private  long time;
    private  String score;

    public TestModel(String id, String title, String linktest, String linkanswer, long time, String score) {
        this.id = id;
        this.title=title;
        this.linktest = linktest;
        this.linkanswer = linkanswer;
        this.time = time;
        this.score = score;
    }

    public String getTitle() {
        return title;
    }

    public TestModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLinktest() {
        return linktest;
    }

    public void setLinktest(String linktest) {
        this.linktest = linktest;
    }

    public String getLinkanswer() {
        return linkanswer;
    }

    public void setLinkanswer(String linkanswer) {
        this.linkanswer = linkanswer;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
