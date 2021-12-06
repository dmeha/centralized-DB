package com.centdb.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Hashing {
	public byte[] hash;
	public String passwordHashed;
	public Hashing(String password) {
		try {
			byte[] salt = new byte[16];
			for (int i = 0; i < 16; i++) {
		        salt[i] = (byte) i;
		    }
			KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 100, 128);
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			hash = factory.generateSecret(spec).getEncoded();
			String hashedPassword = new String(hash);
			passwordHashed = hashedPassword;
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
		}
	}
}
