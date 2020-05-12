package com.sidchiku9.retrofit2;

import com.google.gson.annotations.SerializedName;

public class Comments {
    private int postId;
    private int id;
    private String name;
    private String email;

    @SerializedName("body")
    private String body;

    public int getPostId() {
        return postId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getBody() {
        return body;
    }
}
