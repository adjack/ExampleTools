package com.yuan.lifefinance.tool;

import android.util.Base64;
import android.util.Log;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * copyright:华润创业(深圳)有限公司
 * author:admin
 * create_date:2019/8/2 10:05
 * <p>
 * describe:TODO
 */
public class EncryptUtilsss {
    public static String AES_Transformation = "AES/ECB/NoPadding";

    public static byte[] encryptAES2Base64(byte[] data, byte[] key) {
        return base64Encode(encryptAES(data, key));
    }

    public static byte[] encryptAES(byte[] data, byte[] key) {
        return desTemplate(data, key, "AES", AES_Transformation, true);
    }

    public static byte[] decryptBase64AES(byte[] data, byte[] key) {
        return decryptAES(base64Decode(data), key);
    }
    private static byte[] base64Encode(byte[] input) {
        return Base64.encode(input, 2);
    }

    private static byte[] base64Decode(byte[] input) {
        return Base64.decode(input, 2);
    }

    public static byte[] decryptAES(byte[] data, byte[] key) {
        return desTemplate(data, key, "AES", AES_Transformation, false);
    }

    public static byte[] desTemplate(byte[] data, byte[] key, String algorithm, String transformation, boolean isEncrypt) {
        if (data != null && data.length != 0 && key != null && key.length != 0) {
            try {
                SecretKeySpec keySpec = new SecretKeySpec(key, algorithm);
                Cipher cipher = Cipher.getInstance(transformation);
                SecureRandom random = new SecureRandom();
                cipher.init(isEncrypt ? 1 : 2, keySpec, random);
                return cipher.doFinal(data);
            } catch (Throwable var8) {
                Log.d("sasafe",var8.getMessage());
                var8.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }



}
