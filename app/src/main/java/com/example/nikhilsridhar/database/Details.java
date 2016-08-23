package com.example.nikhilsridhar.database;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class Details extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    Toolbar my_toolbar;
    DrawerLayout drawerLayout;
    ImageView imageView, img2;
    TextView tx_name, tx_pos;
    private static int TAKE_PICTURE = 1;
    private Uri imageUri;
    private static final int DETAILS_PICK_IMAGE = 100;
    static final int DETAILS_REQUEST_IMAGE_CAPTURE = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_details_layout);


        imageView = (ImageView) findViewById(R.id.playerImage);
        tx_name   = (TextView) findViewById(R.id.nameTxt);
        tx_pos    = (TextView) findViewById(R.id.posTxt);
        imageView.setImageResource(getIntent().getIntExtra("img_id", 00));
        tx_name.setText("Name : "+ getIntent().getIntExtra("nm", 00));
        tx_pos.setText("Position"+ getIntent().getIntExtra("ps", 00));

        my_toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(my_toolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    /*    Button cameraButton = (Button) findViewById(R.id.button_camera);
        cameraButton.setOnClickListener(cameraListener); */

    }

   /* private View.OnClickListener cameraListener = new View.OnClickListener() {
        public void onClick(View v) {
            takePhoto(v);
        }
    }; */



  /*  private void takePhoto(View v){
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        File photo = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "picture.jpg");
        imageUri = Uri.fromFile(photo);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, TAKE_PICTURE);
    } */

   /* @Override

    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
        super.onActivityResult(requestCode, resultCode, intent);
        if(resultCode == Activity.RESULT_OK){
            Uri selectedImage = imageUri;
            getContentResolver().notifyChange(selectedImage, null);

            ImageView imageView = (ImageView) findViewById(R.id.playerImage);
            ContentResolver cr = getContentResolver();
            Bitmap bitmap;


            try {
                    bitmap = MediaStore.Images.Media.getBitmap(cr, selectedImage);
                    imageView.setImageBitmap(bitmap);
                Toast.makeText(Details.this, " Photo Taken!", Toast.LENGTH_SHORT).show();
            } catch (Exception e){

            }
        }
    } */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.details_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;

            case R.id.miCompose:
            {
                View menuItemView = findViewById(R.id.miCompose);
                PopupMenu popupMenu = new PopupMenu(this, menuItemView);
                popupMenu.setOnMenuItemClickListener(this);
                popupMenu.inflate(R.menu.details_edit_photo);
                popupMenu.show();
                return true;
            }

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch(item.getItemId()){
            case R.id.details_popup_gallery:
                Details_openGallery();
                break;
            case R.id.details_popup_cam:
                Details_dispatchTakePictureIntent();
                break;
            case R.id.details_popup_remove:
                Details_removeImage();
                break;

        }
        return false;
    }

    private void Details_removeImage(){
        img2 = (ImageView) findViewById(R.id.playerImage);
        img2.setImageResource(R.drawable.def);
    }

    private void Details_dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, DETAILS_REQUEST_IMAGE_CAPTURE);
        }
    }

    private void Details_openGallery(){
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(gallery, DETAILS_PICK_IMAGE);

    }
    @Override

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        img2 = (ImageView) findViewById(R.id.playerImage);
        ;                super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == DETAILS_PICK_IMAGE ){
            imageUri = data.getData();
            img2.setImageURI(imageUri);
        }
        else  if (requestCode == DETAILS_REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            img2.setImageBitmap(imageBitmap);
        }
    }
}
