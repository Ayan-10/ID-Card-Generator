package com.example.idcardgenerator;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.idcardgenerator.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();

//Extract the data…
        String name = bundle.getString("name");
        String id = bundle.getString("id");
        String branch = bundle.getString("branch");
        String father = bundle.getString("father");
        String cn = bundle.getString("cn");
        String adrs = bundle.getString("adrs");
        Bitmap image = bundle.getParcelable("image");

        binding.imageView2.setImageBitmap(image);
        binding.name.setText(name);
        binding.id.setText("ID  :  " +id);
        binding.branch.setText("Branch : " +branch);
        binding.father.setText("Father's Name : " +father);
        binding.contact.setText("Contact No. : " +cn);
        binding.adrs.setText("Address : " +adrs);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}