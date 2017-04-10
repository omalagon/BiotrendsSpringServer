package com.biotrends.utils.security;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Oscar Malagon
 * @since 09/04/2017
 */
@Slf4j
public final class Decryptor {

	private static final byte[] sharedvector = {0x01, 0x02, 0x03, 0x05, 0x07, 0x0B, 0x0D, 0x11};
	
	private Decryptor(){}
	
	public static String decryptText(String encText){
 
        String text = "";
        byte[] keyArray = new byte[24];
        byte[] temporaryKey;
        String key = "biotrendslaboratoriossas";
        
        try
        {
            MessageDigest m = MessageDigest.getInstance("MD5");
            temporaryKey = m.digest(key.getBytes("UTF-8"));           
 
            if(temporaryKey.length < 24){
                int index = 0;
                for(int i=temporaryKey.length;i< 24;i++)
                {                  
                    keyArray[i] =  temporaryKey[index];
                }
            }
            
            Cipher c = Cipher.getInstance("DESede/CBC/PKCS5Padding");
            c.init(Cipher.DECRYPT_MODE, new SecretKeySpec(keyArray, "DESede"), new IvParameterSpec(sharedvector));
            byte[] decrypted = c.doFinal(Base64.decodeBase64(encText));   
 
            text = new String(decrypted, "UTF-8");                    
        }
        catch(NoSuchAlgorithmException | UnsupportedEncodingException | NoSuchPaddingException | InvalidKeyException |
        		InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException ex)
        {
        	log.error("Error descifrando", ex);
        }      
 
        return text; 
 
    }
}
