package com.vincenzo.example.depeat;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {



        Button loginBtn;
        Button registerBtn;

        EditText emailEt;
        EditText passwordEt;

        Switch aSwitch;
        LinearLayout ll;

        boolean a = false;

        final static int LEN_PASS = 6;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.login_activity);  //richiamare il setContentView, quale layout mostrare

            //inizializzare UI controllers
            loginBtn = findViewById(R.id.login_btn);
            registerBtn = findViewById(R.id.register_btn);
            emailEt = findViewById(R.id.edtxMail);
            passwordEt = findViewById(R.id.edtxPassword);

            aSwitch = findViewById(R.id.switch1);
            ll = findViewById(R.id.linearLayout);


            if(hasInvitationCode())
                registerBtn.setVisibility(View.INVISIBLE);


            loginBtn.setOnClickListener(this);
            registerBtn.setOnClickListener(this);
            aSwitch.setOnClickListener(this);

            /*registerBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("Button", "Register premuto");
                }
            });*/

            loginBtn = findViewById(R.id.login_btn);
            Log.i("MainActivity","Activity created");
        }


        public void doRegister() {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            setContentView(R.layout.register_activity);
            startActivity(intent);
            finish();
        }


        private boolean hasInvitationCode(){
            return false;
        }

        @Override
        public void onClick(View v){
            if(v.getId()==R.id.login_btn){
                //Log.i("Button", "Log premuto");
                doLogin();
            }else if(v.getId()==R.id.register_btn){
                doRegister();
            }
            else if(v.getId()==R.id.switch1){
                ChangeColor(ll);
            }
        }

        private void ChangeColor(LinearLayout ll){
            if(!a) {
                ll.setBackgroundColor(Color.rgb(84,110, 122));
                a = true;
            }
            else {
                ll.setBackgroundColor(Color.WHITE);
                a = false;
            }
        }


        public void doLogin(){

            String email = emailEt.getText().toString();
            String password = passwordEt.getText().toString();
            if(!isEmailValid(email)){
                showToast(R.string.email_hint);
                return;
            }
            if(password.length() < LEN_PASS){
                showToast(R.string.password_hint);
                return;
            }
            /*  if(!isEmailValid(emailEt.getText().toString())){
                showToast(R.string.email_hint);
                return;
        }
        if (passwordEt.getText().toString().length() > LEN_PASS){
            showToast(R.string.password_hint);
        }
*/
            showToast(R.string.credential_ok);

           /*Intent intent = new Intent(LoginActivity.this, WelcomeActivity.class);
            String email2=emailEt.getText().toString();
            intent.putExtra("email",email2);
            startActivity(intent);*/
        }



        private boolean isEmailValid(CharSequence email){
            return Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }

        private void showToast(@StringRes int resId){
            Toast.makeText(this, getString(resId), Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onResume(){
            super.onResume();
            Log.i("Lifecycle", "onResume chiamato");
        }

        @Override
        protected void onRestart(){
            super.onRestart();
            Log.i("Lifecycle", "onRestart chiamato");
        }

        @Override
        protected void onPause(){
            super.onPause();
            Log.i("Lifecycle", "onPause chiamato");
        }

        @Override
        protected void onStop(){
            super.onStop();
            Log.i("Lifecycle", "onStop chiamato");
        }

        @Override
        protected void onDestroy(){
            super.onDestroy();
            Log.i("Lifecycle", "onDestroy chiamato");
        }
    }

