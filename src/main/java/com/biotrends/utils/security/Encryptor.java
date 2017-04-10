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
public final class Encryptor {

	private static final byte[] sharedvector = {0x01, 0x02, 0x03, 0x05, 0x07, 0x0B, 0x0D, 0x11};

	private Encryptor(){}
	
	public static String encrypt(String text){
		String encText = "";
        byte[] keyArray = new byte[24];
        byte[] temporaryKey;
        String key = "biotrendslaboratoriossas";
        byte[] toEncryptArray = null;
        
        try {
        	toEncryptArray =  text.getBytes("UTF-8");
        	MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        	temporaryKey = messageDigest.digest(key.getBytes("UTF-8"));
        	
        	if(temporaryKey.length < 24)
            {
                int index = 0;
                for(int i=temporaryKey.length;i< 24;i++)
                {                   
                    keyArray[i] =  temporaryKey[index];
                }
            }  
        	Cipher c = Cipher.getInstance("DESede/CBC/PKCS5Padding");            
            c.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(keyArray, "DESede"), new IvParameterSpec(sharedvector));            
            byte[] encrypted = c.doFinal(toEncryptArray);            
            encText = Base64.encodeBase64String(encrypted);
		} catch(NoSuchAlgorithmException | UnsupportedEncodingException | NoSuchPaddingException | InvalidKeyException |
				InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException ex){
            log.error("Error cifrando", ex);
        }
        
        return encText;
	}
}
