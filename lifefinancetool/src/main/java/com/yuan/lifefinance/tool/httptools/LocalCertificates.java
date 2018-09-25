package com.yuan.lifefinance.tool.httptools;


import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/21.
 */

public class LocalCertificates {

    private static final String TAG = "LocalCertificates";
    // 证书数据
    private static List<byte[]> CERTIFICATES_DATA = new ArrayList<>();

    /**
     * 仅在服务器需验证客户端身份时使用，在Application#onCreate()中调用
     * @param path 证书存放路径
     * @param context Application Context
     * @throws IOException
     */
    public static void loadAll(String path, Context context) throws IOException {
        String[] certFiles = context.getAssets().list(path);
        if (certFiles != null) {
            for (String cert : certFiles) {
                InputStream is = context.getAssets().open(path + "/" + cert);
                // 这里将证书读取出来，，放在配置中byte[]里
                LocalCertificates.addCertificate(is);
                is.close();
            }
        }
    }

    /**
     * 添加https证书
     *
     * @param inputStream
     */
    private static void addCertificate(@NonNull InputStream inputStream) throws IOException {
        Log.i(TAG, "#addCertificate inputStream = " + inputStream);
        int ava;// 数据当次可读长度
        int len = 0;// 数据总长度
        ArrayList<byte[]> data = new ArrayList<>();
        while ((ava = inputStream.available()) > 0) {
            byte[] buffer = new byte[ava];
            inputStream.read(buffer);
            data.add(buffer);
            len += ava;
        }

        byte[] buff = new byte[len];
        int dstPos = 0;
        for (byte[] bytes : data) {
            int length = bytes.length;
            System.arraycopy(bytes, 0, buff, dstPos, length);
            dstPos += length;
        }

        CERTIFICATES_DATA.add(buff);
    }

    /**
     * https证书
     *
     * @return
     */
    public static List<byte[]> getCertificatesData() {
        return CERTIFICATES_DATA;
    }
}
