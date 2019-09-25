package com.yuan.lifefinance.tool.tools;

import java.security.spec.AlgorithmParameterSpec;
import java.util.Base64;


import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * copyright:华润创业(深圳)有限公司
 * author:admin
 * create_date:2019/8/2 14:00
 * <p>
 * describe:TODO
 */
public class DesUtil {
    static         AlgorithmParameterSpec iv  = null;// 加密算法的参数接口，IvParameterSpec是它的一个实现
    private static SecretKey              key = null;

    public DesUtil() throws Exception {
        String desIv = "DESDESDE";
        byte[] DESkey = "ABXCDDWK".getBytes();
//        byte[] DESIV = desIv.getBytes();
        DESKeySpec keySpec = new DESKeySpec(DESkey);// 设置密钥参数
        iv = new IvParameterSpec(desIv.getBytes());// 设置向量
//        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");// 获得密钥工厂
//        key = keyFactory.generateSecret(keySpec);// 得到密钥对象
        key = new SecretKeySpec(DESkey, desIv);
    }

    /**
     * 加密
     * @param data 待加密的数据
     * @return 加密后的数据
     * @throws Exception
     */
    public String encode(String data) throws Exception {
        Cipher enCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");// 得到加密对象Cipher
        enCipher.init(Cipher.ENCRYPT_MODE, key, iv);// 设置工作模式为加密模式，给出密钥和向量
        byte[] pasByte = enCipher.doFinal(data.getBytes("utf-8"));
//        return Base64.getEncoder().encodeToString(pasByte);
        return android.util.Base64.encodeToString(pasByte,android.util.Base64.DEFAULT);
    }

    /**
     * 解密
     * @param data  解密前的数据
     * @return 解密后的数据
     * @throws Exception
     */
    public String decode(String data) throws Exception {
        Cipher deCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        deCipher.init(Cipher.DECRYPT_MODE, key, iv);
//        byte[] pasByte = deCipher.doFinal(Base64.getDecoder().decode(data));
        byte[] pasByte = deCipher.doFinal(android.util.Base64.decode(data,android.util.Base64.DEFAULT));
        return new String(pasByte, "UTF-8");
    }
}
