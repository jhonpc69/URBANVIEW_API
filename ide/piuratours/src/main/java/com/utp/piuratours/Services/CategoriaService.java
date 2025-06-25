package com.utp.piuratours.Services;
import java.io.IOException;
import java.util.List;


import com.utp.piuratours.Dto.Categorias.CategoriasDto;
import com.utp.piuratours.Models.Categoria;
import org.springframework.web.multipart.MultipartFile;

public interface CategoriaService {
    Categoria obtenerLocalPorId (Integer id);

    List<CategoriasDto> listarCategorias();

    CategoriasDto agregarCategoria(String descripcion, MultipartFile imagen) throws IOException;

    String eliminarCategoria(Integer idCategoria);
}
