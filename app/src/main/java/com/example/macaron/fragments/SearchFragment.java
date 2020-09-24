package com.example.macaron.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.macaron.R;
import com.example.macaron.adapter.MyRecipesAdapter;

import java.util.List;

import model.Ingrediente;
import model.Receita;
import retrofit.RetrofitInitializer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFragment extends Fragment {
    View view;

    SearchView searchByName;
    SearchView searchByIngredient;
    RecyclerView recyclerView;
    ImageButton btnVoltar;

    List<Receita> receitaList;

    MyRecipesAdapter myAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_recipe_search, container, false);
        initComponents();

        searchByName.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {


                myAdapter.getFilter().filter(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                return false;
            }
        });
        searchByIngredient.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                receitaList.clear();
                Call<List<Ingrediente>> call = new RetrofitInitializer().setIngredienteReceitaService().searchByName(s);
                call.enqueue(new Callback<List<Ingrediente>>() {
                    @Override
                    public void onResponse(Call<List<Ingrediente>> call, Response<List<Ingrediente>> response) {

                        List<Ingrediente> ingredienteList = response.body();
                        for (Ingrediente i : ingredienteList) {
                            Call<Receita> callReceita = new RetrofitInitializer().setReceitaService().select(i.getId_secundario());
                            callReceita.enqueue(new Callback<Receita>() {
                                @Override
                                public void onResponse(Call<Receita> call, Response<Receita> response) {

                                    receitaList.add(response.body());
                                    buildRecyclerView(receitaList);
                                    myAdapter.notifyDataSetChanged();

                                }

                                @Override
                                public void onFailure(Call<Receita> call, Throwable t) {

                                }
                            });

                        }
                    }

                    @Override
                    public void onFailure(Call<List<Ingrediente>> call, Throwable t) {

                    }
                });

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });


        return view;
    }

    private void initComponents() {
        btnVoltar = view.findViewById(R.id.imageButton4);
        searchByName = view.findViewById(R.id.searchViewName);
        searchByIngredient = view.findViewById(R.id.searchViewIngredient);
        recyclerView = view.findViewById(R.id.RecyclerSearchRecipes);

        Call<List<Receita>> call = new RetrofitInitializer().setReceitaService().select();
        call.enqueue(new Callback<List<Receita>>() {
            @Override
            public void onResponse(Call<List<Receita>> call, Response<List<Receita>> response) {
                receitaList = response.body();
                buildRecyclerView(receitaList);
            }

            @Override
            public void onFailure(Call<List<Receita>> call, Throwable t) {

            }
        });
    }

    private void buildRecyclerView(List<Receita> receitaList) {
        try {
            myAdapter = new MyRecipesAdapter(getContext(), receitaList);
            recyclerView.setAdapter(myAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

            myAdapter.setOnItemClickListener(new MyRecipesAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    Receita receita = receitaList.get(position);
                    RecipeFragment myFrag = new RecipeFragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt("id", receita.getId_usuario());
                    bundle.putString("Nome", receita.getNome());
                    bundle.putInt("Tempo", receita.getTempo_preparo());
                    bundle.putInt("Dificuldade", receita.getDificuldade());
                    bundle.putInt("Porcoes", receita.getPorcoes());
                    bundle.putString("Categoria", receita.getCategoria());
                    bundle.putInt("Tipo", receita.getTipo());
                    bundle.putString("ModoPreparo", receita.getModo_preparo());
                    myFrag.setArguments(bundle);

                    getParentFragmentManager().beginTransaction().replace(R.id.fragment_container, myFrag).commit();


                }
            });

        } catch (Exception e) {

        }
    }

}
