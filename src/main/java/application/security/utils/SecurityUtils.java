package application.security.utils;

import org.apache.commons.codec.binary.Base64;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * Util methods in order to generate Public/Private RSA keys from Strings
 *
 * Created by matan,
 * On 03/10/2016.
 */

public final class SecurityUtils {

    public static PrivateKey getPrivateKeyFromString(String keyString) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] base64 = getEncodedKeyFromString(keyString);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(base64);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(keySpec);
    }

    public static PublicKey getPublicKeyFromString(String keyString) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] base64 = getEncodedKeyFromString(keyString);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(base64);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(keySpec);
    }

    private static byte[] getEncodedKeyFromString(String keyString) {
        Base64 base64 = new Base64();
        return base64.decode(keyString);
    }

}
