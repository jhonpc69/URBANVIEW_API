package com.utp.piuratours.Models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "Publicaciones")
public class Publicaciones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdPublicacion")
    private Integer IdPublicacion;

    @OneToOne
    @JoinColumn(name = "IdLocal")
    private Locales idLocal;

    @Column(name = "titulo")
    private String Titulo;

    @Column(name = "estado")
    private String estado;


    @Column(name = "FechaPublicacion")
    private String fechaPublicacion;

    @OneToMany(mappedBy = "idPublicacion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comentarios> comentarios;

}
