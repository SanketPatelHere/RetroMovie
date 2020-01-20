package com.example.retromovie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.converter.gson.GsonConverterFactory;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    Context context;
    ArrayList<Result> mylst;

    public CustomAdapter(Context context, ArrayList<Result> mylst)
    {
        this.context = context;
        this.mylst = mylst;
    }
    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.clayout, parent, false);
        return new MyViewHolder(v);
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        ImageView imgv;
        TextView tvId, tvDesc, tvReleaseDate;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = (TextView)itemView.findViewById(R.id.tvTitle);
            imgv = (ImageView) itemView.findViewById(R.id.imgv);
            tvId = (TextView)itemView.findViewById(R.id.tvId);
            tvDesc = (TextView)itemView.findViewById(R.id.tvDesc);
            tvReleaseDate = (TextView)itemView.findViewById(R.id.tvReleaseDate);
        }
    }
    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {
        holder.tvTitle.setText(mylst.get(position).getTitle());
        //holder.imgv.setImageResource(mylst.get(position).getBackdropPath()+"");
        //holder.imgv.setImageResource(R.drawable.ic_launcher_background);
        Picasso.get().load(mylst.get(position).getPosterPath()).placeholder(R.drawable.common_google_signin_btn_icon_dark).into(holder.imgv);
        holder.tvId.setText("Id = "+mylst.get(position).getId());
        holder.tvDesc.setText("Desc = "+mylst.get(position).getVoteCount()+" = voting");
        holder.tvReleaseDate.setText("Release Date = "+mylst.get(position).getReleaseDate());
    }

    @Override
    public int getItemCount() {
        return mylst.size();
    }


}
