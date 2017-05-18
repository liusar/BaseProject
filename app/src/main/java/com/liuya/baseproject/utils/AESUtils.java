package com.liuya.baseproject.utils;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import android.util.Base64;

public class AESUtils {
    /** 
     * 加密 
     * @param content 要加密的内容 
     * @param password  加密密码 
     * @return 
     */  
    private final static String EncodeKey="qazwsxedcrfvtgbyhnujmikoyhbgtrew";
    private final static String DecodeKey="qazwsxedcrfvtgbyhnujmikoyhbgtrew";
    
    private static SecretKeySpec getKey(String password) throws UnsupportedEncodingException {
        int keyLength = 256;
        byte[] keyBytes = new byte[keyLength / 8];
        Arrays.fill(keyBytes, (byte) 0x0);
        byte[] passwordBytes = password.getBytes("UTF-8");
        int length = passwordBytes.length < keyBytes.length ? passwordBytes.length : keyBytes.length;
        System.arraycopy(passwordBytes, 0, keyBytes, 0, length);
        SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
        return key;
    }    
    
 // 加密
    public static String Encrypt(String sSrc){
        SecretKeySpec skeySpec;
		try {
			skeySpec = getKey(EncodeKey);
			 Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");//"算法/模式/补码方式"
		        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
		        byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
		        return Base64.encodeToString(encrypted,Base64.DEFAULT);//此处使用BASE64做转码功能，同时能起�?2次加密的作用�?
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       return null;
    }
 
    // 解密
    public static String Decrypt(String sSrc) {
        try {
            SecretKeySpec skeySpec = getKey(DecodeKey);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] encrypted1 = Base64.decode(sSrc,Base64.DEFAULT);//先用base64解密
            try {
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original,"utf-8");
                return originalString;
            } catch (Exception e) {
                System.out.println(e.toString());
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }
    /**
     * 将text中的+字符替换�?,字符
     * @param text
     * @return
     */
    public static String ReplacePlusSymbol(String text)
    {
    	if(text==null||text.length()==0)
    		return null;
    	return text.replaceAll("[\\u002B]", ",");
    }

    
}
