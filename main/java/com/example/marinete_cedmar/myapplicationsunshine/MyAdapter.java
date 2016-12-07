package com.example.marinete_cedmar.myapplicationsunshine;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

import models.*;

public class MyAdapter extends ArrayAdapter<Produto> {

    ArrayList<Produto> products = new ArrayList<Produto>();

    public MyAdapter(Context context, int textViewResourceId, ArrayList<Produto> objects) {
        super(context, textViewResourceId, objects);
        products = objects;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.presentation_listview_item,null);
        TextView textViewName = (TextView) v.findViewById(R.id.textViewName);
        TextView textViewCategory = (TextView) v.findViewById(R.id.textViewCategory);
        TextView textViewPreco = (TextView) v.findViewById(R.id.textViewPrice);

        textViewName.setText(products.get(position).getNome());
        
        textViewCategory.setText(products.get(position).getCategoria());
        textViewPreco.setText("R$: " + String.valueOf(products.get(position).getPreco()));


        return v;

    }

}