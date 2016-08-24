package com.example.nikhilsridhar.database;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by nikhilsridhar on 12/08/16.
 */
public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


    ImageView img;
    TextView nm, ps;
    ArrayList<Player> players = new ArrayList<Player>();
    Context ctx;

    public MyHolder(View itemView, Context ctx, ArrayList<Player> players) {
        super(itemView);

        this.players = players;
        this.ctx = ctx;
        this.img = (ImageView) itemView.findViewById(R.id.playerImage);
        this.nm = (TextView) itemView.findViewById(R.id.nameTxt);
        this.ps = (TextView) itemView.findViewById(R.id.posTxt);
        itemView.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

       int position = getAdapterPosition();
        Player player = this.players.get(position);
        Intent intent = new Intent(ctx, Details.class);
        intent.putExtra("img_id", player.getImg());
        intent.putExtra("nm", player.getName());
        intent.putExtra("pos", player.getPos());
        this.ctx.startActivity(intent);
    }

}

