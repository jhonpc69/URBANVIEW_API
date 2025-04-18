package com.utp.piuratours.Repository;

import com.utp.piuratours.Models.Imagenes;
import com.utp.piuratours.Models.Locales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ImagenesRepository extends JpaRepository<Imagenes,Integer> {

    @Query("SELECT I FROM Imagenes I WHERE I.idSolicitud.idLocal.idLocal = :idSolicitud")
    List<Imagenes> obtenerImagenesPorIdSolicitud(Integer idSolicitud);
    @Query("SELECT I FROM Imagenes I WHERE I.idSolicitud.idSolicitud = :idSolicitud")
    List<Imagenes> obtenerImagenesPorIdSolicitudes(Integer idSolicitud);
}
