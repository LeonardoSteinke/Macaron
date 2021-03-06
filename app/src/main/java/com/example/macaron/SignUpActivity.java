package com.example.macaron;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import model.Usuario;
import retrofit.RetrofitInitializer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import services.AppDatabase;

public class SignUpActivity extends AppCompatActivity {

    EditText edtNome;
    EditText edtEmail;
    EditText edtSenha;
    EditText edtSenhaRepeat;
    Button btn;
    ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initComponents();

        btn.setOnClickListener(v -> {
            if (edtSenha.getText().toString().equals(edtSenhaRepeat.getText().toString())) {

                dialog.show();

                Usuario u = new Usuario();
                u.setNome(edtNome.getText().toString());
                u.setEmail(edtEmail.getText().toString());
                u.setSenha(edtSenha.getText().toString());
                Call<Usuario> call = new RetrofitInitializer().setUserService().SignUp(u);
                call.enqueue(new Callback<Usuario>() {
                    @Override
                    public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                        dialog.hide();
                        if (response.body().getId() != 0) {
                            AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "dbMacaron").allowMainThreadQueries().build();
                            db.usuarioDao().insertAll(response.body());
                            Intent i = new Intent(SignUpActivity.this, DashboardActivity.class);
                            startActivity(i);
                        } else {
                            Toast.makeText(getApplicationContext(), "E-mail já cadastrado", Toast.LENGTH_SHORT).show();

                        }
                    }


                    @Override
                    public void onFailure(Call<Usuario> call, Throwable t) {
                        dialog.hide();
                        Log.i("testes", "Deu erro");
                    }
                });


            } else {
                dialog.hide();
                Toast.makeText(getApplicationContext(), "Senhas não conferem", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initComponents() {
        edtNome = findViewById(R.id.edtNameSignUp);
        edtEmail = findViewById(R.id.edtEmail);
        edtSenha = findViewById(R.id.edtSenhaSignUp);
        edtSenhaRepeat = findViewById(R.id.edtSenha2SignUp);
        btn = findViewById(R.id.progressButton);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Conectando com o servidor");
        dialog.setCancelable(false);
        dialog.setInverseBackgroundForced(false);

    }




}