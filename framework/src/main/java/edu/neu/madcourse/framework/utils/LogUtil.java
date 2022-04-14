package edu.neu.madcourse.framework.utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import androidx.core.app.ActivityCompat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogUtil {
    private static SimpleDateFormat simpleDateFormat =
            new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    /*
    /**
     * Checks if the app has permission to write to device storage
     *
     * If the app does not has permission then the user will be prompted to grant permissions
     *
     * @param activity
     */
    /*
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }*/
    public static void i(String text) throws IOException {
        if(TextUtils.isEmpty(text))
            return;
        Log.i("DEBUG_TAG",text);
        writeToFile(text);
    }
    public static void e(String text){
        if(TextUtils.isEmpty(text))
            return;
        Log.e("ERROR_TAG",text);
    }

    public static void writeToFile(String text) throws IOException {
        String fileName =  "/sdcard/Paws/Paws.log";
        String log = simpleDateFormat.format(new Date())+" "+text;
        File fileGroup = new File(android.os.Environment.getExternalStorageDirectory(),"/sdcard/Paws/");
        if(!fileGroup.exists())
            fileGroup.createNewFile();
        if(fileGroup.exists())
            Log.i("File_exists","ex");
        else
            Log.i("File_exists","NOT");
        BufferedWriter bufferedWriter =null;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileName, true);
            bufferedWriter = new BufferedWriter(
                    new OutputStreamWriter(fileOutputStream));
            bufferedWriter.write(log);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(bufferedWriter!=null){
                try{
                    bufferedWriter.close();
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
