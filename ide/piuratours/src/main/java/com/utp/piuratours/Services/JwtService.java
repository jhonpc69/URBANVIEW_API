package com.utp.piuratours.Services;

import com.utp.piuratours.Models.Usuarios;

import javax.crypto.SecretKey;
import java.util.Date;

public interface JwtService {
    public String extractUsername(String token);
    public String generateToken(final Usuarios user);
    public String generateRefreshToken(final Usuarios user);
    public String buildToken(final Usuarios user, final long expiration);
    public boolean isTokenValid(String token, Usuarios user);
    public boolean isTokenExpired(String token);
    public Date extractExpiration(String token);
    public SecretKey getSignInKey();


}
