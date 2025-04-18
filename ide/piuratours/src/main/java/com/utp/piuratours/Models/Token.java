package com.utp.piuratours.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public final class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="token",unique = true)
    private String token;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    @Column(name = "token_type")
    private TokenType tokenType = TokenType.BEARER;

    @Column(name = "is_revoked", nullable = false)
    private Boolean isRevoked;

    @Column(name = "is_expired", nullable = false)
    private Boolean isExpired;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Usuarios user;

    public enum TokenType {
        BEARER
    }

}
