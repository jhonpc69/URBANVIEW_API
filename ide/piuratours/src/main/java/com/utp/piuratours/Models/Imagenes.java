package com.utp.piuratours.Models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Imagenes")
public class Imagenes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idImagenes;

    @ManyToOne
    @JoinColumn(name = "idSolicitud", nullable = false)
    private Solicitudes idSolicitud;

    @Column(name ="imagen" )
    private byte[] imagen;

    @Column(name = "descripcion")
    private String descripcion;
}
