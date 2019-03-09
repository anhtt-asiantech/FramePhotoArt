package com.anhttvn.framesphotoart.util;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.anhttvn.framesphotoart.R;


public class DialogView {
    private String title;
    private String message;
    public Dialog dialog;
    private Context mContex;
    public DialogView(Context context) {
        mContex = context;

    }
    public void showDialo(String title,String message){
        dialog = new Dialog(mContex);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        dialog.setContentView(R.layout.dialog_custom);
        // dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(false);
        TextView tv_title = (TextView) dialog.findViewById(R.id.title);
        tv_title.setText(title);
        tv_title.setTypeface(new ChangeFont().fontChange(mContex));
        TextView tv_message = (TextView) dialog.findViewById(R.id.message);
        tv_message.setText(message);
        tv_message.setTypeface(new ChangeFont().fontChange(mContex));
        Button btn_ok = (Button) dialog.findViewById(R.id.positive_button);
        btn_ok.setTypeface(new ChangeFont().fontChange(mContex));

        Button btn_cancel = (Button) dialog.findViewById(R.id.btn_cancel_dg);
        btn_cancel.setTypeface(new ChangeFont().fontChange(mContex));
        dialog.show();
    }
    public void clickCancel(){
        dialog.findViewById(R.id.close_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
}
