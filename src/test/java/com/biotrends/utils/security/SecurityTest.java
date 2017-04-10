package com.biotrends.utils.security;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.context.annotation.Description;

/**
 * @author Oscar Malagon
 * @since 09/04/2017
 */
public class SecurityTest {
	
	@Test
	@Description("Verificar cifrado")
	public void verificarCifrado(){
		String texto = "OscarMalagon";
		
		String encrypted = Encryptor.encrypt(texto);
		String decrypted = Decryptor.decryptText(encrypted);
		
		assertEquals(texto, decrypted);
	}

}
