package com.utp.piuratours.Controller;

import com.utp.piuratours.Dto.Categorias.CategoriasDto;
import com.utp.piuratours.Models.Categoria;
import com.utp.piuratours.Services.CategoriaService;
import com.utp.piuratours.Services.ImagenesService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/Categorias")
@AllArgsConstructor
public class CategoriasController {

    private final CategoriaService categoriaService;

    @GetMapping("/listarCategorias")
    public ResponseEntity<List<CategoriasDto>> listarCategorias() {
        return ResponseEntity.ok(categoriaService.listarCategorias());
    }

    @PostMapping( "/agregarCategoria")
    public ResponseEntity<CategoriasDto> agregarCategoria(@RequestParam String descripcion, @RequestParam MultipartFile imagen) throws IOException {
        return ResponseEntity.ok(categoriaService.agregarCategoria(descripcion,imagen));
    }

    @PostMapping("/eliminarCategoria")
    public ResponseEntity<String> eliminarCategoria(@RequestParam Integer idCategoria){
        return ResponseEntity.ok(categoriaService.eliminarCategoria(idCategoria));
    }
}
