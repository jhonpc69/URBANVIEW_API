package com.utp.piuratours.Repository;

import com.utp.piuratours.Models.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UsuariosRepository extends JpaRepository<Usuarios,Integer> {
    Optional<Usuarios> getUsuariosByDniUsuario(String dni);
}
