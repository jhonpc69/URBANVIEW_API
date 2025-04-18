package com.utp.piuratours.Services.Impl;

import com.utp.piuratours.Models.TipoUsuario;
import com.utp.piuratours.Repository.TipoUsuarioRepository;
import com.utp.piuratours.Services.TipoUsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class TipoUsuarioServiceImpl implements TipoUsuarioService {

    private final TipoUsuarioRepository tipoUsuarioRepository;

    @Override
    public TipoUsuario obtenerTipoUsuarioPorId(Integer id) {
        return tipoUsuarioRepository.findById(id).get();
    }
}
