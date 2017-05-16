package com.example.danfa.myapplication;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class IssuesAdapter extends RecyclerView.Adapter<IssuesAdapter.ViewHolder> {

    private ArrayList<Issue> issue;
    private Context context;

    public IssuesAdapter(ArrayList<Issue> issue, Context context){
        this.issue = issue;
        this.context = context;
    }


    @Override
    public IssuesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(issue.get(position).getName());
        holder.description.setText(issue.get(position).getDescription());
        holder.issuesCount.setText(String.valueOf(issue.get(position).getIssues_count()));
        Picasso.with(context).load(issue.get(position).getImageUrl()).into(holder.imageUrl);



    }

    @Override
    public int getItemCount() {
        return issue.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name, description, issuesCount;
        private ImageView imageUrl;

        public ViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.name);
            description = (TextView) itemView.findViewById(R.id.description);
            issuesCount = (TextView) itemView.findViewById(R.id.open_issues_count);
            imageUrl = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }
}
