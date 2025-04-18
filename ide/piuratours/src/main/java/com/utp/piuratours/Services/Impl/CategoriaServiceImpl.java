package com.utp.piuratours.Services.Impl;

import com.utp.piuratours.Builder.CategoriaBuilder;
import com.utp.piuratours.Dto.Categorias.CategoriasDto;
import com.utp.piuratours.Models.Categoria;
import com.utp.piuratours.Repository.CategoriaRepository;
import com.utp.piuratours.Services.CategoriaService;
import com.utp.piuratours.Services.ComentariosService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;
    @Override
    public Categoria obtenerLocalPorId(Integer id) {
        return categoriaRepository.findById(id).get();
    }

    @Override
    public List<CategoriasDto> listarCategorias() {
        List<Categoria> list = categoriaRepository.findAll();
        return CategoriaBuilder.BuildearCategoria(list);
    }

    @Override
    public CategoriasDto agregarCategoria(String descripcion) {
        Categoria categoria = new Categoria();
        categoria.setNombreCategoria(descripcion);
        categoriaRepository.save(categoria);
        return CategoriasDto.builder()
                .idCategoria(categoria.getIdCategoria())
                .NombreCategoria(categoria.getNombreCategoria())
                .build();
    }

    @Override
    public String eliminarCategoria(Integer idCategoria) {
        categoriaRepository.deleteById(idCategoria);
        return "Se elimino Correctamente";
    }
}
