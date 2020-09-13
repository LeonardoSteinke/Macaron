package com.example.macaron.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.macaron.R;
import com.example.macaron.adapter.MyIngredientsAdapter;

import java.util.List;

import model.Ingrediente;
import retrofit.RetrofitInitializer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyIngredientsFragment extends Fragment {

    private Button btn;
    private View view;
    private RecyclerView recyclerView;
    private List<Ingrediente> ingredientList;
    private MyIngredientsAdapter myAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my_ingredients, container, false);
        initComponents();
        return view;
    }

    private void initComponents() {
        btn = view.findViewById(R.id.btnAddRecipe);
        recyclerView = view.findViewById(R.id.myRecipesRecycler);
        Call<List<Ingrediente>> call = new RetrofitInitializer().setIngredienteService().select();
        call.enqueue(new Callback<List<Ingrediente>>() {
            @Override
            public void onResponse(Call<List<Ingrediente>> call, Response<List<Ingrediente>> response) {
                ingredientList = response.body();
                buildRecyclerView();
            }

            @Override
            public void onFailure(Call<List<Ingrediente>> call, Throwable t) {
                buildRecyclerView();
            }
        });

    }

    public void buildRecyclerView() {
        try {
            myAdapter = new MyIngredientsAdapter(getContext(), ingredientList);
            recyclerView.setAdapter(myAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        } catch (Exception e) {

        }
        btn.setOnClickListener(v -> getParentFragmentManager().beginTransaction().replace(R.id.fragment_container, new AddUserIngredients()).commit());
    }
}
