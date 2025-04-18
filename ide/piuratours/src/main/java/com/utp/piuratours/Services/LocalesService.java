package com.utp.piuratours.Services;


import com.utp.piuratours.Dto.Locales.LocalesDto;
import com.utp.piuratours.Models.Locales;

import java.util.List;

public interface LocalesService {

    Locales grabarLocal (Locales local);

    Locales obtenerLocalPorId(Integer idLocal);

    List<LocalesDto> listarLocales();

    String eliminarLocales(Integer idLocales);

}
