package com.utp.piuratours.Controller;

import com.utp.piuratours.Dto.Publicaciones.PublicacionesDto;
import com.utp.piuratours.Services.PublicacionesService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Publicaciones")
@AllArgsConstructor
public class PublicacionesController {

    private final PublicacionesService publicacionesService;

    @GetMapping("/listarPublicaciones")
    public ResponseEntity<List<PublicacionesDto>> listarPublicaciones(@RequestParam Integer idCategoria, @RequestParam(required = false) String titulo) {
        return ResponseEntity.ok(publicacionesService.ObtenerTodasPublicaciones(idCategoria, titulo));
    }
}
