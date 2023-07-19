package com.cba.thisurakarunanayaka.utilities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Constants {
    public final static String DATABASE_NAME = "cba_assessment";
    public final static String BASE_URL ="http://123.231.9.43:3999";


    public Constants() {
    }
    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static void showAlertBox(Context context, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Alert"); // Set the title of the alert box (optional)
        builder.setMessage(message); // Set the message to be displayed in the alert box

        // Set a positive button with a click listener
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Perform any action if needed when the user clicks "OK"
                dialog.dismiss(); // Dismiss the alert box
            }
        });

        // Create and show the alert dialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}

