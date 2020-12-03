package com.example.java_background2.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.java_background2.MainViewModel;
import com.example.java_background2.R;
import com.example.java_background2.databinding.FragmentExecutorBinding;

public class ExecutorFragment extends Fragment {
    private MainViewModel viewModel;
    private FragmentExecutorBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_executor, container, false);
        binding = FragmentExecutorBinding.bind(view);
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity(), new ViewModelProvider.AndroidViewModelFactory(requireActivity().getApplication())).get(MainViewModel.class);

        viewModel.progressLiveData.observe(getViewLifecycleOwner(), progress -> {
            binding.progress.setProgress(progress);
        } );
        binding.button.setOnClickListener(v -> viewModel.longTask());
    }
}