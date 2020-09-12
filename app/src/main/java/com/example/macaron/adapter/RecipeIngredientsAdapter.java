package com.example.macaron.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.macaron.R;
import com.example.macaron.fragments.MyRecipesFragment;

import java.util.List;

import model.Ingrediente;
import retrofit.RetrofitInitializer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeIngredientsAdapter extends RecyclerView.Adapter<RecipeIngredientsAdapter.MyViewHolder> {

    Context context;
    List<Ingrediente> ingredientes;
    MyViewHolder mvh;

    public RecipeIngredientsAdapter(Context ct, List<Ingrediente> ingrediente) {
        context = ct;
        this.ingredientes = ingrediente;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.add_ingredients_card, parent, false);
        mvh = new MyViewHolder(view);
        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        mvh.getSpnIngredientes().setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                ingredientes.get(position).setIngrediente(holder.spnIngredientes.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        mvh.getSpnUnidadeMedida().setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ingredientes.get(position).setUnidade_medida(holder.spnUnidadeMedida.getSelectedItem().toString());
                ingredientes.get(position).setQuantidade(Float.parseFloat(holder.edtQuantidade.getText().toString()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void register(int id_receita) {


        for (Ingrediente ingrediente : ingredientes) {
            ingrediente.setId_receita(id_receita);

            Call<Ingrediente> call = new RetrofitInitializer().setIngredienteReceitaService().cadastrarIngrediente(ingrediente);
            call.enqueue(new Callback<Ingrediente>() {
                @Override
                public void onResponse(Call<Ingrediente> call, Response<Ingrediente> response) {

                }

                @Override
                public void onFailure(Call<Ingrediente> call, Throwable t) {
                }
            });
        }


    }


    @Override
    public int getItemCount() {
        return ingredientes.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        public Spinner spnIngredientes;
        public Spinner spnUnidadeMedida;
        public EditText edtQuantidade;

        public Spinner getSpnIngredientes() {
            return spnIngredientes;
        }

        public EditText getEdtQuantidade() {
            return edtQuantidade;
        }

        public Spinner getSpnUnidadeMedida() {
            return spnUnidadeMedida;
        }

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            spnIngredientes = itemView.findViewById(R.id.spnIngredientes);
            ArrayAdapter<CharSequence> adapterIngredientes = ArrayAdapter.createFromResource(context, R.array.ingredientes, R.layout.support_simple_spinner_dropdown_item);
            spnIngredientes.setAdapter(adapterIngredientes);

            spnUnidadeMedida = itemView.findViewById(R.id.spnUnidadeMedida);
            ArrayAdapter<CharSequence> adapterMedida = ArrayAdapter.createFromResource(context, R.array.unidade_medida, R.layout.support_simple_spinner_dropdown_item);
            spnUnidadeMedida.setAdapter(adapterMedida);


            edtQuantidade = itemView.findViewById(R.id.edtQuantidadeIngrediente);

        }
    }
}
