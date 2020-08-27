package com.example.macaron;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.List;

import model.Usuario;
import retrofit.RetrofitInitializer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import services.AppDatabase;


public class MainActivity extends AppCompatActivity {

    Button btnSignUp;
    Button btnSignIn;
    EditText edtUsername;
    EditText edtPassword;
    ImageView imgLogo;
    ProgressDialog dialog;

    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        initComponents();

        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "dbMacaron").allowMainThreadQueries().build();
        try {
        List<Usuario> userList = db.usuarioDao().getAll();
            for (Usuario user: userList) {
            if(user != null){
                i = new Intent(MainActivity.this, DashboardActivity.class);
                startActivity(i);
            }
            }
        } catch (Exception e) {
            Log.i("testes", "deu erro no banco");
        }


        btnSignIn.setOnClickListener(v -> {
            dialog.show();
            Usuario u = new Usuario();
            u.setEmail(edtUsername.getText().toString());
            u.setSenha(edtPassword.getText().toString());
            Call<Usuario> call = new RetrofitInitializer().setSession().login(u);
            call.enqueue(new Callback<Usuario>() {
                @Override
                public void onResponse(Call<Usuario> call, Response<Usuario> response) {

                    if (response.body().getEmail().equalsIgnoreCase(u.getEmail())) {
                        dialog.hide();
                        db.usuarioDao().insertAll(response.body());
                        i = new Intent(MainActivity.this, DashboardActivity.class);
                        startActivity(i);

                    }
                }

                @Override
                public void onFailure(Call<Usuario> call, Throwable t) {
                    dialog.hide();
                    Toast.makeText(MainActivity.this, "Usuário ou senha incorretos", Toast.LENGTH_SHORT).show();
                }
            });

        });


        btnSignUp.setOnClickListener(v -> {
            i = new Intent(MainActivity.this, SignUpActivity.class);
            startActivity(i);
        });


    }


    private void initComponents() {
        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignIn = findViewById(R.id.btnSignIn);
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        imgLogo = findViewById(R.id.imgLogo);
        imgLogo.setImageResource(R.mipmap.logo_foreground);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Conectando com o servidor");
        dialog.setCancelable(false);
        dialog.setInverseBackgroundForced(false);
    }
}