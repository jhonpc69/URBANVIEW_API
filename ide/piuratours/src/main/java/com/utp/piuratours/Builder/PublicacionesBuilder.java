package com.utp.piuratours.Builder;

import com.utp.piuratours.Dto.Comentarios.ComentariosDto;
import com.utp.piuratours.Dto.Publicaciones.PublicacionesDto;
import com.utp.piuratours.Models.Comentarios;
import com.utp.piuratours.Models.Locales;
import com.utp.piuratours.Models.Publicaciones;
import com.utp.piuratours.Repository.ComentariosRepository;
import com.utp.piuratours.Repository.SolicitudesRepository;
import com.utp.piuratours.Services.ComentariosService;
import com.utp.piuratours.Services.ImagenesService;
import com.utp.piuratours.Services.LocalesService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PublicacionesBuilder {

    public static List<PublicacionesDto> BuildearPublicaciones(List<Publicaciones> list, LocalesService localesService, SolicitudesRepository solicitudesRepository, ImagenesService imagenesService, ComentariosService comentariosService) {
        // Retrieve all necessary data in bulk
        Map<Integer, Locales> localesMap = list.stream()
                .map(Publicaciones::getIdLocal)
                .map(local -> localesService.obtenerLocalPorId(local.getIdLocal()))
                .collect(Collectors.toMap(Locales::getIdLocal, local -> local));

        Map<Integer, List<byte[]>> imagenesMap = localesMap.values().stream()
                .collect(Collectors.toMap(Locales::getIdLocal, local -> imagenesService.obtenerImagenesPorIdSolicitud(local.getIdLocal())));

        Map<Integer, List<ComentariosDto>> comentariosMap = list.stream()
                .collect(Collectors.toMap(Publicaciones::getIdPublicacion, publicacion -> {
                    List<ComentariosDto> comentarios = comentariosService.findByIdPublicacion(publicacion);
                    return comentarios != null ? comentarios : List.of();
                }));

        return list.stream().map(publicacion -> {
            Locales local = localesMap.get(publicacion.getIdLocal().getIdLocal());
            List<byte[]> imagenes = imagenesMap.get(local.getIdLocal());
            List<ComentariosDto> comentarios = comentariosMap.get(publicacion.getIdPublicacion());
            Double valoracion = comentarios.stream().mapToDouble(ComentariosDto::getValoracion).average().orElse(0);

            return PublicacionesDto.builder()
                    .idUsuario(solicitudesRepository.buscarporPublicacion(local.getIdLocal()).getIdUsuario().getIdUsuario())
                    .idPublicacion(publicacion.getIdPublicacion())
                    .idLocal(local.getIdLocal())
                    .estado(publicacion.getEstado())
                    .Titulo(publicacion.getTitulo())
                    .descripcion(local.getDescripcion())
                    .horaApertura(local.getHoraApertura())
                    .horaCierre(local.getHoraCierre())
                    .fechaPublicacion(publicacion.getFechaPublicacion())
                    .linkInstagram(local.getLink_Instagram())
                    .linkFacebook(local.getLink_Facebook())
                    .linkMaps(local.getLink_Maps())
                    .comentarios(comentarios)
                    .imagenes(imagenes)
                    .valoracion(valoracion)
                    .build();
        }).collect(Collectors.toList());
    }
}