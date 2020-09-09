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
import com.example.macaron.adapter.MyRecipesAdapter;

import java.util.List;

import model.Receita;
import retrofit.RetrofitInitializer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyRecipesFragment extends Fragment {

    private Button btn;
    private View view;
    private RecyclerView recyclerView;
    private List<Receita> receitaList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my_recipes, container, false);
        initComponents();


        return view;
    }

    private void initComponents() {
        Call<List<Receita>> call = new RetrofitInitializer().setReceitaService().select();
        call.enqueue(new Callback<List<Receita>>() {
            @Override
            public void onResponse(Call<List<Receita>> call, Response<List<Receita>> response) {
                receitaList = response.body();

                btn = view.findViewById(R.id.btnAddRecipe);
                recyclerView = view.findViewById(R.id.myRecipesRecycler);

                try {
                    MyRecipesAdapter myAdapter = new MyRecipesAdapter(getContext(), receitaList);
                    recyclerView.setAdapter(myAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                } catch (Exception e) {

                }
                btn.setOnClickListener(v -> getParentFragmentManager().beginTransaction().replace(R.id.fragment_container, new AddRecipeFragment()).commit());
            }

            @Override
            public void onFailure(Call<List<Receita>> call, Throwable t) {
                btn = view.findViewById(R.id.btnAddRecipe);
                recyclerView = view.findViewById(R.id.myRecipesRecycler);

                try {
                    MyRecipesAdapter myAdapter = new MyRecipesAdapter(getContext(), receitaList);
                    recyclerView.setAdapter(myAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                } catch (Exception e) {

                }
                btn.setOnClickListener(v -> getParentFragmentManager().beginTransaction().replace(R.id.fragment_container, new AddRecipeFragment()).commit());
            }
        });

    }
}
