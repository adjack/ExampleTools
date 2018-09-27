package com.yuan.lifefinance.tool.tools;

import android.graphics.Bitmap;
import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by 123 on 2018/9/27.
 */

public class FileTools {
    /**
     *
     * @param bmp
     * @param fileName
     * @throws IOException
     */
    public static void saveToSD(Bitmap bmp, String fileName) throws IOException {
        String dirName = Environment.getExternalStorageDirectory()
                .getAbsolutePath() + File.separator + "finance"+ File.separator;
        // 判断sd卡是否存在
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File dir1 = new File(dirName);
            if(!dir1.exists()) dir1.mkdir();

            String[] value = fileName.split("_");//方便排序
            String subDir = dirName+value[1]+"_"+value[0];
            File dir2 = new File(subDir);
            // 判断文件夹是否存在，不存在则创建
            if(!dir2.exists()){
                dir2.mkdir();
            }
            File file = new File(subDir+ File.separator+fileName+".jpg");
            // 判断文件是否存在，不存在则创建
            if (!file.exists()) {
                file.createNewFile();
            }

            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(file);
                if (fos != null) {
                    // 第一参数是图片格式，第二个是图片质量，第三个是输出流
                    bmp.compress(Bitmap.CompressFormat.PNG, 100, fos);
                    // 用完关闭
                    fos.flush();
                    fos.close();
                }
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
