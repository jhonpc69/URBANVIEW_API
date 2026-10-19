package com.utp.piuratours.Repository;

import com.utp.piuratours.Models.Usuarios;
import com.utp.piuratours.Models.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface VerificationCodeRepository extends JpaRepository<VerificationCode, Long> {
    @org.springframework.data.jpa.repository.Lock(jakarta.persistence.LockModeType.PESSIMISTIC_WRITE)
    Optional<VerificationCode> findByUsuario(Usuarios usuario);
}
