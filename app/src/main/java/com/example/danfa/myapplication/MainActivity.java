package com.example.danfa.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{


    /**
     * Homework:
     *
     *  -  show all repositories in recycler view + check if api has photo url, if so display using picasso
     *  -  add search and change api accordingly
     *
     * @param savedInstanceState
     */


    private RecyclerView recyclerView;
    private ArrayList<Issue> issues = new ArrayList<>();
    private IssuesAdapter adapter;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView = (SearchView) findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(this);


        initViews();
    }

    private void initViews(){
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        loadJSON();
    }

    private void loadJSON(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RedditAPI redditAPI = retrofit.create(RedditAPI.class);

        redditAPI.getChannelData().enqueue(new Callback<List<Data>>() {
            @Override
            public void onResponse(Call<List<Data>> call, Response<List<Data>> response) {
                Log.d("a", "got response!");
                List<Data> body = response.body();

                //issues = new ArrayList<>(Arrays.asList(body.));
                for (int i = 0 ; i < body.size() ; i++){
                    issues.add(new Issue(body.get(i).name, body.get(i).description, body.get(i).openIssuesCount, body.get(i).owner.avatarUrl));
                }
                adapter = new IssuesAdapter(issues, getBaseContext());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Data>> call, Throwable t) {

                Log.e("a", "got error!");
            }
        });
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        filterTopics(query);

        return false;
    }
//
    @Override
    public boolean onQueryTextChange(String newText) {
        filterTopics(newText);

        return false;
    }

    public ArrayList<Issue> filterTopics(String searchWord) {
        ArrayList<Issue> filteredList = new ArrayList<>();
        if (searchWord.equalsIgnoreCase("")){
            filteredList =  this.issues;
        } else {
            Issue currentTopic;
            String currentTopicTitle;
            for (int i = 0 ; i < this.issues.size() ; i++){
                currentTopic = this.issues.get(i);
                currentTopicTitle = currentTopic.getName();
                if (currentTopicTitle.toLowerCase().contains(searchWord.toLowerCase())){
                    filteredList.add(currentTopic);

                }
            }
        }
        bindTopicsToRecyclerView(filteredList);
        return filteredList;


    }

    public void bindTopicsToRecyclerView(ArrayList<Issue> filteredTopics){
        adapter = new IssuesAdapter(filteredTopics, getBaseContext());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
