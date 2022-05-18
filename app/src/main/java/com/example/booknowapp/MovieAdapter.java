package com.example.booknowapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {

    Context context;
    ArrayList<User> list;

    public MovieAdapter(Context context, ArrayList<User> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.items,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        User user = list.get(position);
        holder.theater.setText(user.getTheater());
        holder.movie.setText(user.getMovie());
        holder.date.setText(user.getDate());
        holder.time.setText(user.getTime());
        holder.tickets.setText(user.getTickets());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView theater, movie, date, time, tickets;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            theater = itemView.findViewById(R.id.tvTheater);
            movie = itemView.findViewById(R.id.tvMovie);
            date = itemView.findViewById(R.id.tvDate);
            time = itemView.findViewById(R.id.tvTime);
            tickets = itemView.findViewById(R.id.tvTickets);
        }
    }
}
