package com.utp.piuratours.Builder;

import com.utp.piuratours.Dto.Categorias.CategoriasDto;
import com.utp.piuratours.Dto.Locales.LocalesDto;
import com.utp.piuratours.Models.Categoria;
import com.utp.piuratours.Models.Locales;

import java.util.List;
import java.util.stream.Collectors;

public class LocalesBuilder {
    public static List<LocalesDto> BuildearLocales(List<Locales> list) {
        return list.stream().map(registro -> LocalesDto.builder()
                .idLocal(registro.getIdLocal())
                .nombreLocal(registro.getNombreLocal())
                .direccion(registro.getUbicacion())
                .telefono(registro.getTelefono())
                .valoracion(registro.getValoracion())
                .descripcion(registro.getDescripcion())
                .horaApertura(registro.getHoraApertura())
                .horaCierre(registro.getHoraCierre())
                .estado(registro.getEstado())
                .idCategoria(registro.getIdCategoria().getIdCategoria())
                .build()).collect(Collectors.toList());
    }
}
