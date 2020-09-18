package com.example.macaron.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import com.example.macaron.R;
import com.example.macaron.adapter.IngredientsAdapter;

import java.util.List;

import model.Usuario;
import services.AppDatabase;

public class AddUserIngredients extends AddIngredients {

    IngredientsAdapter myAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        initComponents();

        int idUser = 0;

        AppDatabase db = Room.databaseBuilder(getContext(), AppDatabase.class, "dbMacaron").allowMainThreadQueries().build();
        try {
            List<Usuario> userList = db.usuarioDao().getAll();
            for (Usuario user : userList) {
                if (user != null) {
                    idUser = user.getId();
                    System.out.println(idUser);
                }
            }
        } catch (Exception e) {
            Log.i("testes", "deu erro no banco");
        }


        createList();
        buildRecyclerView();


        btn.setOnClickListener(view -> {
            insertItem();
        });

        int finalIdUser = idUser;
        btnConfirmar.setOnClickListener(view -> {

         myAdapter.registerUSER(finalIdUser);

            getParentFragmentManager().beginTransaction().replace(R.id.fragment_container, new MyIngredientsFragment()).commit();

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
