package com.yuan.lifefinance.tool.view;

import android.app.Activity;
import android.content.Context;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;


import com.flyco.animation.BounceEnter.BounceEnter;
import com.flyco.animation.ZoomExit.ZoomOutExit;
import com.flyco.dialog.widget.base.BaseDialog;
import com.yuan.lifefinance.tool.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

public class CustomHintDialog {
    public static final int Dialog_TYPE_1 = 101;
    public static final int Dialog_TYPE_2 = 102;
    public static final int Dialog_TYPE_3 = 103;
    public static final int Dialog_style_1 = 4;
    public static final int Dialog_style_2 = 5;
    public static final int Dialog_style_3 = 6;
    public static final int Dialog_style_4_0 = 70;
    public static final int Dialog_style_4_2 = 72;
    public static final int Dialog_style_5 = 8;

    public interface OnSureListener {
        void sureClick();
    }

    public CustomHintDialog(Context context, OnSureListener clickListener, String content, String exitDisc, String sureDisc, int dialog_type) {
        int loadLayoutid = 0;
        if (dialog_type == Dialog_TYPE_1) {
            loadLayoutid = R.layout.my_isopen_dialog;
        } else if (dialog_type == Dialog_TYPE_2) {
            loadLayoutid = R.layout.my_isopen_dialog2;
        }
        CustomDialog1 dialog = new CustomDialog1(context, loadLayoutid, clickListener, content, exitDisc, sureDisc);
        dialog.showAnim(new BounceEnter().duration(800));
//		dialog.dismissAnim(new ZoomOutExit().duration(50));
        dialog.show();
    }



    class CustomDialog1 extends BaseDialog<CustomDialog1> {
        @BindView(R.id.sure)
        Button sure;
        @BindView(R.id.cancel)
        Button cancel;
        @BindView(R.id.textView2)
        TextView textContent;
        private int layoutID;
        private Context mContext;
        private OnSureListener clickListener;
        private String content;
        private String exitDisc;
        private String sureDisc;

        public CustomDialog1(Context context, int layoutID, OnSureListener clickListener, String content, String exitDisc, String sureDisc) {
            super(context);
            this.layoutID = layoutID;
            this.mContext = context;
            this.clickListener = clickListener;
            this.content = content;
            this.exitDisc = exitDisc;
            this.sureDisc = sureDisc;
        }

        @Override
        public View onCreateView() {
            widthScale(0.85f);
            View inflate = View.inflate(mContext, layoutID, null);
            ButterKnife.bind(this, inflate);
            return inflate;
        }

        @Override
        public void setUiBeforShow() {
            textContent.setText(content);
            sure.setText(sureDisc);
            cancel.setText(exitDisc);
            sure.setOnClickListener(view -> {
                clickListener.sureClick();
                dismiss();
            });
            cancel.setOnClickListener(view -> dismiss());

        }
    }

    class CustomDialog3 extends BaseDialog<CustomDialog3>{
        @BindView(R.id.sure)
        Button sure;
        @BindView(R.id.textView2)
        TextView textContent;
        private int layoutID;
        private Context mContext;
        private OnSureListener clickListener;
        private String content;
        private String sureDisc;

        public CustomDialog3(Context context, int layoutID, OnSureListener clickListener, String content, String sureDisc) {
            super(context);
            this.layoutID = layoutID;
            this.mContext = context;
            this.clickListener = clickListener;
            this.content = content;
            this.sureDisc = sureDisc;
        }

        @Override
        public View onCreateView() {
            widthScale(0.85f);
            View inflate = View.inflate(mContext, layoutID, null);
            ButterKnife.bind(this, inflate);
            return inflate;
        }

        @Override
        public void setUiBeforShow() {
            textContent.setText(content);
            sure.setText(sureDisc);
            sure.setOnClickListener(view -> {
                clickListener.sureClick();
                dismiss();
            });

        }
    }

    public void settingDialogPostion(Window dialogWindow,Activity activity) {

//        Window dialogWindow = getWindow();
        WindowManager windowManager = activity.getWindowManager();
        dialogWindow.setBackgroundDrawableResource(android.R.color.transparent);
        Display d = windowManager.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        p.width = (int) (d.getWidth() * 0.8);
        p.x = (int) (d.getWidth() * 0.1);
        p.y = (int) (d.getHeight() * 1) / 5;
        p.alpha = 1.0f;// 透明度80%
        dialogWindow.setGravity(Gravity.LEFT | Gravity.TOP);
        dialogWindow.setAttributes(p);
    }

}
