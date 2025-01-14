package org.apache.cordova.logcat;

import android.content.Context;
import android.util.Log;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LogCat extends CordovaPlugin {

    private static final String TAG = "LogCatPlugin";

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if ("sendLogs".equals(action)) {
            logExample();
            callbackContext.success("Logs sent.");
            return true;
        } else if ("error".equals(action)) {
            createFileWithString("skipped.txt", "AFONSO", callbackContext);
            return true;
        } else if ("success".equals(action)) {
            createFileWithString("string.txt", "This is a sample string.", callbackContext);
            return true;
        } else {
            callbackContext.error("Invalid action.");
            return false;
        }
    }

    private void logExample() {
        Log.v(TAG, "Verbose log");
        Log.d(TAG, "Debug log");
        Log.i(TAG, "Information log");
        Log.w(TAG, "Warning log");
        Log.e(TAG, "Error log");
    }

    private void createFileWithString(String fileName, String content, CallbackContext callbackContext) {
        if ("AFONSO".equals(content)) {
            Log.e(TAG, "File creation skipped: content is 'AFONSO'.");
            callbackContext.error("File creation skipped: content is 'AFONSO'.");
            return;
        }

        Log.i(TAG, "Content validated successfully.");

        Context context = this.cordova.getActivity().getApplicationContext();
        File file = new File(context.getFilesDir(), fileName);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(content);
            Log.i(TAG, "File created successfully: " + file.getAbsolutePath());
            callbackContext.success("File created successfully at: " + file.getAbsolutePath());
        } catch (IOException e) {
            Log.w(TAG, "An error occurred while creating the file: " + e.getMessage());
            callbackContext.error("Error creating file: " + e.getMessage());
        }
    }
}
