package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private EditText edtEmail, edtUserName, edtPassword;
    private Button btnSignUp, btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        setTitle("Sign Up");

        edtEmail = findViewById(R.id.edtEnterEmail);
        edtUserName = findViewById(R.id.edtUserName);
        edtPassword = findViewById(R.id.edtEnterPassword);


        //This is used to signup using enter button on the mobile keyPad
        edtPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent event) {

                if (keyCode == KeyEvent.KEYCODE_ENTER &&
                        event.getAction() == KeyEvent.ACTION_DOWN) {

                    onClick(btnSignUp);

                }

                return false;
            }
        });

        btnSignUp = findViewById(R.id.btnSignUp);
        btnLogin = findViewById(R.id.btnLogin);

        Parse.enableLocalDatastore(SignUp.this);
        Parse.initialize(new Parse.Configuration.Builder(SignUp.this)
                .applicationId("kpbEsiZqg67OV5WmNVUdFJV4syUHmEtYsKHngWqj")
                .clientKey("1XYARHsgW0IIooV91nsvL5O2ASyqTQzj2K40eYBY")
                .server("https://parseapi.back4app.com/")
                .enableLocalDataStore()
                .build());


        btnSignUp.setOnClickListener(this);
        btnLogin.setOnClickListener(this);


        if (ParseUser.getCurrentUser() != null) {

            transitionToSocialMediaActivity();
        }

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnSignUp:

                if (edtEmail.getText().toString().equals("")
                        || edtUserName.getText().toString().equals("") ||
                        edtPassword.getText().toString().equals("")) {

                    FancyToast.makeText(SignUp.this, "Email, UserName, Password is required!", Toast.LENGTH_SHORT, FancyToast.INFO, true).show();
                } else {

                    final ParseUser appUser = new ParseUser();
                    appUser.setEmail(edtEmail.getText().toString());
                    appUser.setUsername(edtUserName.getText().toString());
                    appUser.setPassword(edtPassword.getText().toString());
                    final ProgressDialog progressDialog = new ProgressDialog(this);
                    progressDialog.setMessage("Signing up" + edtUserName.getText().toString());
                    progressDialog.show();

                    appUser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {

                                FancyToast.makeText(SignUp.this, appUser.getUsername() + " is signed up", Toast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();
                                transitionToSocialMediaActivity();

                            } else {

                                FancyToast.makeText(SignUp.this, "There is an error: " + e.getMessage(), Toast.LENGTH_SHORT, FancyToast.ERROR, true).show();

                            }

                            progressDialog.dismiss();


                        }
                    });
                }

                break;

            case R.id.btnLogin:

                Intent intent = new Intent(SignUp.this, LoginActicvity.class);
                startActivity(intent);

                break;

        }

    }

    public void rootLayOutIsTapped(View view) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    private void transitionToSocialMediaActivity(){

        Intent intent = new Intent(SignUp.this,SocialMediaActivity.class);
        startActivity(intent);
    }
}