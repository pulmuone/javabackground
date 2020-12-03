package com.example.java_background2.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.java_background2.R;
import com.example.java_background2.databinding.FragmentJobBinding;
import com.example.java_background2.databinding.FragmentWorkBinding;
import com.example.java_background2.worker.MyWorker;

public class WorkFragment extends Fragment {

    private FragmentWorkBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_work, container, false);
        binding = FragmentWorkBinding.bind(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(MyWorker.class)
                .build();

        WorkManager.getInstance(requireContext())
                .getWorkInfoByIdLiveData(request.getId())
                .observe(getViewLifecycleOwner(), workInfo -> {
                    int progress = workInfo.getProgress().getInt("progress", 0);
                    binding.progress.setProgress(progress);
                });

        binding.button.setOnClickListener(v -> {
            WorkManager.getInstance(requireContext()).enqueue(request);
        });
    }
}