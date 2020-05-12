package com.sidchiku9.retrofit2;

import com.google.gson.annotations.SerializedName;

public class Posts {
    private int userId;
    private int id;
    private String title;

    @SerializedName("body")
    private String body;

    public int getUserid() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
}
