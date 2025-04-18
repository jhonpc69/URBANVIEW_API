package com.utp.piuratours.Controller;

import com.utp.piuratours.Dto.Publicaciones.PublicacionesDto;
import com.utp.piuratours.Dto.Solicitudes.SolicitudBandejaDto;
import com.utp.piuratours.Dto.Solicitudes.SolicitudRegistroDto;
import com.utp.piuratours.Services.SolicitudesService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/Solicitudes")
@AllArgsConstructor
public class SolicitudesController {

    private final SolicitudesService solicitudesService;

    @PostMapping("/GrabarSolicitud")
    public ResponseEntity<SolicitudRegistroDto> grabarSolicitud(@RequestParam Integer idUsuario,
                                                                @RequestParam String ruc,
                                                                @RequestParam String nombreLocal,
                                                                @RequestParam String telefono,
                                                                @RequestParam (required = false) String diasSemana,
                                                                @RequestParam String horaApertura,
                                                                @RequestParam String horaCierre,
                                                                @RequestParam Integer idCategoria,
                                                                @RequestParam("imagenes") List<MultipartFile> imagenes,
                                                                @RequestParam("fichaRuc") MultipartFile fichaRuc,
                                                                @RequestParam String descripcion,
                                                                @RequestParam String ubicacion,
                                                                @RequestParam String linkFacebook,
                                                                @RequestParam String linkInstagram,
                                                                @RequestParam String linkMaps
    ) throws IOException {
        SolicitudRegistroDto response = solicitudesService.grabarSolicitud(idUsuario,ruc, nombreLocal, telefono,diasSemana, horaApertura, horaCierre, idCategoria, imagenes,fichaRuc,descripcion,ubicacion,linkFacebook,linkInstagram,linkMaps);
        return ResponseEntity.ok(response);
}

    @PostMapping("/DecisionSolicitud")
    public ResponseEntity<PublicacionesDto> decisionSolicitud(@RequestParam Integer idSolicitud,
                                                              @RequestParam String decision,
                                                              @RequestParam  (required = false) String comentario) {
        PublicacionesDto response = solicitudesService.decisionSolicitud(idSolicitud, decision,comentario);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/ListarSolicitudes")
    public  ResponseEntity<List<SolicitudBandejaDto>> listarSolicitudes(){

        List<SolicitudBandejaDto> response = solicitudesService.listarSolicitudes();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/ListarSolicitudesPorIdUsuario")
    public  ResponseEntity<List<SolicitudBandejaDto>> listarSolicitudesPorIdUsuario(@RequestParam Integer idUsuario){

        List<SolicitudBandejaDto> response = solicitudesService.listarSolicitudesPorIdUsuario(idUsuario);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/descargarAdjunto")
    public  ResponseEntity<byte[]> descargarAdjunto(@RequestParam String ruta){

        byte[] response = solicitudesService.descargarAdjunto(ruta);
        return ResponseEntity.ok(response);
    }



}
