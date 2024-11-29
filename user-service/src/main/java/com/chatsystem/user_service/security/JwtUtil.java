package com.chatsystem.user_service.security;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

@Component
public class JwtUtil {

  private static final String PUBLIC_KEY_PATH = "src/main/resources/key/public_key.pem";

  private static PublicKey getPublicKey() throws Exception {
    String publicKeyPEM =
        new String(Files.readAllBytes(Paths.get(PUBLIC_KEY_PATH)))
            .replace("-----BEGIN PUBLIC KEY-----", "")
            .replace("-----END PUBLIC KEY-----", "")
            .replaceAll("\\s", "");
    byte[] keyBytes = java.util.Base64.getDecoder().decode(publicKeyPEM);
    X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    return keyFactory.generatePublic(spec);
  }

  public static void validateToken(String token) throws Exception {
    PublicKey publicKey = getPublicKey();
    Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token).getBody();
  }
}
