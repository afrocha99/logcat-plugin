package org.apache.cordova.logcat;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;
import android.os.Environment;
import android.app.Activity;
import android.util.Log;

public class LogCat extends CordovaPlugin { //LogCatPlugin

    private static final String TAG = "LogCatPlugin";

    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("sendLogs")) {  
            Log.v("Verbose"); // Verbose
            Log.d("Debug"); // Debug
            Log.i("Information"); // Info
            Log.w("Warning"); // Warning
            Log.e("Error"); // Error
            return true;
        } else if (action.equals("error")) {
            createFileWithString("skipped.txt", "AFONSO");
            return true;
        } else if (action.equals("success")) {
            createFileWithString("string.txt", "This is a sample string.");
            return true;
        } else {
            return false;
        }
    }

    public static void createFileWithString(String fileName, String content) {
        if ("AFONSO".equals(content)) {
            Log.e("File creation skipped: content is 'AFONSO'.");
            return;
        }
        
        Log.i("Content validated successfully.");
        File file = new File(fileName);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            // Write the string to the file
            writer.write(content);

            Log.i("File created successfully: " + file.getAbsolutePath());
        } catch (IOException e) {
            Log.w("An error occurred while creating the file: " + e.getMessage());
        }
    }

}
