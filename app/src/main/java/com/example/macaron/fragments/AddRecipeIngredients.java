package com.example.macaron.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.macaron.R;
import com.example.macaron.adapter.RecipeIngredientsAdapter;

import java.util.ArrayList;
import java.util.List;

import model.Ingrediente;
import model.Receita;
import retrofit.RetrofitInitializer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddRecipeIngredients extends Fragment {

    View view;
    //ProgressDialog dialog = new ProgressDialog(getContext());


    private RecyclerView recyclerView;
    private List<Ingrediente> ingredienteList = new ArrayList<>();
    private TextView btn;
    private ImageButton btnConfirmar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_recipe_ingredient, container, false);
        initComponents();
        Ingrediente ingrediente;
        ingrediente = new Ingrediente();
        ingredienteList.add(ingrediente);
        recyclerView = view.findViewById(R.id.myRecipesRecycler);
        RecipeIngredientsAdapter myAdapter = new RecipeIngredientsAdapter(getContext(), ingredienteList);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager((getContext())));

        btn.setOnClickListener(view -> {
            ingredienteList.add(new Ingrediente());
            recyclerView.setAdapter(new RecipeIngredientsAdapter(getContext(), ingredienteList));
            recyclerView.setLayoutManager(new LinearLayoutManager((getContext())));
            System.out.println("cliquei no +");
        });

        btnConfirmar.setOnClickListener(view -> {
            //dialog.show();

            Receita receita = new Receita();
            receita.setId_usuario(getArguments().getInt("id"));
            receita.setNome(getArguments().getString("Nome"));
            receita.setTempo_preparo((getArguments().getInt("Tempo")));
            receita.setDificuldade(getArguments().getInt("Dificuldade"));
            receita.setPorcoes(getArguments().getInt(("Porcoes")));
            receita.setCategoria(getArguments().getString("Categoria"));
            receita.setTipo(getArguments().getInt("Tipo"));
            receita.setModo_preparo(getArguments().getString("ModoPreparo"));

            myAdapter.register();

            Call<Receita> call = new RetrofitInitializer().setReceitaService().cadastrarReceita(receita);
            call.enqueue(new Callback<Receita>() {
                @Override
                public void onResponse(Call<Receita> call, Response<Receita> response) {

                   // dialog.hide();
                    getParentFragmentManager().beginTransaction().replace(R.id.fragment_container, new MyRecipesFragment()).commit();
                }

                @Override
                public void onFailure(Call<Receita> call, Throwable t) {
                   // dialog.hide();
                    Log.i("testes", "deu algum erro no cadastro de receita");
                }
            });
        });

        return view;
    }

    private void initComponents() {
        btn = view.findViewById(R.id.btnAddIngredient);
        btnConfirmar = view.findViewById(R.id.imageButton);


    }
}
