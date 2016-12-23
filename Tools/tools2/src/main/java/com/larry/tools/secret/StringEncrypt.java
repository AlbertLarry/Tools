package com.larry.tools.secret;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 对字符串加密,加密算法使用MD5,SHA-1,SHA-256,默认使用SHA-256
 *
 * @author liuxuewen
 * @date 2014-1-22
 */

public class StringEncrypt {
    /**
     * 对字符串加密,加密算法使用MD5,SHA-1,SHA-256,默认使用SHA-256
     *
     * @param strSrc  要加密的字符串
     * @param encName 加密类型
     * @return
     */
    public static String EncryptBySHA_256(String strSrc) {
        return Encrypt(strSrc, null);
    }

    public static String EncryptByMD5(String strSrc) {
        return Encrypt(strSrc, "MD5");
    }

    public static String Encrypt(String strSrc, String encName) {
        MessageDigest md = null;
        String strDes = null;

        byte[] bt = strSrc.getBytes();
        try {
            if (encName == null || encName.equals("")) {
                encName = "SHA-256";
            }
            md = MessageDigest.getInstance(encName);
            md.update(bt);
            strDes = bytes2Hex(md.digest()); // to HexString
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        return strDes;
    }

    public static String bytes2Hex(byte[] bts) {
        String des = "";
        String tmp = null;
        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                des += "0";
            }
            des += tmp;
        }
        return des;
    }

    /**
     * 验证密码是否正确，true正确;false错误;
     *
     * @param password        密码
     * @param encryptPassword 加密后的密码
     * @return
     */
    public static boolean verifyPassword(String password, String encryptPassword) {
        boolean result = false;
        if (null != password && !"".equals(password)) {
            String encNew = StringEncrypt.EncryptBySHA_256(password);
            result = encNew.equals(encryptPassword);
        }
        return result;
    }

}