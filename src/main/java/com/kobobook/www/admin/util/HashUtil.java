package com.kobobook.www.admin.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class HashUtil {

    /**
     * MD5
     *
     * @param str
     * @return
     */
    public static String md5(String str){
        String MD5 = "";
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            MD5 = getString(str, md);
        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
            MD5 = null;
        }
        return MD5;
    }

    /**
     * SHA256
     *
     * @param str
     * @return
     */
    public static String sha256(String str) {
        String SHA = "";
        try {
            MessageDigest sh = MessageDigest.getInstance("SHA-256");
            SHA = getString(str, sh);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            SHA = null;
        }
        return SHA;
    }

    private static String getString(String str, MessageDigest sh) {
        String result;
        sh.update(str.getBytes());
        byte byteData[] = sh.digest();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        result = sb.toString();
        return result;
    }

}
