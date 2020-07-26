package com.example.macaron;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.macaron.fragments.FeedFragment;
import com.example.macaron.fragments.MyIngredientsFragment;
import com.example.macaron.fragments.MyRecipesFragment;
import com.example.macaron.fragments.PerfilFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);

        BottomNavigationView bottonNav = findViewById(R.id.bottom_navigation);
        bottonNav.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FeedFragment()).commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            switch (item.getItemId()) {
                case R.id.navigation_feed:
                    selectedFragment = new FeedFragment();
                    break;
                case R.id.navigation_myrecipes:
                    selectedFragment = new MyRecipesFragment();
                    break;
                case R.id.navigation_myingredients:
                    selectedFragment = new MyIngredientsFragment();
                    break;
                case R.id.navigation_perfil:
                    selectedFragment = new PerfilFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            return true;

        }
    };
}
