package com.sidchiku9.retrofit2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;

    private JsonPlaceHolderAPI jsonPlaceHolderAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = findViewById(R.id.postView);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);

        //getPosts();

        getComments();
    }

    private void getPosts(){

        Map<String, String> parameters = new HashMap<>();
        parameters.put("usedId","1");
        parameters.put("sort","id");
        parameters.put("order","desc");

        Call<List<Posts>> call = jsonPlaceHolderAPI.getPosts(parameters);

        call.enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
                if(!response.isSuccessful()){
                    mTextView.setText("Code : " + response.code());
                    return;
                }

                List<Posts> posts = response.body();

                for(Posts post : posts){
                    String content = "";

                    content += "USER ID : " + post.getUserid() + "\n" + "ID : " + post.getId() + "\n" +
                            "TITLE : " + post.getTitle() + "\n" + "BODY : " + post.getBody() + "\n" + "\n";

                    mTextView.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {
                mTextView.setText(t.getMessage());
            }
        });
    }

    private void getComments(){
        Call<List<Comments>> call = jsonPlaceHolderAPI.getComments("posts/3/comments");

        call.enqueue(new Callback<List<Comments>>() {
            @Override
            public void onResponse(Call<List<Comments>> call, Response<List<Comments>> response) {

                if(!response.isSuccessful()){
                    mTextView.setText("Code : " + response.code());
                    return;
                }

                List<Comments> comments = response.body();

                for(Comments comment : comments){
                    String content = "";

                    content += "POST ID : " + comment.getPostId() + "\n" +
                            "ID : " + comment.getId() + "\n" +
                            "NAME : " + comment.getName() + "\n" +
                            "EMAIL : " + comment.getEmail() + "\n" +
                            "BODY : " + comment.getBody() + "\n";

                    mTextView.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Comments>> call, Throwable t) {
                mTextView.setText(t.getMessage());
            }
        });
    }
}
