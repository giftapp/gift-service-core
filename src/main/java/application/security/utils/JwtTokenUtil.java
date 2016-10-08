package application.security.utils;

import application.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Utils methods to generate and consume JWT tokens
 *
 * @author Matan Lachmish
 * @date 06/10/2016
 */
@Component
public class JwtTokenUtil {

    private static final String CLAIM_KEY_USER_ID = "userId";
    private static final String CLAIM_KEY_USER_PHONE_NUMBER = "userPhoneNumber";
    private static final String CLAIM_KEY_TOKEN_TYPE = "tokenType";
    private static final String CLAIM_KEY_CREATED = "created";

    private static final String TOKEN_TYPE_ACCESS = "accessToken";

    @Value("${security.identityPrivateKey}")
    private String privateKey;

    @Value("${security.identityPublicKey}")
    private String publicKey;

    @Value("${security.token.expiration}")
    private Long expiration;

    public String getUserIdFromToken(String token) {
        String userId;
        try {
            final Claims claims = getClaimsFromToken(token);
            userId = claims.get(CLAIM_KEY_USER_ID, String.class);
        } catch (Exception e) {
            userId = null;
        }
        return userId;
    }

    public String getUserPhoneNumberFromToken(String token) {
        String userPhoneNumber;
        try {
            final Claims claims = getClaimsFromToken(token);
            userPhoneNumber = claims.get(CLAIM_KEY_USER_PHONE_NUMBER, String.class);
        } catch (Exception e) {
            userPhoneNumber = null;
        }
        return userPhoneNumber;
    }

    public String getTokenTypeFromToken(String token) {
        String tokenType;
        try {
            final Claims claims = getClaimsFromToken(token);
            tokenType = claims.get(CLAIM_KEY_TOKEN_TYPE, String.class);
        } catch (Exception e) {
            tokenType = null;
        }
        return tokenType;
    }


    public Date getCreatedDateFromToken(String token) {
        Date created;
        try {
            final Claims claims = getClaimsFromToken(token);
            created = new Date((Long) claims.get(CLAIM_KEY_CREATED));
        } catch (Exception e) {
            created = null;
        }
        return created;
    }

    public Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }

    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(SecurityUtils.getPublicKeyFromString(publicKey))
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(User user) throws InvalidKeySpecException, NoSuchAlgorithmException {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USER_ID, user.getId().toString());
        claims.put(CLAIM_KEY_USER_PHONE_NUMBER, user.getPhoneNumber());
        claims.put(CLAIM_KEY_TOKEN_TYPE, TOKEN_TYPE_ACCESS);
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    String generateToken(Map<String, Object> claims) throws InvalidKeySpecException, NoSuchAlgorithmException {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.RS256, SecurityUtils.getPrivateKeyFromString(privateKey))
                .compact();
    }

    public String refreshToken(String token) {
        String refreshedToken;
        try {
            final Claims claims = getClaimsFromToken(token);
            claims.put(CLAIM_KEY_CREATED, new Date());
            refreshedToken = generateToken(claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    public Boolean validateToken(String token) {
        //validate only signing and expiration date
        return !isTokenExpired(token);
    }
}