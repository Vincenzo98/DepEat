package com.vincenzo.example.depeat.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
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

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.vincenzo.example.depeat.R;
import com.vincenzo.example.depeat.datamodels.Shop;
import com.vincenzo.example.depeat.datamodels.User;
import com.vincenzo.example.depeat.services.RestController;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, Response.Listener<String>, Response.ErrorListener{

    private static final String TAG = LoginActivity.class.getSimpleName();


        Button loginBtn;
        Button registerBtn;

        EditText emailEt;
        EditText passwordEt;

        Switch aSwitch;
        LinearLayout ll;

        boolean a = false;

        final static int LEN_PASS = 6;

        private RestController restController;

        public static final String EMAIL_KEY = "email";

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            restController = new RestController(this);

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


            loginBtn = findViewById(R.id.login_btn);
            Log.i("MainActivity","Activity created");


        }



        public void doRegister() {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
           // setContentView(R.layout.register_activity);
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

            showToast(R.string.credential_ok);

            Map<String, String> params = new HashMap<>();
            params.put("identifier", email);
            params.put("password", password);

            restController.postRequest(User.LOGIN_ENDPOINT, params, this,this);
            showToast(R.string.eseguito);

        }


        private boolean isEmailValid(CharSequence email){
            return Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }

        private void showToast(@StringRes int resId){
            Toast.makeText(this, getString(resId), Toast.LENGTH_SHORT).show();
        }

        private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
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

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.e(TAG, error.getMessage());
        showToast(error.getMessage());
    }


    @Override
    public void onResponse(String response) {
            try{
                JSONObject jsonObject = new JSONObject(response);
                Log.i("jsonInformazione", jsonObject.toString());
            }catch (JSONException ex){
                Log.e("erroreeeee", ex.getMessage());
            }


        Intent i = new Intent();
        i.putExtra("response",response);
        setResult(Activity.RESULT_OK,i);
        finish();

    }
}

