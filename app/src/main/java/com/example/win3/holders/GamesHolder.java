package com.example.win3.holders;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.win3.InfoGamesActivity;
import com.example.win3.R;
import com.example.win3.model.Game;

import java.util.ArrayList;
import java.util.List;

public class GamesHolder extends RecyclerView.Adapter<GamesHolder.ViewHolder>{


    private final LayoutInflater inflater;
    private final List<Game> gameList;
    private final Context context;

    public GamesHolder(Context context, ArrayList<Game> gameList) {
        this.inflater = LayoutInflater.from(context);;
        this.gameList = gameList;
        this.context = context;
    }

    @NonNull
    @Override
    public GamesHolder.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GamesHolder.ViewHolder holder, int position) {
        holder.textViewTitle.setText(gameList.get(position).title);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("HOLDERGAMES", gameList.get(holder.getAdapterPosition()).title);
                Intent intent = new Intent(context, InfoGamesActivity.class);
                intent.putExtra("title", gameList.get(holder.getAdapterPosition()).title);
                intent.putExtra("description", gameList.get(holder.getAdapterPosition()).description);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return gameList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.item_name);
        }
    }
}
