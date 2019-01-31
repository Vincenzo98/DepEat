package com.vincenzo.example.depeat.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.vincenzo.example.depeat.R;


public class RegisterActivity extends AppCompatActivity {



    EditText email;
    EditText password;
    EditText phonenmb;

    Boolean emailtrue = false;
    Boolean passwordTrue = false;
    Boolean phoneTrue = false;
    Button register;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        email = findViewById(R.id.edtxMail1);
        password = findViewById(R.id.edtxPassword1);
        phonenmb = findViewById(R.id.edtxPhone);
        register =  findViewById(R.id.register_btn);

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.i( "before", s.toString());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.i("now", s.toString());   // per vedere cosa stiamo facendo

                String email1 = email.getText().toString();
                checkemail(email1);
                btncheck(register);
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.i("after", s.toString());
            }
        });


        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.i( "before", s.toString());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.i("now", s.toString());

                String password1 = password.getText().toString();
                checkpass(password1);
                btncheck(register);
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.i("after", s.toString());
            }
        });


        phonenmb.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.i( "before", s.toString());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.i("now", s.toString());

                String phone1 = phonenmb.getText().toString();
                checkphone(phone1);
                btncheck(register);
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.i("after", s.toString());
            }
        });


    }
    private void checkemail(String email){
        if(email.contains("@") && email.contains(".") && email.length() > 6)
            emailtrue = true;
        else
            emailtrue = false;

        //       oppure     return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void checkpass(String password){
        if(password.length() > 6 )
            passwordTrue = true;
        else
            passwordTrue = false;
    }

    private void checkphone(String phonenmb){
        if(phonenmb.length() >6)
            phoneTrue = true;
        else
            phoneTrue = false;
    }

    private void btncheck (Button button){
        if(emailtrue  && passwordTrue && phoneTrue )
            button.setEnabled(true);
        else
            button.setEnabled(false);
    }
}
