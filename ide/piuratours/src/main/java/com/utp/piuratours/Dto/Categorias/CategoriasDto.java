package com.utp.piuratours.Dto.Categorias;

import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CategoriasDto {
    private Integer idCategoria;
    private String NombreCategoria;

}
