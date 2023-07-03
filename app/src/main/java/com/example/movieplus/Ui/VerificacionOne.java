package com.example.movieplus.Ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.movieplus.R;
import com.example.movieplus.databinding.ActivityVerificacionBinding;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class VerificacionOne extends AppCompatActivity {
private ActivityVerificacionBinding binding;
private FirebaseAuth mAuth;
private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVerificacionBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        mAuth = FirebaseAuth.getInstance();

      /*  binding.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.editPhone.getText().toString().trim().isEmpty()){
                    Toast.makeText(VerificacionOne.this,"Invalido el numero", Toast.LENGTH_LONG).show();
                }else if (binding.editPhone.getText().toString().trim().length() != 10){
                    Toast.makeText(VerificacionOne.this,"Type valid phone number", Toast.LENGTH_LONG).show();
                }else {
                    otpSend();
                }
            }
        });*/

    }
/*
    private void otpSend() {
        binding.btnSend.setVisibility(View.INVISIBLE);

        mCallback = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                binding.btnSend.setVisibility(View.VISIBLE);
                Toast.makeText(VerificacionOne.this,e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);

                binding.btnSend.setVisibility(View.VISIBLE);
                Toast.makeText(VerificacionOne.this,"OTP is suceesfuly send ", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(VerificacionOne.this, VerificacionTwo.class);
                intent.putExtra("phone",binding.editPhone.getText().toString().trim());
                intent.putExtra("verificationId", s);
                startActivity(intent);
            }
        };
        //
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber("+54"+binding.editPhone.getText().toString().trim())
                        .setTimeout(60L, TimeUnit.SECONDS)
                        .setActivity(this)
                        .setCallbacks(mCallback)
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }*/
}