package com.cheese.radio.util;

/**
 * Created by 29283 on 2018/4/8.
 */

public class DataStore {
    private static DataStore instance = new DataStore();
    private String image;
    private Integer id;
    private DataStore() {
    }

    public static DataStore getInstance() {
        return instance;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getId() {
        return id=id!=null?id:0;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
