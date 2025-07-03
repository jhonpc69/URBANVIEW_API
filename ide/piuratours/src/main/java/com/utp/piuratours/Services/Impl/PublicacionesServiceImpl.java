package com.utp.piuratours.Services.Impl;

import com.utp.piuratours.Builder.PublicacionesBuilder;
import com.utp.piuratours.Dto.Publicaciones.PublicacionesDto;
import com.utp.piuratours.Models.Comentarios;
import com.utp.piuratours.Models.Publicaciones;
import com.utp.piuratours.Repository.ComentariosRepository;
import com.utp.piuratours.Repository.LocalesRepository;
import com.utp.piuratours.Repository.PublicacionesRepository;
import com.utp.piuratours.Repository.SolicitudesRepository;
import com.utp.piuratours.Services.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PublicacionesServiceImpl implements PublicacionesService {

    private final PublicacionesRepository publicacionesRepository;
    private final LocalesService localesService;
    private final SolicitudesRepository solicitudesRepository;
    private final ImagenesService imagenesService;

    @Lazy
    private final ComentariosService comentariosService;

    @Override
    public Publicaciones GuardarPublicacion(Publicaciones publicacion) {
        return publicacionesRepository.save(publicacion);
    }

    @Override
    public Publicaciones obtenerPublicacionId(Integer idPublicacion) {
        return publicacionesRepository.findById(idPublicacion).get();
    }

    @Override
    public List<PublicacionesDto>
    ObtenerTodasPublicaciones(Integer idCategoria, String titulo) {
        List<Publicaciones> publicaciones = new ArrayList<Publicaciones>();
        if (titulo == null || titulo.isEmpty()) {
            publicaciones = publicacionesRepository.obtenerPublicacionPorCategoria2(idCategoria);

        } else {
            publicaciones = publicacionesRepository.obtenerPublicacionPorCategoria(idCategoria, titulo.toUpperCase());

        }
        return PublicacionesBuilder.BuildearPublicaciones(publicaciones, localesService, solicitudesRepository, imagenesService, comentariosService);
    }

    @Override
    public List<PublicacionesDto>
    ObtenerTodasPublicacionesPorLocal(Integer idLocal) {
        List<Publicaciones> publicaciones = new ArrayList<Publicaciones>();

        publicaciones = publicacionesRepository.obtenerPublicacionPorCategoria3(idLocal);


        return PublicacionesBuilder.BuildearPublicaciones(publicaciones, localesService, solicitudesRepository, imagenesService, comentariosService);
    }


    @Override
    public List<PublicacionesDto>
    listarPublicacionesDestacadas() {
        List<Publicaciones> publicaciones = new ArrayList<Publicaciones>();

        publicaciones = publicacionesRepository.findByEstado("1");

        List<PublicacionesDto> publicacionesResult = PublicacionesBuilder.BuildearPublicaciones(publicaciones, localesService, solicitudesRepository, imagenesService, comentariosService);

        List<PublicacionesDto> top5 = publicacionesResult.stream()
                .sorted(Comparator.comparingDouble(PublicacionesDto::getValoracion).reversed()) // orden descendente
                .limit(5)
                .collect(Collectors.toList());

        return top5;
    }

    @Override
    public List<PublicacionesDto>
    ObtenerTodasPublicaciones2(Integer idCategoria) {
        List<Publicaciones> publicaciones = publicacionesRepository.obtenerPublicacionPorCategoria2(idCategoria);
        return PublicacionesBuilder.BuildearPublicaciones(publicaciones, localesService, solicitudesRepository, imagenesService, comentariosService);
    }
}
