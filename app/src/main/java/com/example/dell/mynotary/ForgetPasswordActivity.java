package com.example.dell.mynotary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ForgetPasswordActivity extends AppCompatActivity {
    Button btnchange;
    EditText passwordET;
    EditText confirmpasswordET;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        btnchange = (Button) findViewById(R.id.btn_fgpwd_change);
        final EditText passwordET = (EditText) findViewById(R.id.forgotpwd_et_pwd);
        final EditText confirmpasswordET = (EditText) findViewById(R.id.forgotpwd_et_cpwd);

        btnchange=(Button)findViewById(R.id.btn_fgpwd_change);
        btnchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strpassword = passwordET.getText().toString();
                String strconfirmpassword = confirmpasswordET.getText().toString();


                if (strpassword.isEmpty())

                {
                    Toast.makeText(ForgetPasswordActivity.this, "password cannot be empty!", Toast.LENGTH_SHORT).show();

                } else if (strconfirmpassword
                        .isEmpty())

                {
                    Toast.makeText(ForgetPasswordActivity.this, "confirm password cannot be empty", Toast.LENGTH_SHORT).show();

                } else if (!strconfirmpassword.equals(strpassword)) {
                    Toast.makeText(ForgetPasswordActivity.this, "password don't match", Toast.LENGTH_SHORT).show();
                } else

                {
                    Toast.makeText(ForgetPasswordActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ForgetPasswordActivity.this, LoginActivity.class);
                    startActivity(intent);


                }

            }

        });

    }

}




