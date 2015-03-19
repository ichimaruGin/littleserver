package com.iwebirth.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by YY_410 on 2015/3/18.
 */
public class DigestMethods {

    static{

    }

    static final String[] hexArray = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};

    public static String getMD5Value(byte[] s){
        byte[] ss;
        StringBuilder sb = new StringBuilder();;
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(s);
            ss = digest.digest();
            for(int i =0;i<ss.length;i++){
                sb.append(byteToString(ss[i]));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    //byte转为等值的hex
    public static String byteToString(byte b){
        int bb = b;
        if(bb < 0)
            bb += 256;
        int h = bb/16;
        int l = bb%16;
        return hexArray[h]+hexArray[l];
    }

    public static void main(String[] args){
        String md5 = DigestMethods.getMD5Value("666670".getBytes());
        System.out.println(md5);
    }
}
