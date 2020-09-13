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

public class AddUserIngredients extends AddIngredients {

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

         //myAdapter.register();

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
        btn = view.findViewById(R.id.btnAddIngredient);
        btnConfirmar = view.findViewById(R.id.imageButton);
    }
}
