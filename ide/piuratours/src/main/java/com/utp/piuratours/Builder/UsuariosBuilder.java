package com.utp.piuratours.Builder;

import com.utp.piuratours.Dto.Usuarios.UsuarioRespuestaRegistroDto;
import com.utp.piuratours.Models.Usuarios;

import java.util.List;
import java.util.stream.Collectors;

public class UsuariosBuilder {

    public static UsuarioRespuestaRegistroDto BuildearUsuario(Usuarios usuarios){
       return UsuarioRespuestaRegistroDto.builder().nombreUsuario(usuarios.getNombreUsuario())
                .apellidosUsuario(usuarios.getApellidosUsuario())
                .dniUsuario(usuarios.getDniUsuario())
                .telefono(usuarios.getTelefono())
                .correo(usuarios.getCorreo())
                .tipoUsuario(usuarios.getIdTipoUsuario().getIdTipoUsuario().toString()).build();
    }

    public static List<UsuarioRespuestaRegistroDto> BuildearUsuarios(List<Usuarios> list){
    return list.stream().map(usuario -> UsuarioRespuestaRegistroDto.builder()
            .nombreUsuario(usuario.getNombreUsuario())
            .apellidosUsuario(usuario.getApellidosUsuario())
            .dniUsuario(usuario.getDniUsuario())
            .telefono(usuario.getTelefono())
            .correo(usuario.getCorreo())
            .tipoUsuario(usuario.getIdTipoUsuario().getIdTipoUsuario().toString()).build()).collect(Collectors.toList());
    }
}