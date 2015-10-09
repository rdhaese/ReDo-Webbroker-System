package org.realdolmen.webbroker.service;

import org.realdolmen.webbroker.model.user.User;
import org.realdolmen.webbroker.util.Pair;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.Random;

/**
 * Service to create secure passwords and to determine if the password of a user is correct.
 *
 * @author Youri Flement
 */
@Stateless
@LocalBean
public class PasswordService implements Serializable {

    private static final int HASH_ITERATIONS = 65536;

    private static final int HASH_LENGTH = 256;

    private static final int SALT_LENGTH = 16;

    private static final String HASH_ALGORITHM = "PBKDF2WithHmacSHA512";

    /**
     * Create a secure password by hashing and salting the input password. Returns a {@link Pair} with the hash and salt.
     * Use {@link Pair#getFirst()} to retrieve the salt and {@link Pair#getSecond()} to retrieve the hash.
     *
     * @param password The password to secure.
     * @return  The salt and hash.
     */
    public Pair<String, String> createSecurePassword(String password) {
        return createSecurePassword(createSalt(), password);
    }

    /**
     * Create a secure password based on a salt and a plaintext password.
     *
     * @param salt  The salt to use.
     * @param password  The plaintext password.
     * @return  The salt in String representation and the hashed password.
     */
    private Pair<String, String> createSecurePassword(byte[] salt, String password) {
        try {
            String hashedPassword = byteArrayToString(hash(password, salt));
            return new Pair<>(byteArrayToString(salt), hashedPassword);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            return null;
        }
    }

    /**
     * Determine whether the plaintext password matches the saved password of the user.
     *
     * @param user  The user.
     * @param unhashedPassword The plaintext password.
     * @return <code>true</code> if the password is correct, <code>false</code> otherwise.
     */
    public boolean isCorrectPassword(User user, String unhashedPassword) {
        if(user == null || unhashedPassword == null || unhashedPassword.isEmpty()) {
            return false;
        }
        Pair<String, String> securePassword = createSecurePassword(stringToByteArray(user.getSalt()), unhashedPassword);
        if (securePassword != null) {
            return securePassword.getSecond().equals(user.getPassword());
        } else {
            return false;
        }
    }

    /**
     * Create a random salt with length {@link PasswordService#SALT_LENGTH}.
     *
     * @return a random salt.
     */
    protected byte[] createSalt() {
        byte[] salt = new byte[SALT_LENGTH];
        Random random = new Random();
        random.nextBytes(salt);
        return salt;
    }

    /**
     * Hash the input string with the given salt. Uses {@link PasswordService#HASH_ITERATIONS} to generate a hash
     * of length {@link PasswordService#HASH_LENGTH}. Uses the algorithm specified by {@link PasswordService#HASH_ALGORITHM}.
     *
     * @param password  The string to hash.
     * @param salt  The salt to use.
     * @return  the hashed string.
     * @throws NoSuchAlgorithmException If the hash algorithm was not found - should never occur.
     * @throws InvalidKeySpecException If the key spec was invalid - should never occur.
     */
    protected byte[] hash(String password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, HASH_ITERATIONS, HASH_LENGTH);
        SecretKeyFactory f = SecretKeyFactory.getInstance(HASH_ALGORITHM);
        return f.generateSecret(spec).getEncoded();
    }

    private String byteArrayToString(byte[] byteArray) {
        return Base64.getEncoder().encodeToString(byteArray);
    }

    private byte[] stringToByteArray(String s) {
        return Base64.getDecoder().decode(s);
    }
}
