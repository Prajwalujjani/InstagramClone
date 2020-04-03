package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUP extends AppCompatActivity implements View.OnClickListener {

    private Button btnSave;
    private EditText edtName, edtPunchSpeed, edtPunchPower, edtKickSpeed, edtKickPower;

    private Button btnTransition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(SignUP.this);
        edtName = findViewById(R.id.edtName);
        edtPunchSpeed = findViewById(R.id.edtPunchSpeed);
        edtPunchPower = findViewById(R.id.edtPunchPower);
        edtKickSpeed = findViewById(R.id.edtKickSPeed);
        edtKickPower = findViewById(R.id.edtKickPower);

        btnTransition = findViewById(R.id.btnNextActivity);


        Parse.enableLocalDatastore(SignUP.this);
        Parse.initialize(new Parse.Configuration.Builder(SignUP.this)
                .applicationId("RdsQy0ti78FX3eK2BKDU95sElQjlOiF1gUeLxfwU")
                .clientKey("KyRxL2Ha8NThfDqwlEFlyPHC5t1AgW28aoYFo4iT")
                .server("https://parseapi.back4app.com/")
                .enableLocalDataStore()
                .build());


        btnTransition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SignUP.this, SignUpLoginActivity.class);
                startActivity(intent);


            }
        });

    }

    //  public void helloWorldTapped(View view){

//        ParseObject boxer = new ParseObject("Boxer");
//        boxer.put("punch_speed",200);
//        boxer.saveInBackground(new SaveCallback() {
//            @Override
//            public void done(ParseException e) {
//                if (e == null) {
//
//                    Toast.makeText(SignUP.this, "Boxer object is saved successfully", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

    //   }
    @Override
    public void onClick(View v) {

        try {
            final ParseObject kickBoxer = new ParseObject("KickBoxer");
            kickBoxer.put("name", edtName.getText().toString());
            kickBoxer.put("punchSpeed",Integer.parseInt(edtPunchSpeed.getText().toString().trim()));
            kickBoxer.put("punchPower",Integer.parseInt(edtPunchPower.getText().toString().trim()));
            kickBoxer.put("kickSpeed",Integer.parseInt(edtKickSpeed.getText().toString().trim()));
            kickBoxer.put("kickPower",Integer.parseInt(edtKickPower.getText().toString().trim()));
            kickBoxer.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        FancyToast.makeText(SignUP.this, kickBoxer.get("Name") + " saved tp server", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                    } else {

                        FancyToast.makeText(SignUP.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();

                    }
                }
            });
        } catch (Exception e) {

            FancyToast.makeText(SignUP.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();


        }

    }
}
