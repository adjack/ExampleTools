package activity.example.yuan.cn.exampletools.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageUtil {
    
    public static final int CAMERA_CODE = 100001;
    public static final int LOC_CODE = 100003;
    public static final int RESULT_CODE = 100002;
    
    public final static String ROOT_PATH = Environment.getExternalStorageDirectory().getPath() + "/tcgj";
    
    
    public static String getFileName() {
        String s= ROOT_PATH + "/tmp_pic_"+ System.currentTimeMillis() + ".jpg";
        return s;
    }
    
    /**
     * 将bitmap专程byte[]
     * @param bitmap
     * @return
     */
	public static byte[] formatBitmapToByte(Bitmap bitmap) {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
		return baos.toByteArray();

	}

    /**
     * 文件大小
     * @param bitmap
     * @return KB
     */
    public static long getBitmapSize(Bitmap bitmap){
        try {
            File file = bitmapToFile(bitmap);
            long s = 1;
            if (file.exists()) {
                FileInputStream fis = null;
                fis = new FileInputStream(file);
                s = fis.available();
            } else {
                file.createNewFile();
            }
            return s/1024;
        }
        catch (Exception ex){

        }

        return 0;
    }

    public static File bitmapToFile(Bitmap bitmap){
        byte[]  bytes = ImageUtil.formatBitmapToByte(bitmap);

        return write(bytes);
    }
	
	/**
	 * 将Drawable转化为Bitmap
	 */
	public static Bitmap drawableToBitmap(Drawable drawable) {
	    if (drawable instanceof BitmapDrawable) {  
            return ((BitmapDrawable) drawable).getBitmap();  
        } else if (drawable instanceof NinePatchDrawable) {  
            Bitmap bitmap = Bitmap  
                    .createBitmap(  
                            drawable.getIntrinsicWidth(),  
                            drawable.getIntrinsicHeight(),  
                            drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888  
                                    : Bitmap.Config.RGB_565);  
            Canvas canvas = new Canvas(bitmap);  
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),  
                    drawable.getIntrinsicHeight());  
            drawable.draw(canvas);  
            return bitmap;  
        } else {  
            return null;  
        }  
    }
	
	/**
	 * byte[] 转为Bitmap
	 * @param b
	 * @return
	 */
	public static Bitmap bytesToBimap(byte[] b) {
        if (b.length != 0) {
            return BitmapFactory.decodeByteArray(b, 0, b.length);
        } else {
            return null;
        }
    }
	
	/**
	 * 保存图片
	 * @param
	 * @param
	 */
	public static boolean saveImage(Bitmap bitmap) {  
        byte[]  b = formatBitmapToByte(bitmap);
        write(b);
        return true;  
    } 
	
	// 写到sdcard中
	public static File  write(byte[] bs){
        File file = null;
//	    String fileName = getFileName();
	    try {
            //检查目录
	        File fileP = new File(ROOT_PATH);
	        if (!fileP.exists()) {
	            fileP.mkdirs();
	        }
            //检查文件
	        file = new File(getFileName());
	        if (!file.exists()) {
	            file.getParentFile().mkdirs();
	            file.createNewFile();
	        }

            FileOutputStream out=new FileOutputStream(file);
            out.write(bs);
            out.flush();
            out.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return file;
	
	}
	
//	public static String getImageUrl(String url){
//		if(!url.contains("ttp://")){
//			return UrlUtil.BASE_URL_MAG+"/"+url;
//		}
//		else{
//			return url;
//		}
//	}
	
//	public static String getADImageUrl(String url){
//		if(!url.contains("ttp://")){
//			return UrlUtil.BASE_URL_H5_TEST+"/"+url;
//		}
//		else{
//			return url;
//		}
//	}
	
	
	private static final String TAG ="ImageUtil";
	
	public static Intent choosePicture() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        return Intent.createChooser(intent, null);
    }
	
	

    /**
     * 拍照后返回
     */
    public static Intent takeBigPicture() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, newPictureUri(getNewPhotoPath()));
        return intent;
    }

    public static String getDirPath() {
        return Environment.getExternalStorageDirectory().getPath() + "/WebViewUploadImage";
    }

    private static String getNewPhotoPath() {
        return getDirPath() + "/" + System.currentTimeMillis() + ".jpg";
    }

    public static String retrievePath(Context context, Intent sourceIntent, Intent dataIntent) {
        String picPath = null;
        try {
            Uri uri;
            if (dataIntent != null) {
                uri = dataIntent.getData();
                if (uri != null) {
                    picPath = ContentUtil.getPath(context, uri);
                }
                if (isFileExists(picPath)) {
                    return picPath;
                }

                Log.w(TAG, String.format("retrievePath failed from dataIntent:%s, extras:%s", dataIntent, dataIntent.getExtras()));
            }

            if (sourceIntent != null) {
                uri = sourceIntent.getParcelableExtra(MediaStore.EXTRA_OUTPUT);
                if (uri != null) {
                    String scheme = uri.getScheme();
                    if (scheme != null && scheme.startsWith("file")) {
                        picPath = uri.getPath();
                    }
                }
                if (!TextUtils.isEmpty(picPath)) {
                    File file = new File(picPath);
                    if (!file.exists() || !file.isFile()) {
                        Log.w(TAG, String.format("retrievePath file not found from sourceIntent path:%s", picPath));
                    }
                }
            }
            return picPath;
        } finally {
            Log.d(TAG, "retrievePath(" + sourceIntent + "," + dataIntent + ") ret: " + picPath);
        }
    }

    private static Uri newPictureUri(String path) {
        return Uri.fromFile(new File(path));
    }

    private static boolean isFileExists(String path) {
        if (TextUtils.isEmpty(path)) {
            return false;
        }
        File f = new File(path);
        if (!f.exists()) {
            return false;
        }
        return true;
    }
	
}
