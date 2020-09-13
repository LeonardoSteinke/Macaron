package com.example.macaron.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.macaron.R;

import java.util.ArrayList;
import java.util.List;

import model.Ingrediente;


public class AddIngredients extends Fragment {

    public View view;
    public RecyclerView recyclerView;
    public List<Ingrediente> ingredienteList;
    public TextView btn;
    public ImageButton btnConfirmar;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_ingredient, container, false);
        return view;
    }

    public void insertItem() {
        ingredienteList.add(new Ingrediente());
    }

    public void removeItem() {

    }

    public void createList() {
        ingredienteList = new ArrayList<>();
        ingredienteList.add(new Ingrediente());
    }

    public void buildRecyclerView() {
        recyclerView = view.findViewById(R.id.myRecipesRecycler);
    }


    public void initComponents() {
        btn = view.findViewById(R.id.btnAddIngredient);
        btnConfirmar = view.findViewById(R.id.imageButton);
    }
}
