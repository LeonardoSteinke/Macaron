package com.example.macaron.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.macaron.R;

import model.Receita;

public class RecipeFragment extends Fragment {

    private View view;

    private TextView txtNome;
    private TextView txtTempoPreparo;
    private TextView txtDificuldade;
    private TextView txtPorcoes;
    private TextView txtCategoria;
    private TextView txtTipo;
    private TextView txtModoPreparo;
    Receita receita;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_recipe, container, false);
        initComponents();

        return view;
    }

    private void initComponents() {
        receita = new Receita();
        receita.setId_usuario(getArguments().getInt("id"));
        receita.setNome(getArguments().getString("Nome"));
        receita.setTempo_preparo((getArguments().getInt("Tempo")));
        receita.setDificuldade(getArguments().getInt("Dificuldade"));
        receita.setPorcoes(getArguments().getInt(("Porcoes")));
        receita.setCategoria(getArguments().getString("Categoria"));
        receita.setTipo(getArguments().getInt("Tipo"));
        receita.setModo_preparo(getArguments().getString("ModoPreparo"));

        txtNome = view.findViewById(R.id.txtNomeReceita);
        txtNome.setText(""+receita.getNome());
        txtTempoPreparo = view.findViewById(R.id.txtTmp);
        txtTempoPreparo.setText(""+receita.getTempo_preparo());
        txtDificuldade = view.findViewById(R.id.txtDifi);
        txtDificuldade.setText(""+receita.getDificuldade());
        txtPorcoes = view.findViewById(R.id.txtPor);
        txtPorcoes.setText(""+receita.getPorcoes());
        txtCategoria = view.findViewById(R.id.txtCat);
        txtCategoria.setText(receita.getCategoria());
        txtTipo = view.findViewById(R.id.txtTip);
        txtTipo.setText(""+receita.getTipo());
        txtModoPreparo = view.findViewById(R.id.txtModo_preparo);
        txtModoPreparo.setText(""+receita.getModo_preparo());

    }
}
