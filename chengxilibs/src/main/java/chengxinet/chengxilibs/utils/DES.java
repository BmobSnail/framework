package chengxinet.chengxilibs.utils;

import android.util.Base64;

import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * Created by Administrator on 2016/4/14.
 */
public class DES {
    private static String DESKey = "1234abcd"; // 字节数必须是8的倍数
    private static byte[] iv1 = {(byte)0x12, (byte)0x34, (byte)0x56, (byte)0x78, (byte)0x90, (byte)0xAB, (byte)0xCD, (byte)0xEF};

    public String encode(byte[] data) throws Exception
    {
        try
        {
            DESKeySpec dks = new DESKeySpec(DESKey.getBytes());

            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            //key的长度不能够小于8位字节
            Key secretKey = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            AlgorithmParameterSpec paramSpec = new IvParameterSpec(DESKey.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, secretKey,paramSpec);

            byte[] bytes = cipher.doFinal(data);


//      return byte2hex(bytes);
            return new String(Base64.encode(bytes, Base64.DEFAULT));
        } catch (Exception e)
        {
            throw new Exception(e);
        }
    }



    public String decode(String str) throws Exception {
        IvParameterSpec iv = new IvParameterSpec(iv1);

        DESKeySpec dks = new DESKeySpec(DESKey.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey key = keyFactory.generateSecret(dks);
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key, iv);
        byte data[] = str.getBytes();
        byte encryptedData[] = cipher.doFinal(Base64.decode(data, Base64.DEFAULT));
        return new String(encryptedData,"UTF-8");
    }

}
