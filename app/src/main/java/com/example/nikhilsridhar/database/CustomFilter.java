package com.example.nikhilsridhar.database;

import android.widget.Filter;

import java.util.ArrayList;

/**
 * Created by nikhilsridhar on 12/08/16.
 */
public class CustomFilter extends Filter {


    MyAdapter adapter;
    ArrayList<Player> filterList;




    public CustomFilter(ArrayList<Player> filterList, MyAdapter adapter){

        this.adapter=adapter;
        this.filterList=filterList;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {

        FilterResults results = new FilterResults();
        if(constraint !=null && constraint.length() > 0){

            constraint = constraint.toString().toUpperCase();
            ArrayList<Player> filteredPlayers = new ArrayList<>();
            for(int i=0; i < filterList.size();i++ ){

                if(filterList.get(i).getName().toUpperCase().contains(constraint)){

                    filteredPlayers.add(filterList.get(i));
                }
            }
            results.count = filteredPlayers.size();
            results.values= filteredPlayers;

        }else{
            results.count = filterList.size();
            results.values = filterList;
        }
        return results;
    }

    @Override
    protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

        adapter.players = (ArrayList<Player>) filterResults.values;
        adapter.notifyDataSetChanged();

    }
}