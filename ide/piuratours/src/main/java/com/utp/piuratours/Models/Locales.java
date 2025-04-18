package com.utp.piuratours.Models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.bind.annotation.Mapping;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "Locales")


public class Locales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdLocal")
    private Integer idLocal;

    @Column(name = "NombreLocal")
    private String nombreLocal;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "valoracion")
    private String valoracion;

    @Column(name = "horaApertura")
    private String horaApertura;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "horaCierre")
    private String horaCierre;

    @Column(name = "estado")
    private String estado;

    @Column(name = "Link_Instagram")
    private String Link_Instagram;

    @Column(name = "Link_Facebook")
    private String Link_Facebook;

    @Column(name = "Link_Maps")
    private String Link_Maps;

    @Column(name = "ubicacion")
    private String ubicacion;

    @Column(name = "DiasSemana")
    private String diasSemana;

    @Column(name = "ruc")
    private String ruc;

    @Column(name = "rutapdf")
    private String rutaPdf;

    @ManyToOne
    @JoinColumn(name = "idCategoria")
    private Categoria idCategoria;

    @OneToMany(mappedBy = "idLocal")
    private List<Solicitudes> solicitudesList;

    @OneToOne(mappedBy = "idLocal")
    private Publicaciones publicacionesList;
}

