package com.example.win3.holders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.win3.R;
import com.example.win3.model.UpcomingGame;

import java.util.List;

public class UpcomingHolder extends RecyclerView.Adapter<UpcomingHolder.ViewHolder> {

    private final LayoutInflater inflater;
    private final List<UpcomingGame> upcomingList;
    private final Context context;

    public UpcomingHolder(List<UpcomingGame> upcomingList, Context context) {
        this.inflater = LayoutInflater.from(context);
        this.upcomingList = upcomingList;
        this.context = context;
    }

    @NonNull
    @Override
    public UpcomingHolder.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycler_item_upcoming, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UpcomingHolder.ViewHolder holder, int position) {
        holder.textViewTitle.setText(upcomingList.get(position).title);
        holder.textViewDesc.setText(upcomingList.get(position).description);
        holder.textViewDate.setText(upcomingList.get(position).date);
    }

    @Override
    public int getItemCount() {
        return upcomingList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle, textViewDesc, textViewDate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.title_upcoming);
            textViewDesc = itemView.findViewById(R.id.description_upcoming);
            textViewDate = itemView.findViewById(R.id.date_upcoming);
        }
    }
}
