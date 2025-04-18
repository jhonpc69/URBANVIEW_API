package com.utp.piuratours.Services.Impl;

import com.utp.piuratours.Builder.UsuariosBuilder;
import com.utp.piuratours.Dto.Usuarios.UsuarioRespuestaRegistroDto;
import com.utp.piuratours.Dto.Usuarios.UsuarioRespuestaLogin;
import com.utp.piuratours.Models.TipoUsuario;
import com.utp.piuratours.Models.Usuarios;
import com.utp.piuratours.Repository.UsuariosRepository;
import com.utp.piuratours.Services.TipoUsuarioService;
import com.utp.piuratours.Services.UsuariosService;
import com.utp.piuratours.Util.Util;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@Service
@AllArgsConstructor
public class UsuariosServiceImpl  implements UsuariosService {

    private final UsuariosRepository usuariosRepository;
    private final TipoUsuarioService tipoUsuarioService;

    @Override
    public UsuarioRespuestaRegistroDto registrarUsuarios(String nombre, String apellido, String dni, String telefono, String claveusuario, String correo, String idTipoUsuario) {
        TipoUsuario tipoUsuario;
        if (idTipoUsuario == null){
            tipoUsuario = tipoUsuarioService.obtenerTipoUsuarioPorId(0);
        }else {
            tipoUsuario = tipoUsuarioService.obtenerTipoUsuarioPorId(Integer.valueOf(idTipoUsuario));
        }
        Usuarios usuario = new Usuarios();
        usuario.setNombreUsuario(nombre);
        usuario.setApellidosUsuario(apellido);
        usuario.setDniUsuario(dni);
        usuario.setTelefono(telefono);
        usuario.setClaveusuario(Util.encriptarContrasena(claveusuario));
        usuario.setIdTipoUsuario(tipoUsuario);
        usuario.setCorreo(correo);
        usuariosRepository.save(usuario);
        return UsuariosBuilder.BuildearUsuario(usuario);
    }

    @Override
    public UsuarioRespuestaLogin login(String dni, String claveusuario) {

        Optional<Usuarios> usuario = usuariosRepository.getUsuariosByDniUsuario(dni);
        UsuarioRespuestaLogin userResponse = new UsuarioRespuestaLogin();
        if(usuario.isPresent()){
            if (Util.desencriptarContrasena(claveusuario,usuario.get().getClaveusuario())) {
                return UsuarioRespuestaLogin.builder()
                        .idUsuario(usuario.get().getIdUsuario())
                        .dni(usuario.get().getDniUsuario())
                        .nombresCompletos(usuario.get().getNombreUsuario() +" " +usuario.get().getApellidosUsuario())
                        .tipoUsuario(usuario.get().getIdTipoUsuario().getIdTipoUsuario())
                        .islogueado(TRUE).build();
            }
        }else {
            return null;
        }
        userResponse.setIslogueado(FALSE);
        return userResponse;
    }

    @Override
    public List<UsuarioRespuestaRegistroDto> listarUsuarios() {
        return UsuariosBuilder.BuildearUsuarios(usuariosRepository.findAll());
    }

    @Override
    public Usuarios obtenerUsuarioPorId(Integer idUsuario) {
    return usuariosRepository.findById(idUsuario).get();
    }


    @Override
    public Usuarios obtenerUsuarioPorDNI(String dni) {
        return usuariosRepository.getUsuariosByDniUsuario(dni)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con DNI: " + dni));
    }

}
