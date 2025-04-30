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
@Table(name = "Usuario")

public class Usuarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdUsuario")
    private Integer idUsuario;

    @Column(name = "NombreUsuario")
    private String nombreUsuario;

    @Column(name = "ApellidosUsuario")
    private String apellidosUsuario;

    @Column(name = "dniusuario")
    private String dniUsuario;

    @Column(name = "Telefono")
    private String telefono;

    @Column(name = "ClaveUsuario")
    private String claveusuario;

    @Column(name = "correo")
    private String correo;

    @ManyToOne
    @JoinColumn(name = "idTipoUsuario")
    private TipoUsuario idTipoUsuario;

    @OneToMany(mappedBy = "idUsuario")
    private List<Solicitudes> solicitudesList;

    @OneToMany(mappedBy = "idComentario")
        private List<Comentarios> comentariosList;

    @OneToMany(mappedBy = "user")
    private List<Token> tokens;

    @OneToOne(mappedBy = "usuario")  // Esto mapea la relación inversa en VerificationCode
    private VerificationCode verificationCode;
}
