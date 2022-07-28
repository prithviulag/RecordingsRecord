package com.hxmalar.resrec;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

public class LoginAuth
{
    private static byte[] saltTemp; //temporary, for verifying that hashing works
    protected static byte[] GenerateHash(String input, byte[] hashSalt)
            throws IllegalArgumentException
    {
        try
        {
            KeySpec keySpecif = new PBEKeySpec(input.toCharArray(), hashSalt, 65536, 128);
            SecretKeyFactory keyFact = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] earlyHashPass = keyFact.generateSecret(keySpecif).getEncoded();
            System.out.println(earlyHashPass);

            byte[] hashPassword = keyFact.generateSecret(keySpecif).getEncoded();
            return hashPassword;
        }
        catch (InvalidKeySpecException|NoSuchAlgorithmException x)
        {
            throw new IllegalArgumentException(x.getMessage());
        }
    }
    protected static Boolean RegisterLogin(String newPass)
            throws IllegalArgumentException
    {
        SecureRandom randomGen = new SecureRandom();
        byte[] newSalt = new byte[16];
        randomGen.nextBytes(newSalt);
        saltTemp = newSalt;

        byte[] newHash = GenerateHash(newPass, newSalt);
        System.out.println(newSalt.toString());
        System.out.println(newHash);

        return true;
    }
    protected static Boolean VerifyLogin(String enteredPass)
            throws IllegalArgumentException
    {
        byte[] checkedHash = GenerateHash(enteredPass, saltTemp);
        System.out.println(saltTemp.toString());
        System.out.println(checkedHash);

        return true;
    }
}
