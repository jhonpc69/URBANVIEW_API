package com.utp.piuratours.Services.Impl;

import com.utp.piuratours.Builder.LocalesBuilder;
import com.utp.piuratours.Dto.Locales.LocalesDto;
import com.utp.piuratours.Models.Locales;
import com.utp.piuratours.Repository.LocalesRepository;
import com.utp.piuratours.Services.LocalesService;
import lombok.AllArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LocalesServiceImpl implements LocalesService {

    private final LocalesRepository localesRepository;

    @Override
    public Locales grabarLocal(Locales local) {
        return localesRepository.save(local);
    }

    @Override
    public Locales obtenerLocalPorId(Integer idLocal) {
        return localesRepository.findById(idLocal).get();
    }

    @Override
    public List<LocalesDto> listarLocales() {
        List<Locales> list = localesRepository.findAll();
        return LocalesBuilder.BuildearLocales(list);
    }

    @Override
    public String eliminarLocales(Integer idLocales) {
        localesRepository.deleteById(idLocales);
        return "Se elimino Correctamente";
    }
}
