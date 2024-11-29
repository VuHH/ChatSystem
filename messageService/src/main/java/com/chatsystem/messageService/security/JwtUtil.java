package com.chatsystem.messageService.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Date;

public class JwtUtil {

  private static final String PRIVATE_KEY_PATH = "src/main/resources/key/private_key.pem";
  private static final long EXPIRATION_TIME = 3600000; // 1 hour

  private static PrivateKey getPrivateKey() throws Exception {
    String privateKeyPEM =
        new String(Files.readAllBytes(Paths.get(PRIVATE_KEY_PATH)))
            .replace("-----BEGIN PRIVATE KEY-----", "")
            .replace("-----END PRIVATE KEY-----", "")
            .replaceAll("\\s", "");
    byte[] keyBytes = java.util.Base64.getDecoder().decode(privateKeyPEM);
    PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    return keyFactory.generatePrivate(spec);
  }

  public static String generateToken(String subject) throws Exception {
    PrivateKey privateKey = getPrivateKey();
    return Jwts.builder()
        .setSubject(subject)
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
        .signWith(SignatureAlgorithm.RS256, privateKey)
        .compact();
  }
}
