package com.yuan.lifefinance.tool.tools;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Environment;
import android.os.UserHandle;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.Toast;

import com.yuan.lifefinance.tool.SaleDetailActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by 123 on 2018/9/27.
 */

public class FileTools {

    /**
     * 获取外部存储卡【插入】编译版本不低于24
     * @return
     */
    public String externalSDCardPath(Context context) {
        try {
            StorageManager storageManager = (StorageManager) context.getSystemService(Context.STORAGE_SERVICE);
            // 7.0才有的方法
            List<StorageVolume> storageVolumes = storageManager.getStorageVolumes();
            Class<?> volumeClass = Class.forName("android.os.storage.StorageVolume");
            Method getPath = volumeClass.getDeclaredMethod("getPath");
            Method isRemovable = volumeClass.getDeclaredMethod("isRemovable");
            getPath.setAccessible(true);
            isRemovable.setAccessible(true);
            for (int i = 0; i < storageVolumes.size(); i++) {
                StorageVolume storageVolume = storageVolumes.get(i);
                String mPath = (String) getPath.invoke(storageVolume);
                Boolean isRemove = (Boolean) isRemovable.invoke(storageVolume);
                Log.d("tag2", "mPath is === " + mPath + "isRemoveble == " + isRemove);
            }
        }catch (Exception e){
            Log.d("tag2","e == "+e.getMessage());
        }
        return "";
    }

    /**
     * 获取外部存储卡【插入】编译版本低于24
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private static String getPhysicalExternalFilePathAboveM(){
        try {
            //===========================获取UserEnvironment================
            Class<?> userEnvironment = Class.forName("android.os.Environment$UserEnvironment");
            Method getExternalDirs =userEnvironment.getDeclaredMethod("getExternalDirs");
            getExternalDirs.setAccessible(true);
            //========获取构造UserEnvironment的必要参数UserId================
            Class<?> userHandle = Class.forName("android.os.UserHandle");
            Method myUserId = userHandle.getDeclaredMethod("myUserId");
            myUserId.setAccessible(true);
            int mUserId = (int) myUserId.invoke(UserHandle.class);
            Constructor<?> declaredConstructor = userEnvironment.getDeclaredConstructor(Integer.TYPE);
            // 得到UserEnvironment instance
            Object instance = declaredConstructor.newInstance(mUserId);
            File[] files = (File[]) getExternalDirs.invoke(instance);
            for (int i = 0; i < files.length; i++) {
                if (Environment.isExternalStorageRemovable(files[i])){
                    return files[i].getPath();
                }
            }
        } catch (Exception e) {
        }
        return "";
    }

    /**
     *
     * @param bmp
     * @param fileName
     * @throws IOException
     */
    public static void saveToSD(Bitmap bmp, String fileName) throws IOException {
//        String dirName = Environment.getExternalStorageDirectory()
//                .getAbsolutePath() + File.separator + "finance"+ File.separator;
//        // 判断sd卡是否存在
//        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
//            File dir1 = new File(dirName);
//            if(!dir1.exists()) dir1.mkdir();
//
//            String[] value = fileName.split("_");//方便排序
//            String subDir = dirName+value[1]+"_"+value[0];
//            File dir2 = new File(subDir);
//            // 判断文件夹是否存在，不存在则创建
//            if(!dir2.exists()){
//                dir2.mkdir();
//            }
//            File file = new File(subDir+ File.separator+fileName+".jpg");
//            // 判断文件是否存在，不存在则创建
//            if (!file.exists()) {
//                file.createNewFile();
//            }
//
//            FileOutputStream fos = null;
//            try {
//                fos = new FileOutputStream(file);
//                if (fos != null) {
//                    // 第一参数是图片格式，第二个是图片质量，第三个是输出流
//                    bmp.compress(Bitmap.CompressFormat.PNG, 100, fos);
//                    // 用完关闭
//                    fos.flush();
//                    fos.close();
//                }
//            } catch (FileNotFoundException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }
    }

    public static void saveToSD(Bitmap bmp,String timeInfoBuy,String nowDate,String stockName) throws IOException {
//        try {
//            String dirName = Environment.getExternalStorageDirectory()
//                    .getAbsolutePath() + File.separator + "finance"+ File.separator;
//            // 判断sd卡是否存在
//            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
//                File file = new File(dirName+timeInfoBuy+"_"+stockName+File.separator+stockName+"_"+nowDate+".jpg");
//                if(!file.exists()){
//                    file.createNewFile();
//                }
//                FileOutputStream fos = null;
//                try {
//                    fos = new FileOutputStream(file);
//                    if (fos != null) {
//                        // 第一参数是图片格式，第二个是图片质量，第三个是输出流
//                        bmp.compress(Bitmap.CompressFormat.PNG, 100, fos);
//                        // 用完关闭
//                        fos.flush();
//                        fos.close();
//                    }
//                } catch (FileNotFoundException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//            }
//        }
//        catch(Exception ex){
////            Toast.makeText(SaleDetailActivity.this,"error:"+ex.toString(),Toast.LENGTH_SHORT).show();
//        }
    }
}
