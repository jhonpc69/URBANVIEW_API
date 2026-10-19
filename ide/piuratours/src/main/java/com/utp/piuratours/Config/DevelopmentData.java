package com.utp.piuratours.Config;

import com.utp.piuratours.Models.Categoria;
import com.utp.piuratours.Models.TipoUsuario;
import com.utp.piuratours.Models.Usuarios;
import com.utp.piuratours.Repository.CategoriaRepository;
import com.utp.piuratours.Repository.TipoUsuarioRepository;
import com.utp.piuratours.Repository.UsuariosRepository;
import com.utp.piuratours.Util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Profile("dev")
@RequiredArgsConstructor
public class DevelopmentData implements ApplicationRunner {
    private final CategoriaRepository categorias;
    private final TipoUsuarioRepository tipos;
    private final UsuariosRepository usuarios;

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        for (int id = 0; id <= 1; id++) {
            if (!tipos.existsById(id)) {
                TipoUsuario tipo = new TipoUsuario();
                tipo.setIdTipoUsuario(id);
                tipo.setDescripcion(id == 0 ? "CLIENTE" : "ADMINISTRADOR");
                tipos.save(tipo);
            }
        }
        if (categorias.count() == 0) {
            for (String nombre : new String[]{"RESTAURANTES", "DISCOTECAS", "HOTELES", "CLUBS"}) {
                Categoria categoria = new Categoria();
                categoria.setNombreCategoria(nombre);
                categorias.save(categoria);
            }
        }
        if (usuarios.getUsuariosByCorreo("demo@urbanview.test").isEmpty()) {
            Usuarios usuario = new Usuarios();
            usuario.setNombreUsuario("Usuario");
            usuario.setApellidosUsuario("Demo");
            usuario.setCorreo("demo@urbanview.test");
            usuario.setDniUsuario("00000001");
            usuario.setTelefono("999000001");
            usuario.setClaveusuario(Util.encriptarContrasena("UrbanViewDemo2026!"));
            usuario.setIdTipoUsuario(tipos.findById(0).orElseThrow());
            usuarios.save(usuario);
        }
    }
}
