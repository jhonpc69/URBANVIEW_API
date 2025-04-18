package com.utp.piuratours.Services.Impl;

import com.utp.piuratours.Builder.ImagenesBuilder;
import com.utp.piuratours.Models.Imagenes;
import com.utp.piuratours.Repository.ImagenesRepository;
import com.utp.piuratours.Repository.LocalesRepository;
import com.utp.piuratours.Services.ImagenesService;
import com.utp.piuratours.Services.LocalesService;
import com.utp.piuratours.Util.ApiExceptionUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class ImagenesServiceImpl implements ImagenesService {

    private final ImagenesRepository imagenesRepository;
    @Override
    public Imagenes guardaImagen(Imagenes imagen) {

        return imagenesRepository.save(imagen);
    }

    public byte[] obtenerImagenPorId(Integer id) {
        Imagenes imagen = imagenesRepository.findById(id)
                .orElseThrow(() -> ApiExceptionUtil.lanzarApiExceptionSinResultados("Imagen con ID: " + id));
        return imagen.getImagen();
    }


    @Override
    public List<byte[]> obtenerImagenesPorIdSolicitud(Integer idSolicitud) {

        return ImagenesBuilder.BuildearImagenesByte(imagenesRepository.obtenerImagenesPorIdSolicitud(idSolicitud));
    }

    @Override
    public List<byte[]> obtenerImagenesPorIdSolicitudes(Integer idLocal) {
        return     ImagenesBuilder.BuildearImagenesByte(imagenesRepository.obtenerImagenesPorIdSolicitudes(idLocal));
    }
}
