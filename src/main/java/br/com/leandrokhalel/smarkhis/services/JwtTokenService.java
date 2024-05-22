package br.com.leandrokhalel.smarkhis.services;

import br.com.leandrokhalel.smarkhis.entities.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Service
public class JwtTokenService {

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("API Smarkhis")
                    .withSubject(user.getUsername())
                    .withClaim("userId", user.getId().toString())
                    .withExpiresAt(Instant.now().plus(Duration.ofHours(2L)))
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar token JWT");
        }
    }

    public DecodedJWT validateToken(String tokenJWT) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("API Smarkhis")
                    .build()
                    .verify(tokenJWT);
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Erro ao verificar token JWT");
        }
    }
}
