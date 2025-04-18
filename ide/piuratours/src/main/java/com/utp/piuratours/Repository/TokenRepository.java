package com.utp.piuratours.Repository;

import com.utp.piuratours.Models.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {

    @Query(value = """
            select t from Token t inner join Usuarios u\s
            on t.user.idUsuario = u.idUsuario\s
            where u.idUsuario = :id and (t.isExpired = false or t.isRevoked = false)\s
            """)
    List<Token> findAllValidTokenByUser(Integer id);

    Optional<Token> findByToken(String token);
}
