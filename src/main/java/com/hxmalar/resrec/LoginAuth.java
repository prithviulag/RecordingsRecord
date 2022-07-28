package com.hxmalar.resrec;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

public class LoginAuth
{
    protected Boolean AttemptLogin(String inputPassword)
            throws InvalidKeySpecException, NoSuchAlgorithmException
    {
        SecureRandom randomGen = new SecureRandom();
        byte[] salt = new byte[16];
        randomGen.nextBytes(salt);

        KeySpec keySpecif = new PBEKeySpec(inputPassword.toCharArray(), salt, 65536, 128);
        SecretKeyFactory keyFact = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hashPassword = keyFact.generateSecret(keySpecif).getEncoded();

        return false;
    }
}
