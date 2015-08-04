package com.example.internship.geofenceapp;


import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

import com.google.android.gms.maps.MapFragment;

/**
 * Created by ely on 15/08/01.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB) // insure not using old version
public class Dialog extends android.app.Dialog
{

    private Button btn;
    public Dialog(Context context) {
        super(context);
        this.setTitle("New Place");
        setContentView(R.layout.dialog);
        btn = (Button) findViewById(R.id.cancel_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dismiss();
            }
        });
    }

}
