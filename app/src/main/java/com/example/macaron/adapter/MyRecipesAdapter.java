package com.example.macaron.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.macaron.R;

import java.util.ArrayList;
import java.util.List;

import model.Receita;


public class MyRecipesAdapter extends RecyclerView.Adapter<MyRecipesAdapter.MyViewHolder> implements Filterable {

    Context context;
    List<Receita> receitas;
    List<Receita> receitasFilter;
    private OnItemClickListener listener;

    @Override
    public Filter getFilter() {
        return filter;
    }

    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Receita> filteredList = new ArrayList<>();
            if (charSequence == null || charSequence.length() == 0) {
                filteredList.addAll(receitasFilter);
            } else {
                String filterPattern = charSequence.toString().toLowerCase().trim();
                for (Receita receita : receitasFilter) {
                    if (receita.getNome().toLowerCase().contains(filterPattern)) {
                        filteredList.add(receita);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            receitas.clear();
            receitas.addAll((List) filterResults.values);
            notifyDataSetChanged();


        }
    };

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public MyRecipesAdapter(Context ct, List<Receita> receitas) {
        context = ct;
        this.receitas = receitas;
        receitasFilter = new ArrayList<>(receitas);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recipes_card, parent, false);
        return new MyViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtName.setText(receitas.get(position).getNome());
        holder.txtTempo.setText("Tempo de preparo: " + receitas.get(position).getTempo_preparo());
        holder.txtDificuldade.setText("Dificuldade: " + receitas.get(position).getDificuldade());
        //Picasso.get().load(users.get(position).getAvatar_url()).into(holder.img);
    }


    @Override
    public int getItemCount() {
        return receitas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtName;
        TextView txtTempo;
        TextView txtDificuldade;
        ImageView img;

        public MyViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtTempo = itemView.findViewById(R.id.txtTempo);
            txtDificuldade = itemView.findViewById(R.id.txtDificuldade);
            img = itemView.findViewById(R.id.imgComida);
            itemView.setOnClickListener(view -> {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick((position));
                    }
                }
            });
        }
    }
}
