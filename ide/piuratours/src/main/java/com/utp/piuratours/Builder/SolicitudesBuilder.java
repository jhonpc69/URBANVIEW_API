package com.utp.piuratours.Builder;

import com.utp.piuratours.Dto.Solicitudes.SolicitudBandejaDto;
import com.utp.piuratours.Models.Locales;
import com.utp.piuratours.Models.Solicitudes;
import com.utp.piuratours.Services.ImagenesService;
import com.utp.piuratours.Services.LocalesService;

import java.util.List;
import java.util.stream.Collectors;

public class SolicitudesBuilder {

    public static List<SolicitudBandejaDto> BuildearSolicitudes(List<Solicitudes> list, LocalesService localesService, ImagenesService imagenesService) {
        return list.stream().map(solicitud -> {
            Locales local = localesService.obtenerLocalPorId(solicitud.getIdLocal().getIdLocal());
            List<byte[]> imagenes = imagenesService.obtenerImagenesPorIdSolicitudes(solicitud.getIdSolicitud());
            return SolicitudBandejaDto.builder()
                    .idSolicitud(solicitud.getIdSolicitud())
                    .idUsuario(solicitud.getIdUsuario().getIdUsuario())
                    .estado(solicitud.getEstado())
                    .ruc(local.getRuc())
                    .nombreLocal(solicitud.getIdLocal().getNombreLocal())
                    .descripcion(local.getDescripcion())
                    .horaApertura(local.getHoraApertura())
                    .horaCierre(local.getHoraCierre())
                    .idCategoria(solicitud.getIdLocal().getIdCategoria().getIdCategoria())
                    .fechaRegistro(solicitud.getFechaRegistro())
                    .linkInstagram(local.getLink_Instagram())
                    .linkFacebook(local.getLink_Facebook())
                    .linkMaps(local.getLink_Maps())
                    .imagenes(imagenes)
                    .telefono(local.getTelefono())
                    .ubicacion(local.getUbicacion())
                    .rutaPdf(local.getRutaPdf())
                    .imgPortada(local.getImgPortada())
                    .imgDestacado(local.getImgDestacado())
                    .build();
        }).collect(Collectors.toList());
    }
}
