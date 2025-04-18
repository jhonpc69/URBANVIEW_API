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
@Table(name = "Solicitudes")
public class Solicitudes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idsolicitud")
    private Integer idSolicitud;

    @ManyToOne
    @JoinColumn(name = "IdLocal")
    private Locales idLocal;

    @ManyToOne
    @JoinColumn(name = "IdUsuario")
    private Usuarios idUsuario;

    @Column(name = "estado")
    private String Estado;

    @Column(name = "fechaRegistro")
    private String fechaRegistro;

    @Column(name = "comentario_admin")
    private String comentarioAdmin;

    @OneToMany(mappedBy = "idSolicitud", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Imagenes> imagenes;


}
