package com.utp.piuratours.Services;


import com.utp.piuratours.Dto.Usuarios.UsuarioRespuestaRegistroDto;
import com.utp.piuratours.Dto.Usuarios.UsuarioRespuestaLogin;
import com.utp.piuratours.Models.Usuarios;

import java.util.List;

public interface UsuariosService {
    UsuarioRespuestaRegistroDto registrarUsuarios(String nombre, String apellido, String dni, String telefono, String claveusuario, String correo, String idTipoUsuario);

    UsuarioRespuestaLogin login(String dni, String claveusuario);

    List<UsuarioRespuestaRegistroDto> listarUsuarios();

    Usuarios obtenerUsuarioPorId(Integer idUsuario);
    Usuarios obtenerUsuarioPorDNI(String dni);


}
