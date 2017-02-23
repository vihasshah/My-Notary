package com.example.dell.mynotary;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    TextView btnsignup;
    EditText usernameET;
    EditText pwdloginET;
    Button btnlogin;
    TextView fgpwdET;
    View dialogView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnsignup = (TextView) findViewById(R.id.main_tv_signup);
       final EditText usernameET = (EditText) findViewById(R.id.edittext_uname_edittext);
       final EditText pwdloginET = (EditText) findViewById(R.id.edittext_pwd_edittext);
        btnlogin=(Button)findViewById(R.id.btn_login_btn);
        fgpwdET=(TextView)findViewById(R.id.main_tv_fpwd);
        fgpwdET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ForgetPasswordActivity.class);
                startActivity(intent);

                showForgotPasswordDialog();
            }
        });


        btnsignup.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);

            }
        });
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String struname = usernameET.getText().toString();
                String strpwd = pwdloginET.getText().toString();


                if (struname.isEmpty())

                {
                    Toast.makeText(LoginActivity.this, "Userame cannot be empty!", Toast.LENGTH_SHORT).show();

                } else if (strpwd.isEmpty())

                {
                    Toast.makeText(LoginActivity.this, "Password cannot be empty", Toast.LENGTH_SHORT).show();

                } else

                {
                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);


                }

            }

        });

    }

    private void showForgotPasswordDialog() {
        dialogView= LayoutInflater.from(this).inflate(R.layout.dailog_forgot_pwd,null);

        AlertDialog alertDialog=new AlertDialog.Builder(this)
                .setTitle("Security Question")
                .setView(dialogView)
                .setPositiveButton("Verify", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        handleText();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        alertDialog.show();

    }

    private void handleText() {
        if (dialogView != null){
            EditText q1 = (EditText) dialogView.findViewById(R.id.forgot_et_q1);
            EditText q2 = (EditText) dialogView.findViewById(R.id.forgot_et_q2);
            Intent intent = new Intent(LoginActivity.this,ForgetPasswordActivity.class);
            startActivity(intent);
        }
    }

}
