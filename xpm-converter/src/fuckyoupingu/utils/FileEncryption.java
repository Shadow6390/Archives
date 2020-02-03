/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fuckyoupingu.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 *
 * @author jbraga
 */
/**
 *
 * @author jbraga
 */
public final class FileEncryption {
    
    private static final String INSTANCE = "DES";
    
    private FileEncryption()
    {
        
    }
    
    public static void encryptToFile(byte[] data,String filePath) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, FileNotFoundException, IOException 
    {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(INSTANCE);
        SecretKey key = keyGenerator.generateKey();
        
        Cipher cipher;
        cipher = Cipher.getInstance(INSTANCE);
        
        cipher.init(Cipher.ENCRYPT_MODE, key);
        
        byte[] eData = cipher.doFinal(data);
        
        FileOutputStream fos = new FileOutputStream(filePath);
        fos.write(key.getEncoded().length);
        fos.write(key.getEncoded());
        fos.write(eData);
        fos.close();
    }
    
    public static byte[] decryptFromFile(String filePath) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, FileNotFoundException, IOException, InvalidKeySpecException 
    {
        byte[] result;
        FileInputStream fis = new FileInputStream(filePath);
        int keyLength = fis.read();
        byte[] encodedKey = new byte[keyLength];
        fis.read(encodedKey);
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int bytesRead;
        while (fis.available()!=0)
        {
            byte[] temp = new byte[fis.available()];
            bytesRead=fis.read(temp);
            buffer.write(temp,0,bytesRead);
        }
        byte[] data=buffer.toByteArray();
        fis.close();
        SecretKey key = SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(encodedKey));
        
        Cipher cipher;
        cipher = Cipher.getInstance(INSTANCE);
        
        cipher.init(Cipher.DECRYPT_MODE, key);
        
        result = cipher.doFinal(data);
        return result;
    }
    
    
    public static void fromResourceToFile(InputStream stream,String filename) throws IOException
    {
        FileOutputStream fos = new FileOutputStream(filename);
        int bytesRead=0;
        while (stream.available()!=0)
        {
            byte[] temp = new byte[stream.available()];
            bytesRead=stream.read(temp);
            fos.write(temp,0,bytesRead);
        }
        fos.close();
    }
}
