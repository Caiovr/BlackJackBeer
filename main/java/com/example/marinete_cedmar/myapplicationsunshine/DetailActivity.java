package com.example.marinete_cedmar.myapplicationsunshine;


import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.content.*;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import models.Produto;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent settings = new Intent(this, SettingsActivity.class);
            startActivity(settings);
            return true;
        }
        if (id == R.id.action_refresh){
            Toast.makeText(this, "Dados Atualizados!", Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            int i,position = 0,tam = 9;
            // Vetor com as referencias das imagens
            int [] images = new int[tam];
            images[0] = R.drawable.heineken_image;
            images[1] = R.drawable.budweiser_image;
            images[2] = R.drawable.brahma_image;
            images[3] = R.drawable.stella_image;
            images[4] = R.drawable.eisenbahn_image;
            images[5] = R.drawable.skol_image;
            images[6] = R.drawable.skol_vermelho_image;
            images[7] = R.drawable.skol_azul_image;
            images[8] = R.drawable.skol_verde_image;

            Intent intent = getActivity().getIntent();
            View rootView = inflater.inflate(R.layout.fragment_detail,container,false);
            if (intent != null && intent.hasExtra(Intent.EXTRA_TEXT)){
                Produto product = null;
                String forecastStr = intent.getStringExtra(Intent.EXTRA_TEXT);
                for(i = 0; i < MainActivityFragment.produtos.size(); i++)
                    if (MainActivityFragment.produtos.get(i).getNome().equals(forecastStr)) {
                        position = i;
                        product = MainActivityFragment.produtos.get(i);
                    }


                ((TextView)rootView.findViewById(R.id.textViewDetailName)).setText(String.valueOf(product.getNome()));

                if (product.descricao == null) {
                    ((TextView) rootView.findViewById(R.id.textViewDescription)).setText(String.valueOf(product.getNome()));
                }else{
                    ((TextView) rootView.findViewById(R.id.textViewDescription)).setText(String.valueOf(product.descricao));
                }

                ((TextView)rootView.findViewById(R.id.textViewDetailPrice)).setText("R$: " + String.valueOf((int)product.getPreco())+",00");

                // Pega a imagem referente a posição do vetor de produtos mapeado no vetor de imagens
                if (position < tam)
                    ((ImageView) rootView.findViewById(R.id.imageViewProduct)).setImageResource(images[position]);

                final int posicao = position;
                Button button = (Button) rootView.findViewById(R.id.buttonComprar);
                button.setOnClickListener(
                    new View.OnClickListener() {
                        public void onClick(View v) {
                            if (MainActivity.user.comprar(MainActivityFragment.produtos.get(posicao),1)) {
                                Toast.makeText(getActivity(), "Sucesso!", Toast.LENGTH_LONG).show();
                            }else {
                                Toast.makeText(getActivity(), "Saldo da Carteira insuficiente!", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                );
            }

            return rootView;
        }
    }
}