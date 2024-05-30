package cc.wordview.api.service.util;

import static java.util.Objects.isNull;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.apache.commons.codec.binary.Hex;

import cc.wordview.api.database.entity.User;

public class HashedPassword {
	private String hashed;

	private String email;

	private String password;

	public HashedPassword(User entity) throws InvalidKeySpecException {
		this.email = entity.getEmail();
		this.password = entity.getPassword();

		KeySpec hashSpecifications = getKeySpecifications();

		String generatedHashedPassword = generateHash("PBKDF2WithHmacSHA512", hashSpecifications);

		this.setHashedPassword(generatedHashedPassword);
	}

	private PBEKeySpec getKeySpecifications() throws InvalidKeySpecException {
		if (isNull(email) || isNull(password))
			throw new InvalidKeySpecException("Neither email or password should be null");

		if (email.isEmpty() || password.isEmpty())
			throw new InvalidKeySpecException("Neither email or password should be empty");

		byte[] salt = (email + password).getBytes();

		return new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
	}

	private String generateHash(String algorithm, KeySpec hashSpecifications) {
		try {
			SecretKeyFactory sKFactory = SecretKeyFactory.getInstance(algorithm);

			byte[] hashedPassword = sKFactory.generateSecret(hashSpecifications).getEncoded();

			return Hex.encodeHexString(hashedPassword);

		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();

			return null;
		}
	}

	public String getValue() {
		return hashed;
	}

	public void setHashedPassword(String hashedPassword) {
		this.hashed = hashedPassword;
	}
}
