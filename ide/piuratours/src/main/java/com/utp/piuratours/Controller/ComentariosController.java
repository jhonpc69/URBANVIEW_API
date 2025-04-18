package com.utp.piuratours.Controller;

import com.utp.piuratours.Dto.Categorias.CategoriasDto;
import com.utp.piuratours.Dto.Comentarios.ComentariosDto;
import com.utp.piuratours.Services.ComentariosService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Comentarios")
@AllArgsConstructor
public class ComentariosController {

    private final ComentariosService comentariosService;

    @PostMapping( "/agregarComentarios")
    public ResponseEntity<ComentariosDto> agregarComentarios(@RequestParam String comentario,
                                                             @RequestParam Integer idPublicacion,
                                                             @RequestParam Integer idUsuario
                                                             ) {
        return ResponseEntity.ok(comentariosService.agregarComentarios(comentario,idPublicacion,idUsuario));
    }


}
