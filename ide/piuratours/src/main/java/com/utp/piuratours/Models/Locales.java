package com.utp.piuratours.Models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.bind.annotation.Mapping;

import java.util.List;

@ToString(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "Locales")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class Locales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdLocal")
    @ToString.Include
    private Integer idLocal;

    @Column(name = "NombreLocal")
    @ToString.Include
    private String nombreLocal;

    @Column(name = "telefono")
    @ToString.Include
    private String telefono;

    @Column(name = "valoracion")
    @ToString.Include
    private String valoracion;

    @Column(name = "horaApertura")
    @ToString.Include
    private String horaApertura;

    @Column(name = "descripcion")
    @ToString.Include
    private String descripcion;

    @Column(name = "horaCierre")
    @ToString.Include
    private String horaCierre;

    @Column(name = "estado")
    @ToString.Include
    private String estado;

    @Column(name = "Link_Instagram")
    @ToString.Include
    private String Link_Instagram;

    @Column(name = "Link_Facebook")
    @ToString.Include
    private String Link_Facebook;

    @Column(name = "Link_Maps")
    @ToString.Include
    private String Link_Maps;

    @Column(name = "ubicacion")
    @ToString.Include
    private String ubicacion;



    @Column(name = "ruc")
    @ToString.Include
    private String ruc;

    @Column(name = "rutapdf")
    @ToString.Include
    private String rutaPdf;

    @Column(name ="img_portada" )
    private byte[] imgPortada;

    @Column(name ="img_destacado" )
    private byte[] imgDestacado;


    @ManyToOne
    @JoinColumn(name = "idCategoria")
    @ToString.Exclude
    private Categoria idCategoria;

    @OneToMany(mappedBy = "idLocal")
    @ToString.Exclude
    private List<Solicitudes> solicitudesList;

    @OneToOne(mappedBy = "idLocal")
    @ToString.Exclude
    private Publicaciones publicacionesList;
}

