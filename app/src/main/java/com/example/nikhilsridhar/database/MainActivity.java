package com.example.nikhilsridhar.database;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;

import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.zip.Inflater;

public  class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {


    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    FragmentTransaction fragmentTransaction;
    Toolbar toolbar;
    ImageButton changeBg;


    SearchView sv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     /*   changeBg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(MainActivity.this, changeBg);
                popup.getMenuInflater().inflate(R.menu.popup, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(MainActivity.this,"You Clicked : " + item.getTitle(),Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });

                popup.show();
            }
        }); */



        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sv = (SearchView) findViewById(R.id.mSearch);
        RecyclerView rv = (RecyclerView) findViewById(R.id.myRecycler);
        rv.setHasFixedSize(false);

        rv.setLayoutManager(new LinearLayoutManager(this));


        final MyAdapter adapter = new MyAdapter(this, getPlayers());
        rv.setAdapter(adapter);


        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                adapter.getFilter().filter(query);
                return false;
            }
        });



        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);


        navigationView = (NavigationView) findViewById(R.id.navigation_view);



    }

    @Override

    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }


    private ArrayList<Player> getPlayers() {


        ArrayList<Player> players = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            Player p = new Player();
            p.setName("Employee" + i);
            p.setPos("Desig" + i);
            p.setImg(R.drawable.emp);
            players.add(p);
        }


        return players;


    }

   /* @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        changeBg = (ImageButton) findViewById(R.id.changeBg);
        switch(item.getItemId()){
            case R.id.changeBg:

                Toast.makeText(getApplicationContext(), "Photo", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.change_pass:

                Toast.makeText(getApplicationContext(), "Change password", Toast.LENGTH_SHORT).show();
                return true;

            default: return super.onOptionsItemSelected(item);
        }


        } */

    public void showPopUp(View view){
        PopupMenu popup = new PopupMenu(this, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.popup, popup.getMenu());
        popup.show();
    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch(item.getItemId()){
            
        }
        return false;
    }
}













