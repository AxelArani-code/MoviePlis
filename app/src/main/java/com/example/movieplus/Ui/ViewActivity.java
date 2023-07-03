package com.example.movieplus.Ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.movieplus.Model.Movie;
import com.example.movieplus.R;
import com.example.movieplus.databinding.ActivityMainBinding;
import com.example.movieplus.databinding.ActivityViewBinding;
import com.squareup.picasso.Picasso;

public class ViewActivity extends AppCompatActivity {
    private ActivityViewBinding  binding;
    private double value = 0.0;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        getData();
        binding.btnPremium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogPremium();
            }
        });
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogPremium();
            }
        });
        binding.btnSave2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogPremium();
            }
        });
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void getData() {
        Movie movie = (Movie) getIntent().getSerializableExtra("ListElement");
        binding.textTitleView.setText(movie.getTitulo());
        binding.textGenero.setText(movie.getGenero());
        binding.textDescripsion.setText(movie.getDescripsion());
        binding.textViewFeha.setText("Lanzamiento "+movie.getFecha());
        binding.textViewEstrellas.setText(movie.getEstrella());
        Picasso.get().load(movie.getPortada()).into(binding.portada);
    }

    private void showDialogPremium() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_premium, null);
        dialogBuilder.setView(dialogView);



        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
    }




}