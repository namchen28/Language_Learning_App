package com.example.myapplication.ui.model;

import java.io.Serializable;

public class CoursesModel  implements Serializable {
    private  String id;
    private  String imgBg;
    private  String subtitle;
    private String name;

    public CoursesModel(String id,String imgBg, String subtitle, String name) {
        this.id=id;
        this.imgBg = imgBg;
        this.subtitle = subtitle;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public CoursesModel() {

    }

    public String getImgBg() {
        return imgBg;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
