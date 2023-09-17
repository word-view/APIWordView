package cc.wordview.api.security;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import cc.wordview.api.database.entity.User;

import org.apache.commons.codec.binary.Hex;

public class HashedPassword {
        private String hashed;

        private String email;

        private String password;

        public HashedPassword(User entity) {
                this.email = entity.getEmail();
                this.password = entity.getPassword();

                KeySpec hashSpecifications = getKeySpecifications();

                String generatedHashedPassword = generateHash("PBKDF2WithHmacSHA512",
                                hashSpecifications);

                this.setHashedPassword(generatedHashedPassword);
        }

        private PBEKeySpec getKeySpecifications() {
                byte[] salt = (email + password).getBytes();

                return new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        }

        private String generateHash(String algorithm, KeySpec hashSpecifications) {
                try {
                        SecretKeyFactory sKFactory = SecretKeyFactory.getInstance(algorithm);

                        byte[] hashedPassword = sKFactory.generateSecret(hashSpecifications)
                                        .getEncoded();

                        return Hex.encodeHexString(hashedPassword);

                }
                catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                        e.printStackTrace();

                        return null;
                }
        }

        public String getValue() { return hashed; }

        public void setHashedPassword(String hashedPassword) { this.hashed = hashedPassword; }
}
