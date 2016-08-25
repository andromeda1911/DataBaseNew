package com.example.nikhilsridhar.database;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;

/**
 * Created by nikhilsridhar on 12/08/16.
 */
public class MyAdapter extends RecyclerView.Adapter<MyHolder> implements Filterable{

    Context ctx;
    ArrayList<Player> players, filterList;
    CustomFilter filter;

    public MyAdapter(Context ctx, ArrayList<Player> players){
        this.ctx=ctx;
        this.players=players;
        this.filterList = players;

    }


    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.model, parent, false);

        MyHolder holder = new MyHolder(v, ctx, players);

        return holder;

    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {

        holder.ps.setText(players.get(position).getPos());
        holder.nm.setText(players.get(position).getName());
        holder.img.setImageResource(players.get(position).getImg());





    }

    @Override
    public int getItemCount() {

        return players.size();
    }

    @Override
    public Filter getFilter() {
        if(filter==null){
            filter = new CustomFilter(filterList, this);

        }
        return filter;
    }
}
