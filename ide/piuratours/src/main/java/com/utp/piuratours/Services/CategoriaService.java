package com.utp.piuratours.Services;
import java.util.List;


import com.utp.piuratours.Dto.Categorias.CategoriasDto;
import com.utp.piuratours.Models.Categoria;

public interface CategoriaService {
    Categoria obtenerLocalPorId (Integer id);

    List<CategoriasDto> listarCategorias();

    CategoriasDto agregarCategoria(String descripcion);

    String eliminarCategoria(Integer idCategoria);
}
