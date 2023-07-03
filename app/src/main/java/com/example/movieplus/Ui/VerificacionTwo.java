package com.example.movieplus.Ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.example.movieplus.R;
import com.example.movieplus.databinding.ActivityVerificacionTwoBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class VerificacionTwo extends AppCompatActivity {
private ActivityVerificacionTwoBinding binding;
private String verificationId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVerificacionTwoBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        editTextInput();
        
        binding.textNumero.setText(String.format("+54",getIntent().getStringExtra("phone")));

        verificationId = getIntent().getStringExtra("verificationId");
        binding.textReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(VerificacionTwo.this,"Suceful",Toast.LENGTH_LONG).show();
            }
        });

        binding.btnVerificacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.btnVerificacion.setVisibility(View.INVISIBLE);

                if (binding.etC1.getText().toString().trim().isEmpty() ||
                binding.etC2.getText().toString().trim().isEmpty() ||
                binding. etC3.getText().toString().trim().isEmpty() ||
                 binding .etC4.getText().toString().trim().isEmpty() ||
                binding.etC5.getText().toString().trim().isEmpty() ||
                binding.etC6.getText().toString().trim().isEmpty() ){
                    Toast.makeText(VerificacionTwo.this,"OTP no valido",Toast.LENGTH_LONG).show();
                }else {
                    if (verificationId != null){
                        String code = binding.etC1.getText().toString().trim() +
                                binding.etC2.getText().toString().trim()+
                                binding.etC3.getText().toString().trim()+
                                binding.etC4.getText().toString().trim()+
                                binding.etC5.getText().toString().trim()+
                                binding.etC6.getText().toString().trim();
                        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId,code);
                        FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){

                                     binding.btnVerificacion.setVisibility(View.INVISIBLE);
                                    Toast.makeText(VerificacionTwo.this,"welcome ", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(VerificacionTwo.this,MainActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                }else {
                                    binding.btnVerificacion.setVisibility(View.VISIBLE);
                                    Toast.makeText(VerificacionTwo.this,"OTP no valido",Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                }
            }
        });
    }

    private void editTextInput() {
        binding.etC1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                binding.etC2.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.etC2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                binding.etC3.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.etC3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                binding.etC4.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.etC4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                binding.etC5.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.etC5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                binding.etC6.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}