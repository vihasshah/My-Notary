package com.example.dell.mynotary.Material;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.mynotary.AsyncTasks.AsyncResponse;
import com.example.dell.mynotary.AsyncTasks.WebserviceCall;
import com.example.dell.mynotary.Helpers.Const;
import com.example.dell.mynotary.R;
import com.github.ivbaranov.mli.MaterialLetterIcon;
import com.google.gson.Gson;
import com.tonyodev.fetch.Fetch;
import com.tonyodev.fetch.listener.FetchListener;
import com.tonyodev.fetch.request.Request;

import java.io.File;
import java.util.List;

/**
 * Created by Vihas on 23-03-2017.
 */

public class MaterialAdapter extends BaseAdapter {
    private Context context;
    private List<MaterialModel.DataBean> dataBeanList;
    private Fetch fetch;
    private ProgressDialog progressDialog;
    public MaterialAdapter(Context context, List<MaterialModel.DataBean> dataBeanList) {
        this.context = context;
        this.dataBeanList = dataBeanList;
    }

    @Override
    public int getCount() {
        return dataBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder{
        MaterialLetterIcon icon;
        TextView fileNameTV,uploadByTV;
        ImageView downloadIV;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.single_row_material,parent,false);
            holder.icon = (MaterialLetterIcon) convertView.findViewById(R.id.material_icon);
            holder.fileNameTV = (TextView) convertView.findViewById(R.id.material_file_name);
            holder.downloadIV = (ImageView) convertView.findViewById(R.id.material_download);
            holder.uploadByTV = (TextView) convertView.findViewById(R.id.material_uploaded_by);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        final String fileName = dataBeanList.get(position).getFile();
        holder.icon.setLetter(String.valueOf(fileName.charAt(0)));
        holder.fileNameTV.setText(fileName);
        String uploadStr ="Uploaded By "+dataBeanList.get(position).getFirstname()+" "+dataBeanList.get(position).getLastname();
        holder.uploadByTV.setText(uploadStr);
        holder.downloadIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File dir = new File(Environment.getExternalStorageDirectory(),Const.DIR_NAME);
                if(!dir.exists()){
                    dir.mkdir();
                }
                String file = fileName+"."+ Const.FILE_EXTENSION;
                if(fileExists(file)){
                    Toast.makeText(context, "File exists", Toast.LENGTH_SHORT).show();
                }else{
                    downloadFile(file);
                }
            }
        });
        return convertView;
    }

    /***
     * Check download file
     * @param fileName
     * @return boolean
     */
    private void downloadFile(final String fileName) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Downloading pdf");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setIndeterminate(true);
        progressDialog.show();
        fetch = Fetch.getInstance(context);
        String fileURL = "http://www.vnurture.in/pro/notaryfiles/"+fileName;
        String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+Const.DIR_NAME+"/";
        Request request = new Request(fileURL,dirPath,fileName);
        Log.d("myapp","file ppath:"+dirPath);
        long downloadId = fetch.enqueue(request);
        try {
            fetch.addFetchListener(new FetchListener() {
                @Override
                public void onUpdate(long id, int status, int progress, long downloadedBytes, long fileSize, int error) {
                    if (status == Fetch.STATUS_DOWNLOADING) {
                        progressDialog.setProgress(progress);
                        Log.d("myapp", "progressDialog:" + progress);
                    } else if (status == Fetch.STATUS_DONE) {
                        progressDialog.dismiss();
                        Toast.makeText(context, "Download Completed", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /***
     * check for file if exists or not
     * @param fileName
     * @return boolean
     */
    private boolean fileExists(String fileName){
        Log.d("myapp","file path:" +Environment.getExternalStorageDirectory().getAbsoluteFile()+"/"+Const.DIR_NAME+"/"+fileName);
        File pdfFile = new File(Environment.getExternalStorageDirectory().getAbsoluteFile()+"/"+Const.DIR_NAME+"/"+fileName);
        if(pdfFile.exists()){
            Log.d("myapp","file exists");
            return true;
        }
        Log.d("myapp","file not exists");
        return false;
    }
}
