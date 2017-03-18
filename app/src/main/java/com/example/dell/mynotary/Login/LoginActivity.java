package com.example.dell.mynotary.Login;

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

import com.example.dell.mynotary.AsyncTasks.AsyncResponse;
import com.example.dell.mynotary.AsyncTasks.WebserviceCall;
import com.example.dell.mynotary.Helpers.Const;
import com.example.dell.mynotary.HomeActivity;
import com.example.dell.mynotary.R;
import com.example.dell.mynotary.Signup.SignupActivity;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    TextView btnsignup;
    Button btnlogin;
    TextView fgpwdET;
    View dialogView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnsignup = (TextView) findViewById(R.id.main_tv_signup);
       final EditText emailET = (EditText) findViewById(R.id.edittext_email_edittext);
       final EditText pwdloginET = (EditText) findViewById(R.id.edittext_pwd_edittext);
        btnlogin=(Button)findViewById(R.id.btn_login_btn);
        fgpwdET=(TextView)findViewById(R.id.main_tv_fpwd);
        fgpwdET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

                String stremail = emailET.getText().toString();
                String strpwd = pwdloginET.getText().toString();



                if (stremail.isEmpty())

                {
                    Toast.makeText(LoginActivity.this, "Email cannot be empty!", Toast.LENGTH_SHORT).show();

                } else if (strpwd.isEmpty())

                {
                    Toast.makeText(LoginActivity.this, "Password cannot be empty", Toast.LENGTH_SHORT).show();

                } else

                {
                    //Toast.makeText(LoginActivity.this, "Unauthorized user", Toast.LENGTH_SHORT).show();

                    JSONObject object = new JSONObject();
                    try {
//                        object.put("mode","loginUser");
                        object.put("email", stremail);
                        object.put("password", strpwd);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    String jsonRequest = String.valueOf(object);
//                    String URL = "http://development.ifuturz.com/core/FLAT_TEST/stone_galary/admin/webservice.php";
                    String URL = "http://www.vnurture.in/pro/login.php";
                    new WebserviceCall(LoginActivity.this, URL, jsonRequest, "Loading...", true,new AsyncResponse() {

                        @Override
                        public void onCallback(String response) {
                            LoginModel model = new Gson().fromJson(response,LoginModel.class);
                            if(model.getSuccess() == 1){
                                // login successful
                                Toast.makeText(LoginActivity.this, model.getMessage(), Toast.LENGTH_SHORT).show();
                                // store id to sharedpreference
                                getSharedPreferences(Const.SHAREDPREFERENCE_NAME,MODE_PRIVATE).edit().putString(Const.USER_ID, model.getData().get(0).getId()).apply();
                                // go to home activity
                                Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                                startActivity(intent);
                            }else{
                                // login unsuccessful
                                Toast.makeText(LoginActivity.this, model.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }

                    }).execute();

                }

            }

        });

}

    private void showForgotPasswordDialog() {
        dialogView= LayoutInflater.from(this).inflate(R.layout.dailog_forgot_pwd,null);

        AlertDialog alertDialog=new AlertDialog.Builder(this)
                .setTitle("Email")
                .setView(dialogView)
                .setPositiveButton("Verify", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        handleDialog(dialogView);
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

    private void handleDialog(View dialogView) {
        EditText verifyEmailET = (EditText) dialogView.findViewById(R.id.forgot_et_email);
        String stremail = verifyEmailET.getText().toString();
        JSONObject object = new JSONObject();
        try {
            object.put("mode","forgotPassword");
            object.put("emailId", stremail);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String jsonRequest = String.valueOf(object);
//        String URL = "http://development.ifuturz.com/core/FLAT_TEST/stone_galary/admin/webservice.php";
        String URL = "http://www.vnurture.in/pro/mailtest.php";
        new WebserviceCall(LoginActivity.this, URL, jsonRequest, "Loading...", true,new AsyncResponse() {
            @Override
            public void onCallback(String response) {
//                LoginModel model = new Gson().fromJson(response,LoginModel.class);
//                if(model.getSuccess() == 1) {
//
////                Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
//                    startActivity(intent);
//                }else{
//                    Toast.makeText(LoginActivity.this, model.getMessage(), Toast.LENGTH_SHORT).show();
//                }
            }

        }).execute();
    }


}
