package com.yuan.lifefinance.tool.tools;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;


import com.yuan.lifefinance.tool.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by Administrator on 2017/9/7.
 */

public class PermissionTools {
    private boolean sureOpenPerm = true;//必须强制通过权限
    private int REQUEST_READ_PHONE_STATE = 10000;
    private static PermissionTools peimissionTools;
    private Activity context;

    String[] permissionlist;
    String[] permissionlist_dis;

    long upRequestPermissionTime = 0;
    int noGrantFlagNum = 0;
    HashMap<String, Integer> permissionlistState = new HashMap<>();
    HashMap<String, String> permissionlistDis = new HashMap<>();

    private PermissionDealListener permissionDealListener;

    public interface PermissionDealListener {
        //user forbidden
        void permissionForbidden(ArrayList<String> noGrantpermissionDislist);

        //user allow permission
        void permissionPass();

        //user refuse permission
        void permissionRefuse();
    }

    private PermissionTools(Activity context, @NonNull HashMap<String, String> permissionlistDis, PermissionDealListener permissionDealListener) {
        this.context = context;
        this.permissionlistDis = permissionlistDis;
        Set<String> keySet = permissionlistDis.keySet();
        permissionlist = keySet.toArray(new String[keySet.size()]);
        permissionlist_dis = new String[permissionlist.length];
        for (int i = 0; i < permissionlist.length; i++){
            permissionlist_dis[i] = permissionlistDis.get(permissionlist[i]);
//            Log.d("onRequestPermissionsResult",permissionlist[i]+" --->"+permissionlist_dis[i]);
        }
        this.permissionDealListener = permissionDealListener;
    }

    public static PermissionTools getInstance(Activity contexts, @NonNull HashMap<String, String> permissionlistDis, PermissionDealListener permissionDealListener) {
        if (peimissionTools == null) {
            peimissionTools = new PermissionTools(contexts, permissionlistDis, permissionDealListener);
        }
        return peimissionTools;
    }

    public void requestRunPermission() {
        requestReadPhonePermissionDeal(permissionlist,true);
    }

    public void requestRunPermission(boolean sureOpenPerm) {
        this.sureOpenPerm = sureOpenPerm;
        requestReadPhonePermissionDeal(permissionlist,true);
    }

    public void requestRunPermission(String[] permissionlist) {
        requestReadPhonePermissionDeal(permissionlist,true);
    }

    public void requestRunPermission(String[] permissionlist,boolean sureOpenPerm) {
        requestReadPhonePermissionDeal(permissionlist,sureOpenPerm);
    }

    private int granted_num = 0;//未获取的权限的个数
    private int flag = 0;//记录第一次授权
    private void requestReadPhonePermissionDeal(String[] permissionlist,boolean sureOpenPerm) {
//        LogUtil.d("MycallBack","开始判断权限");
        for (int i = 0; i < permissionlist.length; i++) {
            int permissionCheck = ContextCompat.checkSelfPermission(context, permissionlist[i]);
            if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                granted_num++;
                ActivityCompat.requestPermissions(context, permissionlist, REQUEST_READ_PHONE_STATE);
            }
        }

        if(permissionDealListener != null){
            if(granted_num == 0){
                permissionDealListener.permissionPass();
            }
        }
        else{
            context.finish();
        }
    }

    public void dealPermissionResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d("requestPermissions_log","grantResults:"+grantResults.length);
        if (requestCode == REQUEST_READ_PHONE_STATE) {
            if (grantResults.length > 0) {
                //保存权限授权状态
                boolean flag = true;
                ArrayList<String> noGrantPermission = new ArrayList<>();
                ArrayList<String> noGrantpermissionDislist = new ArrayList<>();
                for (int i = 0; i < permissions.length; i++) {
                    permissionlistState.put(permissions[i], grantResults[i]);
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                        flag = false;
                        noGrantPermission.add(permissions[i]);
                        noGrantpermissionDislist.add(permissionlistDis.get(permissions[i]));
                    }
                }
                if (!flag) {//重新授权
                    long time = (new Date().getTime() - upRequestPermissionTime);
                    if (upRequestPermissionTime != 0 && time < 200) {
                        noGrantFlagNum++;
                    } else {
                        noGrantFlagNum = 0;
                    }
                    Log.d("requestPermissions_log","num:"+noGrantFlagNum);
                    if (noGrantFlagNum > 10) {
                        permissionDealListener.permissionForbidden(noGrantpermissionDislist);
                        noGrantFlagNum = 0;
                    } else {
                        requestRunPermission(noGrantPermission.toArray(new String[noGrantPermission.size()]));
                        upRequestPermissionTime = new Date().getTime();
//                            permissionDealListener.permissionRefuse();
                    }
                } else {//全部授权通过
                    permissionDealListener.permissionPass();

                }
            }
        }
    }

    public void showPermissionDialog(ArrayList<String> noGrantpermissionDislist) {
        String permissionNames = "";
        if(noGrantpermissionDislist.size() == 1){
            permissionNames = "\n▪"+noGrantpermissionDislist.get(0)+"\n";
        }
        else{
            for (int i = 0; i < noGrantpermissionDislist.size(); i++) {
                permissionNames =permissionNames + (i+1)+". "+noGrantpermissionDislist.get(i) + "\n";
            }
        }

//        PermissionDialog dialogs = new PermissionDialog(context, R.layout.permission_dialog, ()->{
//            goAppDetailSettingIntent();
//            context.finish();
//        }, ()->{
//            context.finish();
//        },"请在应用信息里点击权限，并授权\n" + permissionNames,
//                "取消", "确定");
//        dialogs.showAnim(new BounceEnter().duration(800));
//        dialogs.show();

        AlertDialog dialog = null;
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.MyDialogPermissionStyle);
        builder.setCancelable(false);
        builder.setTitle("请先赋予操作权限:\n\n");
        builder.setMessage("请在应用信息里点击权限，并授权\n" + permissionNames).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                goAppDetailSettingIntent();
                dialog.dismiss();
                context.finish();
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                context.finish();
            }
        });
        dialog = builder.create();
        dialog.show();
    }

    private void goAppDetailSettingIntent() {
        getAppDetailSettingIntent(context);
    }


    public void clean(){
        peimissionTools = null;
    }

    /**
     * 进入应用详情
     * @param context
     */
    public static void getAppDetailSettingIntent(Context context) {
        Intent localIntent = new Intent();
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 9) {
//            localIntent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
            localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            localIntent.setData(Uri.fromParts("package", context.getPackageName(), null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            localIntent.setAction(Intent.ACTION_VIEW);
            localIntent.setClassName("com.android.settings","com.android.settings.InstalledAppDetails");
            localIntent.putExtra("com.android.settings.ApplicationPkgName", context.getPackageName());
        }
        context.startActivity(localIntent);
    }

    public interface OnSureListener {
        void sureClick();
    }
    public interface OnExitListener {
        void exitClick();
    }

}
