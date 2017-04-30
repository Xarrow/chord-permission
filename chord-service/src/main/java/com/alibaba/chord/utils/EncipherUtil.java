package com.alibaba.chord.utils;

import java.security.Key;

import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by wb-zj268791 on 2017/4/3.
 */
public class EncipherUtil {
    /**
     * @return
     */
    public static String generateSalt() {
        SecureRandomNumberGenerator secureRandom = new SecureRandomNumberGenerator();
        return secureRandom.nextBytes(2).toHex();
    }

    /**
     * md5(password+salt)
     *
     * @param password
     * @param salt
     * @return
     */
    public static String encryptedPwd(String password, String salt) {

        return new Md5Hash(password, salt, 2).toHex();
    }

    /**
     * @param text
     * @param token
     * @return
     */
    public static String aesEncryptByToken(String text, String token) {
        AesCipherService aesCipherService = new AesCipherService();
        aesCipherService.setKeySize(128);
        return aesCipherService.encrypt(text.getBytes(), token.getBytes()).toHex();
    }

    /**
     * @param encryptedStr
     * @param token
     * @return
     */
    public static String aesDecryptByToken(String encryptedStr, String token) {
        AesCipherService aesCipherService = new AesCipherService();
        aesCipherService.setKeySize(128);
        return new String(aesCipherService.decrypt(Hex.decode(encryptedStr), token.getBytes()).getBytes());
    }

    public static void main(String[] args) {
        aesDecryptByToken("YVOSwahuIz6dJhZqAp5oqg==","0102030405060708");
    }
}
