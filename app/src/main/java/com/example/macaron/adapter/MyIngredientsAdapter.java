package com.example.macaron.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.macaron.R;

import java.text.BreakIterator;
import java.util.List;

import model.Ingrediente;


public class MyIngredientsAdapter extends RecyclerView.Adapter<MyIngredientsAdapter.MyViewHolder>{

    Context context;
    List<Ingrediente> ingredientes;
    private MyRecipesAdapter.OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(MyRecipesAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }

    public MyIngredientsAdapter(Context ct, List<Ingrediente> ingredientes) {
        context = ct;
        this.ingredientes = ingredientes;
    }

    @NonNull
    @Override
    public MyIngredientsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.ingredient_card, parent, false);
        return new MyIngredientsAdapter.MyViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtIngrediente.setText(ingredientes.get(position).getIngrediente());
        holder.txtQtd.setText((int) ingredientes.get(position).getQuantidade());
        holder.txtMedida.setText(ingredientes.get(position).getUnidade_medida());
    }

    @Override
    public int getItemCount() {
        return ingredientes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtIngrediente;
        TextView txtQtd;
        TextView txtMedida;

        public MyViewHolder(@NonNull View itemView, MyRecipesAdapter.OnItemClickListener listener) {
            super(itemView);
            txtIngrediente = itemView.findViewById(R.id.txtIngrediente);
            txtQtd = itemView.findViewById(R.id.txtQtd);
            txtMedida = itemView.findViewById(R.id.txtMedida);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener != null) {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION) {
                            listener.onItemClick((position));
                        }
                    }
                }
            });
        }
    }

}
