package com.example.macaron.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import com.example.macaron.DashboardActivity;
import com.example.macaron.MainActivity;
import com.example.macaron.R;

import java.util.List;

import model.Receita;
import model.Usuario;
import retrofit.RetrofitInitializer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import services.AppDatabase;

public class AddRecipeFragment extends Fragment {

    private Spinner spnCategoria;
    private Spinner spnDificuldade;
    private Spinner spnTipo;
    private Button btnCadastrarReceita;
    private EditText edtNome;
    private EditText edtPorcoes;
    private EditText edtTempoPreparo;
    private EditText edtModopreparo;
    View view;

    ProgressDialog dialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_recipe, container, false);
        initComponents();

        btnCadastrarReceita.setOnClickListener(v -> {
            try {
                Receita receita = new Receita();
                AppDatabase db = Room.databaseBuilder(getContext(), AppDatabase.class, "dbMacaron").allowMainThreadQueries().build();
                List<Usuario> userList = db.usuarioDao().getAll();
                for (Usuario user : userList) {
                    if (user != null) {
                        receita.setId_usuario(user.getId());
                    }
                }
                receita.setNome(edtNome.getText().toString());
                receita.setTempo_preparo(Integer.parseInt(edtTempoPreparo.getText().toString()));
                receita.setDificuldade(spnDificuldade.getSelectedItemPosition());
                receita.setPorcoes(Integer.parseInt(edtPorcoes.getText().toString()));
                receita.setCategoria(spnCategoria.getSelectedItem().toString());
                receita.setTipo(spnTipo.getSelectedItemPosition());
                receita.setModo_preparo(edtModopreparo.getText().toString());

                Fragment myFrag = new AddRecipeIngredients();
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
//                Call<Receita> call = new RetrofitInitializer().setReceitaService().cadastrarReceita(receita);
//                call.enqueue(new Callback<Receita>() {
//                    @Override
//                    public void onResponse(Call<Receita> call, Response<Receita> response) {
//                        dialog.hide();
//                        getParentFragmentManager().beginTransaction().replace(R.id.fragment_container, new MyRecipesFragment()).commit();
//                    }
//
//                    @Override
//                    public void onFailure(Call<Receita> call, Throwable t) {
//                        dialog.hide();
//                        Log.i("testes", "deu algum erro no cadastro de receita");
//                    }
//                });


            } catch (Exception e) {

                Toast.makeText(getContext(), "Verifique todos os campos", Toast.LENGTH_SHORT).show();
            }

        });
        return view;
    }

    private void initComponents() {
        spnCategoria = view.findViewById(R.id.spnCategoria);
        ArrayAdapter<CharSequence> adapterCategoria = ArrayAdapter.createFromResource(getContext(), R.array.Categorias_comidas, R.layout.support_simple_spinner_dropdown_item);
        spnCategoria.setAdapter(adapterCategoria);

        spnDificuldade = view.findViewById(R.id.spnDificuldade);
        ArrayAdapter<CharSequence> adapterDificuldade = ArrayAdapter.createFromResource(getContext(), R.array.dificuldade_comidas, R.layout.support_simple_spinner_dropdown_item);
        spnDificuldade.setAdapter(adapterDificuldade);

        spnTipo = view.findViewById(R.id.spnTipo);
        ArrayAdapter<CharSequence> adapterTipo = ArrayAdapter.createFromResource(getContext(), R.array.tipos_comidas, R.layout.support_simple_spinner_dropdown_item);
        spnTipo.setAdapter(adapterTipo);

        edtNome = view.findViewById(R.id.edtNomeReceita);
        edtPorcoes = view.findViewById(R.id.edtPorcoes);
        edtTempoPreparo = view.findViewById(R.id.edtTempoDePreparo);
        edtModopreparo = view.findViewById(R.id.edtModoPreparo);

        btnCadastrarReceita = view.findViewById(R.id.btnCadastrarReceita);

        dialog = new ProgressDialog(getContext());
        dialog.setMessage("Conectando com o servidor");
        dialog.setCancelable(false);
        dialog.setInverseBackgroundForced(false);

    }

}
