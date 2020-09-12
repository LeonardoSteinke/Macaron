package com.example.macaron.fragments;

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
    private List<Ingrediente> ingredienteList;
    private TextView btn;
    private ImageButton btnConfirmar;
    RecipeIngredientsAdapter myAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_recipe_ingredient, container, false);
        initComponents();

        createList();
        buildRecyclerView();


        btn.setOnClickListener(view -> {
            insertItem();
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


            Call<Receita> call = new RetrofitInitializer().setReceitaService().cadastrarReceita(receita);
            call.enqueue(new Callback<Receita>() {
                @Override
                public void onResponse(Call<Receita> call, Response<Receita> response) {
                    // dialog.hide();
                    Receita receitaResponse = response.body();
                    myAdapter.register(response.body().getId());
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

    public void insertItem() {
        ingredienteList.add(new Ingrediente());
        myAdapter.notifyItemInserted(ingredienteList.size());
    }

    public void removeItem() {

    }

    private void createList() {
        ingredienteList = new ArrayList<>();
        ingredienteList.add(new Ingrediente());
    }

    private void buildRecyclerView() {
        recyclerView = view.findViewById(R.id.myRecipesRecycler);
        myAdapter = new RecipeIngredientsAdapter(getContext(), ingredienteList);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager((getContext())));
    }


    private void initComponents() {
        btn = view.findViewById(R.id.btnAddIngredient);
        btnConfirmar = view.findViewById(R.id.imageButton);


    }
}
