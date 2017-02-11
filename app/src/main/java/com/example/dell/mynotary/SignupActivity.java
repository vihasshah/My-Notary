package com.example.dell.mynotary;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by DELL on 2/9/2017.
 */

public class SignupActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        final EditText nameET=(EditText)findViewById(R.id.edittext_name_edittext);
        final EditText emailET=(EditText)findViewById(R.id.edittext_email_edittext);
        final EditText lastnameET=(EditText)findViewById(R.id.edittext_lastname_edittext);
        final EditText passwordET=(EditText)findViewById(R.id.edittext_password_edittext);
        final EditText cpET=(EditText)findViewById(R.id.edittext_cp_edittext);
        final TextView genderTV=(TextView) findViewById(R.id.textview_gender_textview);
        final RadioButton maleRB=(RadioButton)findViewById(R.id.radiobutton_male_radiobutton) ;
        final RadioButton femaleRB=(RadioButton)findViewById(R.id.radiobutton_female_radiobutton) ;
        final EditText cnoET=(EditText)findViewById(R.id.edittext_cn_edittext);
        Button btnsignup;




        btnsignup=(Button) findViewById(R.id.signup_btn_signup);
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strname=nameET.getText().toString();
                String strlastname=lastnameET.getText().toString();
                String stremail=emailET.getText().toString();
                String strpassword=passwordET.getText().toString();
                String strcp=cpET.getText().toString();
                String strgender=genderTV.getText().toString();
                {
                    if(maleRB.isChecked())
                    {
                        maleRB.getText().toString();
                    }
                    else
                    {
                        femaleRB.getText().toString();
                    }

                }
                // String strmale=maleRB.getText().toString();
                //String strfemale=femaleRB.getText().toString();
                String strcno=cnoET.getText().toString();
                if(strname.isEmpty())
                {
                    Toast.makeText(SignupActivity.this, "Name cannot be empty!", Toast.LENGTH_SHORT).show();

                }
                else if (strlastname.isEmpty())
                {
                    Toast.makeText(SignupActivity.this, "lastname cannot be empty", Toast.LENGTH_SHORT).show();

                }
                else if (stremail.isEmpty())
                {
                    Toast.makeText(SignupActivity.this, "Enter email", Toast.LENGTH_SHORT).show();
                }

                else if (!isValidEmail(stremail)){
                    Toast.makeText(SignupActivity.this, "Enter valid email", Toast.LENGTH_SHORT).show();
                }
                else if (strpassword.isEmpty())
                {
                    Toast.makeText(SignupActivity.this, "please enter password", Toast.LENGTH_SHORT).show();
                }
                else if (strcp.isEmpty())
                {
                    Toast.makeText(SignupActivity.this, "please enter confirm password", Toast.LENGTH_SHORT).show();
                }
                else if (strcno.isEmpty())
                {
                    Toast.makeText(SignupActivity.this, "please enter contact number", Toast.LENGTH_SHORT).show();
                }
                else if (strgender.isEmpty())
                {
                    Toast.makeText(SignupActivity.this, "enter your gender", Toast.LENGTH_SHORT).show();
                }
                else if (!strcp.equals(strpassword))
                {
                    Toast.makeText(SignupActivity.this, "passwords don't match", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Toast.makeText(SignupActivity.this, "Congratulations!Signed up successfully", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }

    public static boolean isValidEmail(CharSequence target) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(target);
        return matcher.matches();
    }

}