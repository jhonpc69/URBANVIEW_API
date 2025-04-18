package com.utp.piuratours.Services;


import com.utp.piuratours.Models.Imagenes;

import java.util.List;

public interface ImagenesService {
    Imagenes guardaImagen (Imagenes imagen);
    byte[] obtenerImagenPorId(Integer id);

    List<byte[]> obtenerImagenesPorIdSolicitud(Integer idLocal);
    List<byte[]> obtenerImagenesPorIdSolicitudes(Integer idLocal);
}
