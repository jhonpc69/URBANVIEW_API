package com.utp.piuratours.Repository;

import com.utp.piuratours.Models.Locales;
import com.utp.piuratours.Models.Publicaciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicacionesRepository extends JpaRepository<Publicaciones,Integer> {

    @Query("Select P from Publicaciones P where P.idLocal.idCategoria.idCategoria = :idCategoria and P.estado='1' and P.Titulo LIKE %:titulo%")
    List<Publicaciones> obtenerPublicacionPorCategoria(Integer idCategoria,String titulo);

    @Query("Select P from Publicaciones P where P.idLocal.idCategoria.idCategoria = :idCategoria and P.estado='1' ")
    List<Publicaciones> obtenerPublicacionPorCategoria2(Integer idCategoria);


    @Query("Select P from Publicaciones P where P.idLocal.idLocal = :idLodal and P.estado='1' ")
    List<Publicaciones> obtenerPublicacionPorCategoria3(Integer idLodal);

    List<Publicaciones> findByEstado(String estado);
}
