package com.example.myapplication.ui.model;

import java.io.Serializable;

public class CourseLessonModel implements Serializable {
    private  String id;
    private  String name;
    private  String video;

    public CourseLessonModel(String id, String name, String video) {
        this.id=id;

        this.name = name;
        this.video=video;
    }

    public String getVideo() {
        return video;
    }

    public String getId() {
        return id;
    }

    public CourseLessonModel() {

    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
