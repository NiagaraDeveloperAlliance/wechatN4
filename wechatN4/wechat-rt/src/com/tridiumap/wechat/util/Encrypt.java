package com.tridiumap.wechat.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Encrypt
{

  public static String SHA1(String decript)
  {
    try
    {
      MessageDigest digest = java.security.MessageDigest
        .getInstance("SHA-1");
      digest.update(decript.getBytes());
      byte messageDigest[] = digest.digest();
      // Create Hex String
      StringBuffer hexString = new StringBuffer();
      // byte[] -> hex string
      for (int i = 0; i < messageDigest.length; i++)
      {
        String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
        if (shaHex.length() < 2)
        {
          hexString.append(0);
        }
        hexString.append(shaHex);
      }
      return hexString.toString();

    }
    catch (NoSuchAlgorithmException e)
    {
      e.printStackTrace();
    }
    return "";
  }

  public static String SHA(String decript)
  {
    try
    {
      MessageDigest digest = java.security.MessageDigest
        .getInstance("SHA");
      digest.update(decript.getBytes());
      byte messageDigest[] = digest.digest();
      // Create Hex String
      StringBuffer hexString = new StringBuffer();
      // byte[] -> hex string
      for (int i = 0; i < messageDigest.length; i++)
      {
        String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
        if (shaHex.length() < 2)
        {
          hexString.append(0);
        }
        hexString.append(shaHex);
      }
      return hexString.toString();

    }
    catch (NoSuchAlgorithmException e)
    {
      e.printStackTrace();
    }
    return "";
  }

  public static String MD5(String input)
  {
    try
    {
      MessageDigest mdInst = MessageDigest.getInstance("MD5");
      mdInst.update(input.getBytes());
      byte[] md = mdInst.digest();
      StringBuffer hexString = new StringBuffer();
      for (int i = 0; i < md.length; i++)
      {
        String shaHex = Integer.toHexString(md[i] & 0xFF);
        if (shaHex.length() < 2)
        {
          hexString.append(0);
        }
        hexString.append(shaHex);
      }
      return hexString.toString();
    }
    catch (NoSuchAlgorithmException e)
    {
      e.printStackTrace();
    }
    return "";
  }

  public static byte[] encryptAES(String content, String password)
  {
    try
    {
      KeyGenerator kgen = KeyGenerator.getInstance("AES");
      kgen.init(128, new SecureRandom(password.getBytes()));
      SecretKey secretKey = kgen.generateKey();
      byte[] enCodeFormat = secretKey.getEncoded();
      SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
      Cipher cipher = Cipher.getInstance("AES");//
      byte[] byteContent = content.getBytes("utf-8");
      cipher.init(Cipher.ENCRYPT_MODE, key);//
      byte[] result = cipher.doFinal(byteContent);
      return result; //
    }
    catch (NoSuchAlgorithmException e)
    {
      e.printStackTrace();
    }
    catch (NoSuchPaddingException e)
    {
      e.printStackTrace();
    }
    catch (InvalidKeyException e)
    {
      e.printStackTrace();
    }
    catch (UnsupportedEncodingException e)
    {
      e.printStackTrace();
    }
    catch (IllegalBlockSizeException e)
    {
      e.printStackTrace();
    }
    catch (BadPaddingException e)
    {
      e.printStackTrace();
    }
    return null;
  }

  public static byte[] decryptAES(byte[] content, String password)
  {
    try
    {
      KeyGenerator kgen = KeyGenerator.getInstance("AES");
      kgen.init(128, new SecureRandom(password.getBytes()));
      SecretKey secretKey = kgen.generateKey();
      byte[] enCodeFormat = secretKey.getEncoded();
      SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
      Cipher cipher = Cipher.getInstance("AES");//
      cipher.init(Cipher.DECRYPT_MODE, key);//
      byte[] result = cipher.doFinal(content);
      return result; //
    }
    catch (NoSuchAlgorithmException e)
    {
      e.printStackTrace();
    }
    catch (NoSuchPaddingException e)
    {
      e.printStackTrace();
    }
    catch (InvalidKeyException e)
    {
      e.printStackTrace();
    }
    catch (IllegalBlockSizeException e)
    {
      e.printStackTrace();
    }
    catch (BadPaddingException e)
    {
      e.printStackTrace();
    }
    return null;
  }

  public static String decryptBASE64(String key)
  {
    return "";
  }

  public static String encryptBASE64(String key)
  {
    return "";
  }

}
