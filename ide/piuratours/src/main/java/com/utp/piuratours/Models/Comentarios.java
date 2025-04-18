package com.utp.piuratours.Models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "Comentarios")
public class Comentarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcomentario")
    private Integer idComentario;

    @ManyToOne
    @JoinColumn(name = "idpublicacion")
    private Publicaciones idPublicacion;

    @ManyToOne
    @JoinColumn(name = "idusuario")
    private Usuarios idUsuario;

    @Column(name = "contenido")
    private String Contenido;

    @Column(name = "fechacomentario")
    private String fechaComentario;

}
