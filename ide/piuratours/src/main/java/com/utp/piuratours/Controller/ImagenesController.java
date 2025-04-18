package com.utp.piuratours.Controller;

import com.utp.piuratours.Services.ImagenesService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Imagenes")
@AllArgsConstructor
public class ImagenesController {

    private final ImagenesService imagenesService;

    @GetMapping(value = "/obtenerImagen")
    public ResponseEntity<byte[]> obtenerImagen(@RequestParam Integer id) {
        return ResponseEntity.ok(imagenesService.obtenerImagenPorId(id));
    }

    @GetMapping(value = ("/obtenerImagenesPorIdSolicitud"))
    public ResponseEntity<List<byte[]>> obtenerImagenesPorIdSolicitud(@RequestParam Integer idLocal){
        return ResponseEntity.ok(imagenesService.obtenerImagenesPorIdSolicitud(idLocal));
    }



}
