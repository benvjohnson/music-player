package com.musicPlayer.musicPlayerApp.Service.Utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import jakarta.xml.bind.DatatypeConverter;

/**
 * Class For Password Hashing 
 */
public class PasswordEncrypter 
{
    //function to encrypt password
    public static String encryptPassword (String userPassword) throws NoSuchAlgorithmException 
    {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(userPassword.getBytes());
        byte[] digested = md5.digest();
        return DatatypeConverter.printHexBinary(digested);
    }
    
}
