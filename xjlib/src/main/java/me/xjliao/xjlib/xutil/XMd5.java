/*
 * Copyright (c) 2017 xjliao.me created by xjliao
 * ProjectName: xjl
 * ModuleName: xjlib
 * FileName: XMd5.java
 * ClassName: XMd5
 * LastModified: 10/11/17 11:35 AM
 */

package me.xjliao.xjlib.xutil;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class XMd5 {
    public static String encryptToMd5(String source) {
        MessageDigest md = null;
        String md5Text = null;
        try {
            md = MessageDigest.getInstance("MD5");

            byte[] messageDigest = md.digest(source.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            md5Text = number.toString(16);
            // Now we need to zero pad it if you actually want the full 32 chars.
            while (md5Text.length() < 32) {
                md5Text = "0" + md5Text;
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return md5Text;
    }
}
