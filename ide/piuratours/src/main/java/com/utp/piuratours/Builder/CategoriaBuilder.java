package com.utp.piuratours.Builder;

import com.utp.piuratours.Dto.Categorias.CategoriasDto;
import com.utp.piuratours.Dto.Imagenes.ImagenesDto;
import com.utp.piuratours.Models.Categoria;
import com.utp.piuratours.Models.Imagenes;

import java.util.List;
import java.util.stream.Collectors;

public class CategoriaBuilder {
    public static List<CategoriasDto> BuildearCategoria(List<Categoria> list) {
        return list.stream().map(registro -> CategoriasDto.builder()
                .idCategoria(registro.getIdCategoria())
                .NombreCategoria(registro.getNombreCategoria())
                .build()).collect(Collectors.toList());
    }
}
