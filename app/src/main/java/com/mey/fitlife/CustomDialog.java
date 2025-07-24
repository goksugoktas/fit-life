package com.mey.fitlife;

import android.app.Activity;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.airbnb.lottie.LottieAnimationView;

public class CustomDialog {

    // başarılı durumlarda vs. dialog göstermek için bu class kullanılır

    Activity activity;
    String message;

    public CustomDialog(Activity activity, String message) {
        this.activity = activity;
        this.message = message;
    }

    public void showCustomAlertDialog() {
        // LayoutInflater kullanarak özel bir düzen oluşturun
        LayoutInflater inflater = LayoutInflater.from(activity);
        View customView = inflater.inflate(R.layout.custom_alert_dialog, null);


        // AlertDialog oluşturun
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setView(customView);

        TextView tvMessage = customView.findViewById(R.id.tvMessage);
        tvMessage.setText(message);

        // AlertDialog'u gösterin
        final AlertDialog alertDialog = builder.create();
        builder.setCancelable(false);
        alertDialog.show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 2 saniye sonra ilgili sayfanın kapatılması sağlanır
                if (!activity.isFinishing())
                    activity.finish();

                // AlertDialog'u kapatın
                alertDialog.dismiss();
            }
        }, 2000); // 2000 milisaniye = 2 saniye
    }

}
