package com.example.movieplus.Ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.movieplus.R;
import com.example.movieplus.databinding.ActivityRegisterBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class RegisterActivity extends AppCompatActivity {
    private ActivityRegisterBinding binding;
    private FirebaseAuth mAuth;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback;
    private String verificationId;
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null){
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        editTextInput();
        mAuth = FirebaseAuth.getInstance();
        binding.btnBackRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
        binding.btnCrearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.containerRegister.setVisibility(View.GONE);
                binding.conteinerCheck.setVisibility(View.VISIBLE);
                //getObtenerDatos();
                //Intent a = new Intent(getApplicationContext(),VerificacionOne.class);
                //startActivity(a);
            }
        });
        //
        binding.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.editPhone.getText().toString().trim().isEmpty()){
                    Toast.makeText(RegisterActivity.this,"Invalido el numero", Toast.LENGTH_LONG).show();
                }else if (binding.editPhone.getText().toString().trim().length() != 10){
                    Toast.makeText(RegisterActivity.this,"Type valid phone number", Toast.LENGTH_LONG).show();
                }else {
                    otpSend();
                }
            }
        });
        //
        binding.btnVerificacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  binding.btnVerificacion.setVisibility(View.INVISIBLE);

                if (binding.etC1.getText().toString().trim().isEmpty() ||
                        binding.etC2.getText().toString().trim().isEmpty() ||
                        binding. etC3.getText().toString().trim().isEmpty() ||
                        binding .etC4.getText().toString().trim().isEmpty() ||
                        binding.etC5.getText().toString().trim().isEmpty() ||
                        binding.etC6.getText().toString().trim().isEmpty() ){
                    Toast.makeText(RegisterActivity.this,"OTP no valido",Toast.LENGTH_LONG).show();
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
                                   // binding.btnVerificacion.setVisibility(View.VISIBLE);
                                    Toast.makeText(RegisterActivity.this,"welcome ", Toast.LENGTH_LONG).show();
                                   // getObtenerDatos();
                                    Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                }else {
                                    binding.btnVerificacion.setVisibility(View.VISIBLE);
                                    Toast.makeText(RegisterActivity.this,"OTP no valido",Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                }
            }
        });
    }
    //Token
    private void otpSend() {
        binding.btnSend.setVisibility(View.INVISIBLE);

        mCallback = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                binding.btnSend.setVisibility(View.VISIBLE);
                Toast.makeText(RegisterActivity.this,e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);

                binding.btnSend.setVisibility(View.VISIBLE);
                Toast.makeText(RegisterActivity.this,"OTP is suceesfuly send ", Toast.LENGTH_LONG).show();
              //  binding.conteinerCheck.setVisibility(View.GONE);
               // binding.coneinerVerificacion.setVisibility(View.VISIBLE);
                 getObtenerDatos();
                Intent intent = new Intent(RegisterActivity.this, VerificacionTwo.class);
                intent.putExtra("phone",binding.editPhone.getText().toString().trim());
                intent.putExtra("verificationId", s);
                startActivity(intent);
            }
        };
        //
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber("+54 0 "+binding.editPhone.getText().toString().trim())
                        .setTimeout(60L, TimeUnit.SECONDS)
                        .setActivity(this)
                        .setCallbacks(mCallback)
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

//
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
    private void getObtenerDatos() {
        String email, password;
        email = String.valueOf(binding.REmail.getText());
        password = String.valueOf(binding.RPassword.getText());

        if (TextUtils.isEmpty(email)){
            Toast.makeText(RegisterActivity.this,"Ingrese Email", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(RegisterActivity.this,"Ingrese Contraseña", Toast.LENGTH_LONG).show();
            return;
        }
        //---
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this,"Cuenta Creada", Toast.LENGTH_LONG).show();

                            Intent i = new Intent(getApplicationContext(),LoginActivity.class);
                            startActivity(i);
                            finish();
                        }else {
                            Toast.makeText(RegisterActivity.this,"Error de Registro", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }


}