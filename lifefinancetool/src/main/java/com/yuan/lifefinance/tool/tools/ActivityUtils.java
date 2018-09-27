package com.yuan.lifefinance.tool.tools;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by jack on 2018/3/30.
 */

public class ActivityUtils {

    /**
     * 跳转目标activity
     *
     * @param mContext
     * @param cls
     * @param keyValues 包含key-values的string[]
     */
    public static void goActivity(Context mContext, Class<?> cls, String[]... keyValues) {
        try {
            Intent intent = new Intent(mContext, cls);
            for (int i = 0; i < keyValues.length; i++) {
                String[] values = keyValues[i];
                intent.putExtra(values[0], values[1]);
            }
            mContext.startActivity(intent);
        } catch (Exception ex) {
        }
    }

    public static void goActivity(Context mContext, Class<?> cls, Object[][] keyValues) {
        initParam(mContext,cls,-1,keyValues);
    }
//
    public static void goActivity(Context mContext, Class<?> cls,int flag, Object[][] keyValues) {
        initParam(mContext,cls,flag,keyValues);
    }

    private static void initParam(Context mContext, Class<?> cls,int flag, Object[][] keyValues){
        try {
            Intent intent = new Intent(mContext, cls);
            if(keyValues != null){
                for (int i = 0; i < keyValues.length; i++) {
                    Object[] values = keyValues[i];
                    if(values[1] instanceof String){
                        intent.putExtra(values[0].toString(),(String) values[1]);
                    }
                    else if(values[1] instanceof Integer){
                        intent.putExtra(values[0].toString(),(Integer) values[1]);
                    }
                    else if(values[1] instanceof Boolean){
                        intent.putExtra(values[0].toString(),(Boolean) values[1]);
                    }
                }
            }
            if(flag != -1) intent.addFlags(flag);
            mContext.startActivity(intent);
        } catch (Exception ex) {
        }
    }

    /**
     * 获取状态栏高度
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        try {
            int statusBarHeight = -1;
            //获取status_bar_height资源的ID
            int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                //根据资源ID获取响应的尺寸值
                statusBarHeight = context.getResources().getDimensionPixelSize(resourceId);
            }
            return statusBarHeight;
        }catch (Exception ex){}
        return 60;
    }

    public static boolean isBackground(Context context) {
        if(context == null){
            return false;
        }
        try {
            ActivityManager activityManager = (ActivityManager) context
                    .getSystemService(Context.ACTIVITY_SERVICE);
            List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager
                    .getRunningAppProcesses();
            for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
                if (appProcess.processName.equals(context.getPackageName())) {
                    if (appProcess.importance != ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    /*Log.i(context.getPackageName(), "处于后台"    + appProcess.processName);*/
                        return true;
                    } else {
                    /*Log.i(context.getPackageName(), "处于前台" + appProcess.processName);*/
                        return false;
                    }
                }
            }
        }
        catch (NullPointerException ex){}
        return false;
    }

    /**
     * 修改状态栏为全透明
     * @param activity
     */
    @TargetApi(19)
    public static void transparencyBar(Activity activity){
        if(activity == null){
            return;
        }
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.setStatusBarColor(Color.TRANSPARENT);

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window =activity.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    /**
     * 设置状态栏背景颜色，状态栏白色字体
     * @param activity
     * @param statusbarcolorId
     */
    public static void setStatusBarStyle(Activity activity, int statusbarcolorId, @NonNull ViewGroup rootView){
        if(activity == null){
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // 设置状态栏底色颜色
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_VISIBLE);
            activity.getWindow().setStatusBarColor(statusbarcolorId);

            if(rootView != null){
                try {
                    RelativeLayout layout = new RelativeLayout(activity);
                    ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ActivityUtils.getStatusBarHeight(activity));
                    layout.setLayoutParams(params);
                    rootView.addView(layout,0);
                }
                catch (Exception ex){}
            }
        }
    }

    /**
     * 全屏白底，深色字体
     * @param activity
     */
    public static void setStatusBarStyle(Activity activity){
        if(activity == null){
            return;
        }
        setStatusBar(activity);
    }

    /**
     * 全屏白底，深色字体
     * @param activity
     */
    public static void setStatusBarStyle(Activity activity,@NonNull ViewGroup rootView){
        if(activity == null){
            return;
        }
        setStatusBar(activity);
        if(rootView != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            try {
                RelativeLayout layout = new RelativeLayout(activity);
                ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ActivityUtils.getStatusBarHeight(activity));
                layout.setLayoutParams(params);
                rootView.addView(layout,0);
            }
            catch (Exception ex){}
        }
    }

    public static void addViewToTopView(Activity activity,@NonNull ViewGroup rootView){
        try {
            RelativeLayout layout = new RelativeLayout(activity);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ActivityUtils.getStatusBarHeight(activity));
            layout.setLayoutParams(params);
            rootView.addView(layout,0);
        }
        catch (Exception ex){}
    }


    private static void setStatusBar(Activity activity) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                // 设置状态栏底色颜色
                activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                activity.getWindow().setStatusBarColor(Color.WHITE);
            }
        }
        catch (Exception ex){}

    }


    /**
     * 设置状态栏图标为深色和魅族特定的文字风格
     * 可以用来判断是否为Flyme用户
     * @param window 需要设置的窗口
     * @param dark 是否把状态栏字体及图标颜色设置为深色
     * @return  boolean 成功执行返回true
     *
     */
//    public static boolean FlymeSetStatusBarLightMode(Window window, boolean dark) {
//        boolean result = false;
//        if (window != null) {
//            try {
//                WindowManager.LayoutParams lp = window.getAttributes();
//                Field darkFlag = WindowManager.LayoutParams.class
//                        .getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
//                Field meizuFlags = WindowManager.LayoutParams.class
//                        .getDeclaredField("meizuFlags");
//                darkFlag.setAccessible(true);
//                meizuFlags.setAccessible(true);
//                int bit = darkFlag.getInt(null);
//                int value = meizuFlags.getInt(lp);
//                if (dark) {
//                    value |= bit;
//                } else {
//                    value &= ~bit;
//                }
//                meizuFlags.setInt(lp, value);
//                window.setAttributes(lp);
//                result = true;
//            } catch (Exception e) {
//
//            }
//        }
//        return result;
//    }

//    public static void setStatusBarStyle(Activity activity,@NonNull RelativeLayout relativeLayout){
//        if(activity == null){
//            return;
//        }
//        setStatusBar(activity);
////        try {
////            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
////                ViewGroup.LayoutParams params = relativeLayout.getLayoutParams();
////                params.height = ActivityUtils.getStatusBarHeight(activity);
////                relativeLayout.setLayoutParams(params);
////            }
////        }
////        catch (NullPointerException ex){}
//    }

//    public static void setStatusBarView(@NonNull Activity activity,RelativeLayout relativeLayout){
//        try {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                ViewGroup.LayoutParams params = relativeLayout.getLayoutParams();
//                params.height = ActivityUtils.getStatusBarHeight(activity);
//                relativeLayout.setLayoutParams(params);
//            }
//        }
//        catch (NullPointerException ex){}
//    }

    /**
     * 设置状态栏文字颜色
     * @param activity
     * @param colorId
     */
//    public static void setStatusBarFontColor(Activity activity, int colorId){
//        if(activity == null){
//            return;
//        }
//        try {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                Window window = activity.getWindow();
//                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//                window.getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//                //设置背景透明
//                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//                window.setStatusBarColor(Color.TRANSPARENT);
//            }
//        }
//        catch (Exception ex){}
//    }

//    public static boolean setStatusBarDarkIcon(Window window, boolean dark) {
//        boolean result = false;
//        if (window != null) {
//            try {
//                WindowManager.LayoutParams lp = window.getAttributes();
//                Field darkFlag = WindowManager.LayoutParams.class.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
//                Field meizuFlags = WindowManager.LayoutParams.class.getDeclaredField("meizuFlags");
//                darkFlag.setAccessible(true);
//                meizuFlags.setAccessible(true);
//                int bit = darkFlag.getInt(null);
//                int value = meizuFlags.getInt(lp);
//                if (dark) {
//                    value |= bit;
//                } else {
//                    value &= ~bit;
//                }
//                meizuFlags.setInt(lp, value);
//                window.setAttributes(lp);
//                result = true;
//            } catch (Exception e) {
//            }
//        }
//        return result;
//    }

    /**
     * 清除透明效果
     * @param activity
     */
//    public static void cleanTransparencyBar(Activity activity){
//        if(activity == null){
//            return;
//        }
//        Window window = activity.getWindow();
//        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//    }

    /**
     * 修改状态栏颜色，支持4.4以上版本
//     * @param activity
//     * @param colorId
     */
//    public static void setStatusBarColor(Activity activity, int colorId) {
//        if(activity == null){
//            return;
//        }
//        transparencyBar(activity);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Window window = activity.getWindow();
//            window.setStatusBarColor(activity.getResources().getColor(colorId));
//        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            //使用SystemBarTint库使4.4版本状态栏变色，需要先将状态栏设置为透明
//            transparencyBar(activity);
//            SystemBarTintManager tintManager = new SystemBarTintManager(activity);
//            tintManager.setStatusBarTintEnabled(true);
//            tintManager.setStatusBarTintResource(colorId);
//        }
//    }

    public static Boolean checkIsVisible(Context context, View view) {
        // 如果已经加载了，判断广告view是否显示出来，然后曝光
        int screenWidth = getScreenMetrics(context).x;
        int screenHeight = getScreenMetrics(context).y;
        Rect rect = new Rect(0, 0, screenWidth, screenHeight);
        int[] location = new int[2];
        view.getLocationInWindow(location);
        if (view.getLocalVisibleRect(rect)) {
            return true;
        } else {
            //view已不在屏幕可见区域;
            return false;
        }
    }

    public static Point getScreenMetrics(Context context){
        DisplayMetrics dm =context.getResources().getDisplayMetrics();
        int w_screen = dm.widthPixels;
        int h_screen = dm.heightPixels;
        return new Point(w_screen, h_screen);
    }


    public static void setFont(Context context,TextView... textViews){
        try {
            for(TextView textView:textViews){
                textView.setTypeface(getTextFontResPingfang(context));
            }
        }
        catch (Exception ex){}

    }

    public static Typeface customTypeface1;
    public static Typeface getTextFontResPingfang(Context context){
        if(customTypeface1 == null){
            customTypeface1= Typeface.createFromAsset(context.getAssets(), "pingfang.ttf");
        }
        return customTypeface1;
    }

    public static Typeface customTypeface2;
    public static Typeface getTextFontResDigital(Context context){
        if(customTypeface2 == null){
            customTypeface2= Typeface.createFromAsset(context.getAssets(), "dinalternatebold.ttf");
        }
        return customTypeface2;
    }

    public static void setDefaultFont(Context context,
                                      String staticTypefaceFieldName, String fontAssetName) {
        final Typeface regular = Typeface.createFromAsset(context.getAssets(),
                fontAssetName);
        replaceFont(staticTypefaceFieldName, regular);
    }

    protected static void replaceFont(String staticTypefaceFieldName,
                                      final Typeface newTypeface) {
        try {
            final Field staticField = Typeface.class
                    .getDeclaredField(staticTypefaceFieldName);
            staticField.setAccessible(true);
            staticField.set(null, newTypeface);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 界面截图
     * @param activity
     * @return
     */
    public static Bitmap activityShot(Activity activity) {
        // 获取windows中最顶层的view
        View view = activity.getWindow().getDecorView();
        view.buildDrawingCache();

        // 获取状态栏高度
        Rect rect = new Rect();
        view.getWindowVisibleDisplayFrame(rect);
        int statusBarHeights = rect.top;
        Display display = activity.getWindowManager().getDefaultDisplay();

        // 获取屏幕宽和高
        int widths = display.getWidth();
        int heights = display.getHeight();

        // 允许当前窗口保存缓存信息
        view.setDrawingCacheEnabled(true);

        // 去掉状态栏
        Bitmap bmp = Bitmap.createBitmap(view.getDrawingCache(), 0,
                statusBarHeights, widths, heights - statusBarHeights-200);

        // 销毁缓存信息
        view.destroyDrawingCache();
        return bmp;
    }

}
