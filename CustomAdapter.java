package com.book.now.app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList event_id, event_name, event_agent, event_code;

    CustomAdapter(Activity activity, Context context, ArrayList event_id, ArrayList event_name, ArrayList event_agent,
                  ArrayList event_code){
        this.activity = activity;
        this.context = context;
        this.event_id = event_id;
        this.event_name = event_name;
        this.event_agent = event_agent;
        this.event_code= event_code;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.event_id_txt.setText(String.valueOf(event_id.get(position)));
        holder.event_name_txt.setText(String.valueOf(event_name.get(position)));
        holder.event_agent_txt.setText(String.valueOf(event_agent.get(position)));
        holder.event_code_txt.setText(String.valueOf(event_code.get(position)));
        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(event_id.get(position)));
                intent.putExtra("name", String.valueOf(event_name.get(position)));
                intent.putExtra("agent", String.valueOf(event_agent.get(position)));
                intent.putExtra("code", String.valueOf(event_code.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });


    }

    @Override
    public int getItemCount() {
        return event_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView event_id_txt, event_name_txt, event_agent_txt, event_code_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            event_id_txt = itemView.findViewById(R.id.event_id_txt);
            event_name_txt = itemView.findViewById(R.id.event_name_txt);
            event_agent_txt = itemView.findViewById(R.id.event_agent_txt);
            event_code_txt = itemView.findViewById(R.id.event_code_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }

    }

}
