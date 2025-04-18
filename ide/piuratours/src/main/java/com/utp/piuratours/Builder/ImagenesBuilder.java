package com.utp.piuratours.Builder;

import com.utp.piuratours.Dto.Imagenes.ImagenesDto;
import com.utp.piuratours.Models.Imagenes;

import java.util.List;
import java.util.stream.Collectors;

public class ImagenesBuilder {

    public static List<ImagenesDto> BuildearImagenes(List<Imagenes> list) {
        return list.stream().map(registro -> ImagenesDto.builder()
                .descripcion(registro.getDescripcion())
                .build()).collect(Collectors.toList());
    }


    public static List<byte[]> BuildearImagenesByte(List<Imagenes> list) {
        return list.stream()
                .map(Imagenes::getImagen)
                .collect(Collectors.toList());
    }
}
