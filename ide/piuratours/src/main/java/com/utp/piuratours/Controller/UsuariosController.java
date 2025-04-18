package com.utp.piuratours.Controller;


import com.utp.piuratours.Dto.Usuarios.UsuarioRespuestaRegistroDto;
import com.utp.piuratours.Services.UsuariosService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Usuarios")
@AllArgsConstructor
public class UsuariosController {

    private final UsuariosService usuariosService;

    @PostMapping("/registrarUsuarios")
    public ResponseEntity<UsuarioRespuestaRegistroDto> registrarUsuarios(@RequestParam String nombre,
                                                                         @RequestParam String apellido,
                                                                         @RequestParam String dni,
                                                                         @RequestParam String telefono,
                                                                         @RequestParam String claveusuario,
                                                                         @RequestParam String correo,
                                                                         @RequestParam (required = false) String idTipoUsuario


    ) {
        return ResponseEntity.ok(usuariosService.registrarUsuarios(nombre, apellido, dni, telefono, claveusuario,correo,idTipoUsuario));
    }

    @GetMapping("/listarUsuarios")
    public ResponseEntity<List<UsuarioRespuestaRegistroDto>> listarUsuarios() {
        return ResponseEntity.ok(usuariosService.listarUsuarios());
    }

}
