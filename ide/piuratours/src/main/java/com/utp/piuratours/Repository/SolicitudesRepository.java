package com.utp.piuratours.Repository;

import com.utp.piuratours.Models.Locales;
import com.utp.piuratours.Models.Solicitudes;
import com.utp.piuratours.Models.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SolicitudesRepository extends JpaRepository<Solicitudes,Integer> {
    List<Solicitudes> findByIdLocal(Locales idLocal);

    @Query("Select S from Solicitudes S where S.idLocal.idLocal = :idLocal ")
    Solicitudes buscarporPublicacion(Integer idLocal);
    List<Solicitudes> findByIdUsuario(Usuarios idUsuario);
}
