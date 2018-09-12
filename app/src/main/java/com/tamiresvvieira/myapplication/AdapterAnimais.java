package com.tamiresvvieira.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tamiresvvieira.myapplication.model.Animal;

import java.util.List;

/**
 * Created by android on 11/09/2018.
 */

public class AdapterAnimais extends BaseAdapter {
    private Context contexto;
    private List<Animal> listaDeAnimais;
    private LayoutInflater inflater;
    public AdapterAnimais(Context context, List<Animal> lista){
        this.contexto = context;
        this.listaDeAnimais = lista;
        this.inflater = LayoutInflater.from(context);
    }



    @Override
    public int getCount() {
        return listaDeAnimais.size();
    }

    @Override
    public Object getItem(int i) {
        return listaDeAnimais.get(i);
    }

    @Override
    public long getItemId(int i) {
        return listaDeAnimais.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        Suporte item;

        if ( view == null){
            view = inflater.inflate(R.layout.layout_lista_animais,null);
            item = new Suporte();
            item.fundo = (LinearLayout) view.findViewById(R.id.layout_lista_fundo);
            item.tvNome = (TextView) view.findViewById(R.id.layout_lista_nome_animal);
            item.tvIdade = (TextView) view.findViewById(R.id.layout_lista_idade);
            item.tvEspecie = (TextView) view.findViewById(R.id.layout_lista_Especie);
            item.ivLogo = (ImageView) view.findViewById(R.id.layout_lista_foto);

            view.setTag(item);
        }else {
            item = (Suporte) view.getTag();


        }
        Animal ani = listaDeAnimais.get(i);
        item.tvNome.setText( ani.getNome());
        item.tvIdade.setText( String.valueOf(ani.getIdade()));
        item.tvEspecie.setText( ani.getEspecie().getNome());



        return view;
    }

    private class Suporte{
        LinearLayout fundo;
        TextView tvNome, tvIdade, tvEspecie;
        ImageView ivLogo;
    }
}
