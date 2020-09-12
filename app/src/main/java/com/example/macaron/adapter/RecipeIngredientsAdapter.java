package com.example.macaron.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    public RecipeIngredientsAdapter(Context ct, List<Ingrediente> ingrediente) {
        context = ct;
        this.ingredientes = ingrediente;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.add_ingredients_card, parent, false);
        MyViewHolder mvh = new MyViewHolder(view);
        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

     //   ingredientes.get(position).setNome(holder.spnIngredientes.getSelectedItem().toString());
      //  ingredientes.get(position).setQuantidade(Integer.parseInt(holder.edtQuantidade.getText().toString()));
    }

    public void register() {

        for (Ingrediente ingrediente : ingredientes) {
            Call<Ingrediente> call = new RetrofitInitializer().setIngredienteReceitaService().cadastrarIngrediente(ingrediente);
            call.enqueue(new Callback<Ingrediente>() {
                @Override
                public void onResponse(Call<Ingrediente> call, Response<Ingrediente> response) {

                }

                @Override
                public void onFailure(Call<Ingrediente> call, Throwable t) {
                    Log.i("testes", "deu algum erro no cadastro de receita");
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
        public EditText edtQuantidade;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            spnIngredientes = itemView.findViewById(R.id.spnIngredientes);
            ArrayAdapter<CharSequence> adapterIngredientes = ArrayAdapter.createFromResource(context, R.array.ingredientes, R.layout.support_simple_spinner_dropdown_item);
            spnIngredientes.setAdapter(adapterIngredientes);

            edtQuantidade = itemView.findViewById(R.id.edtQuantidadeIngrediente);

        }
    }
}
