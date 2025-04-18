package com.utp.piuratours.Services.Impl;

import com.utp.piuratours.Builder.ComentariosBuilder;
import com.utp.piuratours.Dto.Comentarios.ComentariosDto;
import com.utp.piuratours.Models.Comentarios;
import com.utp.piuratours.Models.Publicaciones;
import com.utp.piuratours.Models.Usuarios;
import com.utp.piuratours.Repository.ComentariosRepository;
import com.utp.piuratours.Repository.PublicacionesRepository;
import com.utp.piuratours.Services.ComentariosService;
import com.utp.piuratours.Services.ImagenesService;
import com.utp.piuratours.Services.PublicacionesService;
import com.utp.piuratours.Services.UsuariosService;
import com.utp.piuratours.Util.Util;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ComentariosServiceImpl implements ComentariosService {
    private final ComentariosRepository comentariosRepository;
    private final UsuariosService usuariosService;
    @Lazy
    private final PublicacionesRepository publicacionesRepository;
    @Override
    public List<ComentariosDto> findByIdPublicacion(Publicaciones idPublicacion) {
        List<Comentarios> list = comentariosRepository.findByIdPublicacion(idPublicacion);
        return ComentariosBuilder.BuildearComentarios(list);
    }

    @Override
    public ComentariosDto agregarComentarios(String comentario,Integer idPublicacion,Integer idUsuario) {
        Usuarios usuarioRegistro = usuariosService.obtenerUsuarioPorId(idUsuario);
        Comentarios reponse = new Comentarios();
        reponse.setFechaComentario(Util.obtenerFechaActualString());
        reponse.setIdUsuario(usuarioRegistro);
        reponse.setContenido(comentario);
        reponse.setIdPublicacion(publicacionesRepository.findById(idPublicacion).get());
        comentariosRepository.save(reponse);
        return ComentariosDto.builder()
                .idPublicacion(reponse.getIdPublicacion().getIdPublicacion())
                .Contenido(reponse.getContenido())
                .nombreUsuario(usuarioRegistro.getNombreUsuario() +" "+ usuarioRegistro.getApellidosUsuario())
                .build();
    }
}
