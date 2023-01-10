package com.example.idcardgenerator;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.idcardgenerator.databinding.FragmentFirstBinding;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private  Bitmap image = null;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = binding.edEmail.getText().toString();
                String id = binding.edId.getText().toString();
                String branch = binding.edBr.getText().toString();
                String father = binding.edFn.getText().toString();
                String cn = binding.edCn.getText().toString();
                String adrs = binding.edAdrs.getText().toString();

                if(name.isEmpty() || id.isEmpty() || branch.isEmpty() || father.isEmpty() ||
                cn.isEmpty() || adrs.isEmpty() || image == null){

                    Log.d("haha", "onClick: "+(image == null)+(cn.isEmpty())+(adrs));
                    Toast.makeText(getContext(), "Fill all the fields",Toast.LENGTH_LONG).show();
                } else {
                    Card c = new Card(name, id, branch, father, cn, adrs, image);
                    Bundle bundle = new Bundle();
                    bundle.putString("name", name);
                    bundle.putString("id", id);
                    bundle.putString("branch", branch);
                    bundle.putString("father", father);
                    bundle.putString("cn", cn);
                    bundle.putString("adrs", adrs);
                    bundle.putParcelable("image", image);
                    NavHostFragment.findNavController(FirstFragment.this)
                            .navigate(R.id.action_FirstFragment_to_SecondFragment, bundle);
                }

            }
        });

        binding.upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, 122);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 122) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = requireContext().getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                image = selectedImage;
                binding.pic.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_LONG).show();
            }

        }else {
            Toast.makeText(requireContext(), "You haven't picked Image",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}