package com.example.macaron.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import com.example.macaron.DashboardActivity;
import com.example.macaron.MainActivity;
import com.example.macaron.R;
import com.example.macaron.SignUpActivity;

import services.AppDatabase;

public class PerfilFragment extends Fragment {

    private Button btnSair;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_perfil, container, false);
        btnSair = view.findViewById(R.id.btnSair);


        btnSair.setOnClickListener(view -> {
            AppDatabase db = Room.databaseBuilder(getContext(), AppDatabase.class, "dbMacaron").allowMainThreadQueries().build();
            db.clearAllTables();

            Intent i = new Intent(getContext(), MainActivity.class);
            startActivity(i);



        });




        return view;
    }
}
