package com.example.dell.mynotary.AsyncTasks;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.util.Log;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


/**
 * Created by Dell on 11-01-2017.
 */

public class WebserviceCall extends AsyncTask<Void,Void,String>{
    // interface for response

    AsyncResponse delegate;
    private final MediaType URLENCODE = MediaType.parse("application/json;charset=utf-8");
    ProgressDialog dialog;
    Context context;
    String dialogMessage;
    boolean showDialog = true;
    String URL;
    String jsonBody;
    public WebserviceCall(Context context, String URL, String jsonRequestBody, String dialogMessage, boolean showDialog, AsyncResponse delegate){
        this.context = context;
        this.URL = URL;
        this.jsonBody = jsonRequestBody;
        this.dialogMessage = dialogMessage;
        this.showDialog = showDialog;
        this.delegate = delegate;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if(showDialog) {
            dialog = new ProgressDialog(context);
            dialog.setMessage(dialogMessage);
            dialog.show();
        }
    }

    /**
     * Web service call okhttp
     * @param params url, request body in string respectively
     * @return String response
     */
    @Override
    protected String doInBackground(Void... params) {

        // creating okhttp client
        OkHttpClient client = new OkHttpClient();
       // client.setConnectTimeout(10L, TimeUnit.SECONDS);
        // creating request body
        RequestBody body;
        if(jsonBody != null) {
            body = RequestBody.create(URLENCODE, jsonBody);
        }else{
            body = null;
        };
        // creating request
        Request request = new Request.Builder()
                .post(body)
                .url(URL)
                .build();

        // creating webserivce call and get response

        try {
            Response response = client.newCall(request).execute();
            String res = response.body().string();
            Log.d("myapp",res);
            return res;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if(showDialog){
            if(dialog.isShowing()){
                dialog.dismiss();
            }
        }
        if(s != null){
            // set value to AsyncResponse interface for further proccess in activity
            //  Log.d("myapp",getClass().getSimpleName()+" "+s);
            if(delegate != null) {
                try {
                    JSONObject object = new JSONObject(s);
                    // check if json has value or not
                    if(object.length() > 0){
                        // if json object is not null
                        if(object.getInt("success") == 1){
                            // success
                            delegate.onSuccess(object.getString("message"),object.getJSONArray("data"));
                        }else{
                            // failure
                            delegate.onFailure(object.getString("Fail"));
                        }
                    }else{
                        // failure
                        delegate.onFailure(object.getString("Fail"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else{
                // failure
                delegate.onFailure("Null Response");
            }
        }else{
            Log.d("myapp",getClass().getSimpleName()+": response null");
        }
    }

}
