package com.utp.piuratours.Controller;

import com.utp.piuratours.Dto.Categorias.CategoriasDto;
import com.utp.piuratours.Dto.Locales.LocalesDto;
import com.utp.piuratours.Dto.Publicaciones.PublicacionesDto;
import com.utp.piuratours.Models.Locales;
import com.utp.piuratours.Models.Publicaciones;
import com.utp.piuratours.Repository.PublicacionesRepository;
import com.utp.piuratours.Services.CategoriaService;
import com.utp.piuratours.Services.LocalesService;
import com.utp.piuratours.Services.PublicacionesService;
import lombok.AllArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Locales")
@AllArgsConstructor
public class LocalesController {
    private final LocalesService localesService;
    private final PublicacionesService publicacionesService;
    private final PublicacionesRepository publicacionesRepository;
    @GetMapping("/listarLocales")
    public ResponseEntity<List<LocalesDto>> listarLocales() {
        return ResponseEntity.ok(localesService.listarLocales());
    }

    @PostMapping("/eliminarLocales")
    public ResponseEntity<String> eliminarLocales(@RequestParam Integer idLocales){
        Locales local = localesService.obtenerLocalPorId(idLocales);
        List<PublicacionesDto> list = publicacionesService.ObtenerTodasPublicaciones2(local.getIdCategoria().getIdCategoria());
        for (PublicacionesDto publicacionDto : list) {
            if (publicacionDto.getIdLocal().equals(idLocales)) {
                System.out.println("se encontro coincidencia ");
                Publicaciones publicacion = publicacionesRepository.findById(publicacionDto.getIdPublicacion()).orElse(null);
                System.out.println("se obtuvo objeto");
                if (publicacion != null) {
                    System.out.println("se verifico que no es nulo");
                    publicacion.setEstado("0");
                    publicacionesRepository.save(publicacion);
                }
            }
        }
        local.setEstado("0");
        localesService.grabarLocal(local);
        return ResponseEntity.ok("se elimino correctamente");
    }
}
