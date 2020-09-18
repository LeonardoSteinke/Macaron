package com.example.macaron.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.recyclerview.widget.LinearLayoutManager;


import com.example.macaron.R;
import com.example.macaron.adapter.IngredientsAdapter;


import model.Receita;
import retrofit.RetrofitInitializer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddRecipeIngredients extends AddIngredients {

    IngredientsAdapter myAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        initComponents();

        createList();
        buildRecyclerView();


        btn.setOnClickListener(view -> {
            insertItem();
        });

        btnConfirmar.setOnClickListener(view -> {

            Receita receita = new Receita();
            receita.setId_usuario(getArguments().getInt("id"));
            receita.setNome(getArguments().getString("Nome"));
            receita.setTempo_preparo((getArguments().getInt("Tempo")));
            receita.setDificuldade(getArguments().getInt("Dificuldade"));
            receita.setPorcoes(getArguments().getInt(("Porcoes")));
            receita.setCategoria(getArguments().getString("Categoria"));
            receita.setTipo(getArguments().getInt("Tipo"));
            receita.setModo_preparo(getArguments().getString("ModoPreparo"));


            Call<Receita> call = new RetrofitInitializer().setReceitaService().cadastrarReceita(receita);
            call.enqueue(new Callback<Receita>() {
                @Override
                public void onResponse(Call<Receita> call, Response<Receita> response) {
                    myAdapter.register(response.body().getId());
                    getParentFragmentManager().beginTransaction().replace(R.id.fragment_container, new MyRecipesFragment()).commit();
                }

                @Override
                public void onFailure(Call<Receita> call, Throwable t) {
                    Log.i("testes", "deu algum erro no cadastro de receita");
                }
            });

        });

        return view;
    }

    public void insertItem() {
        super.insertItem();
        myAdapter.notifyItemInserted(ingredienteList.size());
    }

    public void removeItem() {
        super.removeItem();
    }

    public void buildRecyclerView() {
        recyclerView = view.findViewById(R.id.myRecipesRecycler);
        myAdapter = new IngredientsAdapter(getContext(), ingredienteList);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager((getContext())));
    }

    public void initComponents() {
        btn = view.findViewById(R.id.btnAddNewIngredient);
        btnConfirmar = view.findViewById(R.id.imageButton);
    }

}
