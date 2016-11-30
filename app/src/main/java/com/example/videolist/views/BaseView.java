package com.example.videolist.views;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public abstract class BaseView extends LinearLayout {
    private ProgressDialog progress;

    public BaseView(Context context) {
        super(context);
    }

    public BaseView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public BaseView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /**
     * Method that notifies about progress dialog interruption
     */
    public abstract void onProgressCanceled();

    /**
     * Method to show progress dialog with text
     * @param message String message that will be shown while progress dialog spinning
     */
    public void showProgress(String message) {
        if (null == progress)
            progress = new ProgressDialog(getContext());
        progress.setMessage(message);
        progress.setCancelable(true);
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.show();
        progress.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                onProgressCanceled();
            }
        });
    }

    /**
     * Method to hide current progress dialog
     */
    public void hideProgress() {
        if (null != progress)
            progress.dismiss();

        progress = null;
    }

}
