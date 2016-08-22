package com.example.nikhilsridhar.database;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;


public class CameraFragment extends Fragment {

    public View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        setHasOptionsMenu(true);
        final View view = inflater.inflate(R.layout.fragment_camera, container, false);
        return view;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.popup, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


    public void PopupMenu(MenuItem menuItem) {
        PopupMenu popup = new PopupMenu(getContext(), view);
        popup.getMenuInflater().inflate(R.menu.popup, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(getContext(), "You selected the action : " + item.getTitle(), Toast.LENGTH_SHORT).show();
                return true;


            }
        });
        popup.show();
    }



}









 /*   @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.popup_gallery:
                Toast.makeText(getContext(), "Gallery", Toast.LENGTH_SHORT).show();
                break;
            case R.id.popup_cam:
                Toast.makeText(getContext(), "Camera", Toast.LENGTH_SHORT).show();
                break;
            case R.id.popup_remove:
                Toast.makeText(getContext(), "Gallery", Toast.LENGTH_SHORT).show();
                break;
        }
        return true; */


