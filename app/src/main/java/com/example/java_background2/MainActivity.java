package com.example.java_background2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Bundle;

import com.example.java_background2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        binding.buttonExecutor.setOnClickListener( v -> navController.navigate(R.id.executorFragment));
        binding.buttonJob.setOnClickListener( v -> navController.navigate(R.id.jobFragment));
        binding.buttonWork.setOnClickListener(v -> navController.navigate(R.id.workFragment));
    }
}