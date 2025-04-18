package com.utp.piuratours.Repository;

import com.utp.piuratours.Models.Comentarios;
import com.utp.piuratours.Models.Locales;
import com.utp.piuratours.Models.Publicaciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentariosRepository extends JpaRepository<Comentarios,Integer> {
    List<Comentarios> findByIdPublicacion(Publicaciones idPublicacion);
}
