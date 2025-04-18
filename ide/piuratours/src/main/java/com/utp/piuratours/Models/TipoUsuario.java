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
@Table(name = "TipoUsuario")
public class TipoUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTipoUsuario")
    private Integer idTipoUsuario;

    @Column(name = "Descripcion")
    private String Descripcion;

    @OneToMany(mappedBy = "idTipoUsuario")
    private List<Usuarios> usuariosList;
}
