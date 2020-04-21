package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class LoginActicvity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtLoginEmail, edtLoginPassword;
    private Button btnLoginActivity, btnSignUpLoginActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle("Login");

        edtLoginEmail = findViewById(R.id.edtLoginEmail);
        edtLoginPassword = findViewById(R.id.edtLoginPassword);
        btnLoginActivity = findViewById(R.id.btnLoginActivity);
        btnSignUpLoginActivity = findViewById(R.id.btnSignUpLoginActivity);

        //This is used to Login using enter button on the mobile keyPad
        edtLoginPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent event) {

                if (keyCode == KeyEvent.KEYCODE_ENTER &&
                        event.getAction() == KeyEvent.ACTION_DOWN){

                    onClick(btnLoginActivity);

                }

                return false;
            }
        });

        btnLoginActivity.setOnClickListener(this);
        btnSignUpLoginActivity.setOnClickListener(this);

        //it is used for user already logged in to the application
        if(ParseUser.getCurrentUser() != null){

            transitionToSocialMediaActivity();

        }
    }



    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.btnLoginActivity:

                if(edtLoginEmail.getText().toString().equals("")
                         || edtLoginPassword.getText().toString().equals("")){

                    FancyToast.makeText(LoginActicvity.this,"Email, UserName, Password is required!", Toast.LENGTH_SHORT, FancyToast.INFO,true).show();

                }else {

                    ParseUser.logInInBackground(edtLoginEmail.getText().toString(),
                            edtLoginPassword.getText().toString(),
                            new LogInCallback() {
                                @Override
                                public void done(ParseUser user, ParseException e) {

                                    if (user !=null && e == null) {

                                        FancyToast.makeText(LoginActicvity.this, user.getUsername() + " is Logged in successfully", Toast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();
                                        transitionToSocialMediaActivity();

                                    } else {

                                        FancyToast.makeText(LoginActicvity.this, "There is an error: " + e.getMessage(), Toast.LENGTH_SHORT, FancyToast.ERROR, true).show();

                                    }


                                }
                            });
                }

                break;

            case R.id.btnSignUpLoginActivity:

                Intent intent = new Intent(LoginActicvity.this,SignUp.class);
                startActivity(intent);

                break;


        }

    }

    private void transitionToSocialMediaActivity(){

        Intent intent = new Intent(LoginActicvity.this,SocialMediaActivity.class);
        startActivity(intent);
        finish();

    }
}
