package com.example.dell.mynotary.Material;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dell.mynotary.AsyncTasks.AsyncResponse;
import com.example.dell.mynotary.AsyncTasks.WebserviceCall;
import com.example.dell.mynotary.Helpers.Const;
import com.example.dell.mynotary.Helpers.Utils;
import com.example.dell.mynotary.R;
import com.google.gson.Gson;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.tonyodev.fetch.Fetch;
import com.tonyodev.fetch.listener.FetchListener;
import com.tonyodev.fetch.request.Request;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class MaterialActivity extends AppCompatActivity {
    private static final int PICKFILE_REQUEST_CODE = 1;
    Fetch fetch;
    ProgressDialog progressDialog;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Materials");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("application/pdf");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select PDF"), 1);

                //for testing only
//                String url = "http://www.vnurture.in/pro/fetchmaterial.php";
//                String jsonRequest = "{'userid':'1'}";
//                new WebserviceCall(MaterialActivity.this, url, jsonRequest, "Loading...", true, new AsyncResponse() {
//                    @Override
//                    public void onCallback(String response) {
//                        progressDialog = new ProgressDialog(MaterialActivity.this);
//                        progressDialog.setMessage("Downloading Music");
//                        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//                        progressDialog.setIndeterminate(true);
//                        progressDialog.show();
//                        MaterialModel model = new Gson().fromJson(response,MaterialModel.class);
//                        fetch = Fetch.getInstance(MaterialActivity.this);
//                        String fileURL = "http://www.vnurture.in/pro/notaryfiles/"+model.getData().get(0).getFile();
//                        File f = new File(Environment.getExternalStorageDirectory(), "My Notary");
//                        Request request = new Request(fileURL,f.getAbsolutePath(),"img.jpg");
//                        long downloadId = fetch.enqueue(request);
//                        Log.d("myapp","re id "+ downloadId);
//
//                        fetch.addFetchListener(new FetchListener() {
//                            @Override
//                            public void onUpdate(long id, int status, int progress, long downloadedBytes, long fileSize, int error) {
//                                if(status == Fetch.STATUS_DOWNLOADING) {
//                                    progressDialog.setProgress(progress);
//                                    Log.d("myapp", "progressDialog:" + progress);
//                                }else if(status == Fetch.STATUS_DONE){
//                                    progressDialog.dismiss();
//                                    Toast.makeText(MaterialActivity.this, "Download Completed", Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        });
//                    }
//                }).execute();
            }
        });
        // listview
        listView = (ListView) findViewById(R.id.material_list_view);
        String url = "http://www.vnurture.in/pro/fetchmaterial.php";
        String userid = getSharedPreferences(Const.SHAREDPREFERENCE_NAME,MODE_PRIVATE).getString(Const.USER_ID,null);
        String jsonRequest = Utils.createJsonRequest(new String[]{"userid"},new String[]{userid});
        Log.d("myapp",jsonRequest);
        new WebserviceCall(this, url, jsonRequest, "Loading...", true, new AsyncResponse() {
            @Override
            public void onCallback(String response) {
                MaterialModel model = new Gson().fromJson(response,MaterialModel.class);
                if(model.getSuccess() == 1) {
                    MaterialAdapter adapter = new MaterialAdapter(MaterialActivity.this, model.getData());
                    listView.setAdapter(adapter);
                }else{
                    Toast.makeText(MaterialActivity.this, model.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }).execute();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == PICKFILE_REQUEST_CODE){
            if(resultCode == RESULT_OK){
                Uri path = data.getData();
//                Log.d("myapp",path.toString());
                String fileName = getFileName(path);
                // file upload path
                String sourceFileUri = Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+Const.DIR_NAME+"/";
                // file upload name
                String uploadFileName = fileName;
                String serverUri = "www.vnurture.in/pro/fileupload.php";
                String uploadFilePath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+Const.DIR_NAME+"/"+fileName;
                Log.d("myapp","absolute path: "+Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+Const.DIR_NAME+"/"+fileName);
                new Utils.FileUploadInBackground(MaterialActivity.this,sourceFileUri,uploadFilePath,uploadFileName,serverUri).execute();
                (new Upload(MaterialActivity.this, path)).execute();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(fetch != null){
            fetch.release();
        }
    }


    class Upload extends AsyncTask<Void, Void, Void> {
        private ProgressDialog pd;
        private Context c;
        private Uri path;
//        private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("text/pdf");
        private MediaType JSON = MediaType.parse("application/json;charset=utf-8");
        public Upload(Context c, Uri path) {
            this.c = c;
            this.path = path;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = ProgressDialog.show(c, "Uploading", "Please Wait");
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            pd.dismiss();
        }

        @Override
        protected Void doInBackground(Void... params) {
            File f = new File(URI.create(path.toString()));
            Log.d("myapp","file path:"+f.getAbsolutePath());
            String fileName = getFileName(path); // get file name
            OkHttpClient client = new OkHttpClient();
            String userid = getSharedPreferences(Const.SHAREDPREFERENCE_NAME,MODE_PRIVATE).getString(Const.USER_ID,null);
//            RequestBody requestBody = new MultipartBuilder()
//                    .type(MultipartBuilder.FORM)
//                    .addPart("file", f.getName(),
//                            RequestBody.create(MediaType.parse("text/pdf"), fileName)

//            RequestBody body = RequestBody.create(JSON,Utils.createJsonRequest(new String[]{"userid","file"},new String[]{userid,fileName}));
//            com.squareup.okhttp.Request request = new com.squareup.okhttp.Request.Builder()
//                    .post(body)
//                    .url(Const.UPLOAD_DB_URL)
//                    .build();
//            Response response = null;
//            try {
//                response = client.newCall(request).execute();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            try {
//                JSONObject object = new JSONObject(response.body().string());
//                if(object.getInt("success") == 1){
//                    //upload file

//                    String url_path = "http://www.vnurture.in/pro/fileupload.php";
//                    HttpURLConnection conn = null;
//
//                    int maxBufferSize = 1024;
//                    try {
//                        URL url = new URL(url_path);
//                        conn = (HttpURLConnection) url.openConnection();
//                        conn.setDoOutput(true);
//                        conn.setUseCaches(false);
//                        conn.setDoInput(true);
//                        conn.setChunkedStreamingMode(1024);
//                        conn.setRequestMethod("POST");
//                        conn.setRequestProperty("Connection", "Keep-Alive");
//                        conn.setRequestProperty("Content-Type", "multipart/form-data");
//                        conn.setRequestProperty("uploaded_file", fileName+".pdf");
//
//                        OutputStream outputStream = conn.getOutputStream();
//                        InputStream inputStream = getContentResolver().openInputStream(path);
//
//                        int bytesAvailable = inputStream.available();
//                        int bufferSize = Math.min(bytesAvailable, maxBufferSize);
//                        byte[] buffer = new byte[bufferSize];
//
//                        int bytesRead;
//                        while ((bytesRead = inputStream.read(buffer, 0, bufferSize)) != -1) {
//                            outputStream.write(buffer, 0, bytesRead);
//                        }
//                        outputStream.flush();
//                        inputStream.close();
//
//                        BufferedReader reader = new BufferedReader(new InputStreamReader(
//                                conn.getInputStream()));
//                        String line;
//                        while ((line = reader.readLine()) != null) {
//                            Log.d("myapp",line);
////                            decodeFileUploadJson(line);
//                        }
//                        reader.close();
//                        conn.disconnect();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    } finally {
//                        if (conn != null) {
//                            conn.disconnect();
//                        }
//                    }
//                }else{
//                    MaterialActivity.this.runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            Toast.makeText(MaterialActivity.this, "Upload Fail", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

            return null;
        }
    }

    private String getFileName(Uri path) {
        String[] segments = path.getPath().split("/");
        String file = segments[segments.length-1];
        return file;
//        Log.d("myapp","file: "+file);
//        String splitFileName[] = file.split("\\.");
//        Log.d("myapp",splitFileName.length+"");
//        return splitFileName[0];
    }

    private void decodeFileUploadJson(String line) {
        try {
            JSONObject object = new JSONObject(line);
            if(line == null){
                Toast.makeText(this, "File Upload Failed", Toast.LENGTH_SHORT).show();
            }else if(object.getInt("success") == 1){
                Toast.makeText(this, "File Uploaded", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
