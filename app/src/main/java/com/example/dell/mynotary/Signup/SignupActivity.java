package com.example.dell.mynotary.Signup;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.mynotary.AsyncTasks.AsyncResponse;
import com.example.dell.mynotary.AsyncTasks.WebserviceCall;
import com.example.dell.mynotary.Helpers.Const;
import com.example.dell.mynotary.HomeActivity;
import com.example.dell.mynotary.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by DELL on 2/9/2017.
 */

public class SignupActivity extends AppCompatActivity {

    int selectedRole = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        Spinner roleSpinner = (Spinner) findViewById(R.id.signup_spinner_role);
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

        // handling role spinner
        String roles[] = getResources().getStringArray(R.array.role);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,roles);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        roleSpinner.setAdapter(adapter);
        roleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position > 0){
                    selectedRole = position;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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
                    JSONObject object = new JSONObject();
                    try {
                        object.put("firstname",strname);
                        object.put("email", stremail);
                        object.put("password", strpassword);
                        object.put("dob","");
                        object.put("username",strname);
                        object.put("phone",strcno);
                        object.put("role",selectedRole);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    String jsonRequest = String.valueOf(object);
//                    String URL = "http://development.ifuturz.com/core/FLAT_TEST/stone_galary/admin/webservice.php";
                    String URL = "http://www.vnurture.in/pro/registration.php";
                    new WebserviceCall(SignupActivity.this, URL, jsonRequest, "Loading...", true, new AsyncResponse() {
                        @Override
                        public void onCallback(String response) {
                            Log.d("myapp","signup response:" +response);
                            SignupModel model = new Gson().fromJson(response,SignupModel.class);
                            Toast.makeText(SignupActivity.this, model.getMessage(), Toast.LENGTH_SHORT).show();
                            if(model.getSuccess() == 1) {
                                // store id to shared preference for session
                                SharedPreferences.Editor editor = getSharedPreferences(Const.SHAREDPREFERENCE_NAME,MODE_PRIVATE).edit();
                                editor.putString(Const.USER_ID,model.getData().get(0).getId());
                                editor.putInt(Const.ROLE_ID,selectedRole);
                                editor.apply();
                                Intent intent = new Intent(SignupActivity.this, HomeActivity.class);
                                startActivity(intent);
                            }
                        }

                    }).execute();
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