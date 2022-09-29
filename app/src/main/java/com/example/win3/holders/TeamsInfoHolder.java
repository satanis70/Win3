package com.example.win3.holders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.win3.R;
import com.example.win3.model.Team;

import java.util.List;

public class TeamsInfoHolder extends RecyclerView.Adapter<TeamsInfoHolder.ViewHolder> {

    private final LayoutInflater inflater;
    private final List<Team> teamsList;
    private final Context context;

    public TeamsInfoHolder(List<Team> teamsList, Context context) {
        this.inflater = LayoutInflater.from(context);
        this.teamsList = teamsList;
        this.context = context;
    }

    @NonNull
    @Override
    public TeamsInfoHolder.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycler_item_info_teams, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamsInfoHolder.ViewHolder holder, int position) {
        Glide.with(context)
                .load(teamsList.get(holder.getAdapterPosition()).image)
                .apply(new RequestOptions().override(600, 600))
                .into(holder.imageView);
        holder.textViewNameTeam.setText(teamsList.get(holder.getAdapterPosition()).title);
        holder.textViewDesc.setText(teamsList.get(holder.getAdapterPosition()).description);
    }

    @Override
    public int getItemCount() {
        return teamsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNameTeam, textViewDesc;
        AppCompatImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNameTeam = itemView.findViewById(R.id.text_view_name_team);
            textViewDesc = itemView.findViewById(R.id.text_view_desc_team);
            imageView = itemView.findViewById(R.id.appCompatImageView);
        }
    }
}
