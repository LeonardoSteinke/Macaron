package com.example.macaron.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

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

public class FeedFragment extends Fragment {

    ImageView imgSearch;
    TextView txtFeed;

    private View view;
    private RecyclerView recyclerView;
    private List<Receita> receitaList;
    private MyRecipesAdapter myAdapter;
    private SearchView SV;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_feed, container, false);
        initComponents();
        SV.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                myAdapter.getFilter().filter(s);
                return false;
            }
        });

        return view;
    }

    private void initComponents() {
        txtFeed = view.findViewById(R.id.txtFeed);

        recyclerView = view.findViewById(R.id.RecyclerFeed);
        SV = view.findViewById(R.id.searchView);
        Call<List<Receita>> call = new RetrofitInitializer().setReceitaService().select();
        call.enqueue(new Callback<List<Receita>>() {
            @Override
            public void onResponse(Call<List<Receita>> call, Response<List<Receita>> response) {
                receitaList = response.body();
                buildRecyclerView();
            }

            @Override
            public void onFailure(Call<List<Receita>> call, Throwable t) {
                buildRecyclerView();
            }
        });

    }

    public void buildRecyclerView() {
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
