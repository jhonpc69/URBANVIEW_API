package com.utp.piuratours.Services;

import com.utp.piuratours.Dto.Publicaciones.PublicacionesDto;
import com.utp.piuratours.Dto.Solicitudes.SolicitudBandejaDto;
import com.utp.piuratours.Dto.Solicitudes.SolicitudRegistroDto;
import com.utp.piuratours.Models.Locales;
import com.utp.piuratours.Models.Publicaciones;
import com.utp.piuratours.Models.Solicitudes;
import org.springframework.cglib.core.Local;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface SolicitudesService {

    SolicitudRegistroDto grabarSolicitud(Integer idUsuario, String ruc,String nombreLocal, String telefono,String diasSemana, String horaApertura, String horaCierre, Integer idCategoria, List<MultipartFile> imagenes,MultipartFile fichaRuc,String descripcion,String ubicacion,String linkFacebook,String linkInstagram,String linkMaps,MultipartFile imgPortada,MultipartFile imgDestacado) throws IOException;
    PublicacionesDto decisionSolicitud(Integer idSolicitud, String decision,String comentario);

    Solicitudes obtenerSolicitudPorLocal(Locales idLocal);

    List<SolicitudBandejaDto> listarSolicitudes();

    List<SolicitudBandejaDto> listarSolicitudesPorIdUsuario(Integer idUsuario);

    byte[] descargarAdjunto(String ruta);
}
