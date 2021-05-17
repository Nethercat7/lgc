package com.lightning.lgc.core.util;

import org.apache.shiro.crypto.hash.Md5Hash;

import java.util.Random;

public class PwdUtil {
    public static String pwd2MD5(String pwd, String salt, int hash){
        return new Md5Hash(pwd,salt,hash).toHex();
    }

    public static String getSalt(int n){
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz01234567890!@#$%^&*()".toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char aChar = chars[new Random().nextInt(chars.length)];
            sb.append(aChar);
        }
        return sb.toString();
    }
}
