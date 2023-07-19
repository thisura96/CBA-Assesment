package com.cba.thisurakarunanayaka.utilities;

import android.app.ProgressDialog;
import android.content.Context;

import com.cba.thisurakarunanayaka.R;

public class ProgressBars {

    private ProgressDialog progress;

    public void showProgress(Context context, String title, String message) {
        progress = new ProgressDialog(context);
        progress.setTitle(title);
        progress.setMessage(message);
        progress.setContentView(R.layout.custom_progress_dialog);
        progress.getWindow().setBackgroundDrawableResource(android.R.color.white);
        progress.setIndeterminate(true);
        progress.setCancelable(false);
        progress.setProgress(0);
        progress.show();
    }

    // dismiss spinning progress bar
    public void dismissProgress() {
        if (progress != null && progress.isShowing()
                && progress.getWindow() != null) {
            try {
                progress.dismiss();
            } catch (IllegalArgumentException ignore) {

            }
        }
        progress = null;
    }
}
