package com.example.macaron.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.macaron.R;

import model.Receita;

public class AddRecipeFragment extends Fragment {

    private Spinner spnCategoria;
    private Spinner spnDificuldade;
    private Spinner spnTipo;
    private Button btnCadastrarReceita;
    private EditText edtNome;
    private EditText edtPorcoes;
    private EditText edtTempoPreparo;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_recipe, container, false);
        initComponents();


        btnCadastrarReceita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Receita receita = new Receita();
                receita.setNome(edtNome.getText().toString());
                receita.setTempo_preparo(Integer.parseInt(edtTempoPreparo.getText().toString()));
                receita.setDificuldade(spnDificuldade.getSelectedItemPosition());
                receita.setPorcoes(Integer.parseInt(edtPorcoes.getText().toString()));
                receita.setCategoria(spnCategoria.getSelectedItem().toString());
                receita.setTipo(spnTipo.getSelectedItemPosition());


            }

        });


        return view;
    }

    private void initComponents() {
        spnCategoria = view.findViewById(R.id.spnCategoria);
        ArrayAdapter<CharSequence> adapterCategoria = ArrayAdapter.createFromResource(getContext(), R.array.Categorias_comidas, R.layout.support_simple_spinner_dropdown_item);
        spnCategoria.setAdapter(adapterCategoria);

        spnDificuldade = view.findViewById(R.id.spnDificuldade);
        ArrayAdapter<CharSequence> adapterDificuldade = ArrayAdapter.createFromResource(getContext(), R.array.dificuldade_comidas, R.layout.support_simple_spinner_dropdown_item);
        spnDificuldade.setAdapter(adapterDificuldade);

        spnTipo = view.findViewById(R.id.spnTipo);
        ArrayAdapter<CharSequence> adapterTipo = ArrayAdapter.createFromResource(getContext(), R.array.tipos_comidas, R.layout.support_simple_spinner_dropdown_item);
        spnTipo.setAdapter(adapterTipo);

        edtNome = view.findViewById(R.id.edtNomeReceita);
        edtPorcoes = view.findViewById(R.id.edtPorcoes);
        edtTempoPreparo = view.findViewById(R.id.edtTempoDePreparo);

        btnCadastrarReceita = view.findViewById(R.id.btnCadastrarReceita);

    }


}
