package com.example.nikhilsridhar.database;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;

import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.logging.Handler;
import java.util.zip.Inflater;

public  class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {


    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    Toolbar toolbar;
    ImageButton changeBg;
    private static final int PICK_IMAGE = 100;
    ImageView img1;
    Uri imageUri;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    SearchView sv;
    FragmentTransaction fragmentTransaction;
    public static ArrayList<Player> players;
    public static MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Transition exitTrans = new Explode();
        getWindow().setExitTransition(exitTrans);

        Transition reenterTrans = new Explode();
        getWindow().setReenterTransition(reenterTrans);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        players = new ArrayList<>();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sv = (SearchView) findViewById(R.id.mSearch);
        RecyclerView rv = (RecyclerView) findViewById(R.id.myRecycler);
        rv.setHasFixedSize(false);

        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(new DefaultItemAnimator());

        adapter = new MyAdapter(this, getPlayers());
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

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                switch(item.getItemId()){
                    case R.id.edit_profile:
                        Intent a = new Intent(MainActivity.this, EditProfile.class);
                        startActivity(a);
                        break;

                    case R.id.change_pass:
                        Intent b = new Intent(MainActivity.this, ChangePassword.class);
                        startActivity(b);
                        break;

                    case R.id.delete:
                    {
                        deleteProfile();
                        break;
                    }
                    case R.id.sign_out:
                        signOut();
                        break;
                }

                return false;
            }
        });
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override

    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    private ArrayList<Player> getPlayers() {

        for (int i = 0; i < 15; i++) {
            Player p = new Player();
            p.setName("Employee" + i);
            p.setPos("Desig" + i);
            p.setImg(R.drawable.defaultemp);
            players.add(p);
        }
        return players;
    }


    public void showPopUp(View view){
        PopupMenu popup = new PopupMenu(this, view);
        popup.setOnMenuItemClickListener(this);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.popup, popup.getMenu());
        popup.show();
    }

    public void deleteProfile(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Confirm");
        builder.setMessage("Are you sure?");

        builder.setPositiveButton("NO", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }

        });

        builder.setNegativeButton("YES", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_SHORT).show();
                Intent b = new Intent(MainActivity.this, Login.class);
                startActivity(b);
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

        public void signOut(){
            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
            alertDialog.setMessage("Signing out...");
            alertDialog.show();

            Thread t = new Thread(){
            @Override
            public void run(){
                try {
                    sleep(2000);
                    Intent startMainScreen = new Intent(getApplicationContext(), Login.class);
                    startActivity(startMainScreen);
                    finish();
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            };
            t.start();
            }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch(item.getItemId()){
            case R.id.popup_gallery:
                openGallery();
                break;
            case R.id.popup_cam:
                dispatchTakePictureIntent();
                break;
            case R.id.popup_remove:
                removeImage();
                break;

        }
        return false;
    }

    private void removeImage(){
        img1 = (ImageView) findViewById(R.id.img_replace);
        img1.setImageResource(R.drawable.def);
    }



    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    private void openGallery(){
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);

    }
    @Override

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        img1 = (ImageView) findViewById(R.id.img_replace);
;                super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == PICK_IMAGE ){
            imageUri = data.getData();
            img1.setImageURI(imageUri);
        }
        else  if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            img1.setImageBitmap(imageBitmap);
        }
    }
}













