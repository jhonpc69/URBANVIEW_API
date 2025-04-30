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
@Table(name = "Categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCategoria")
    private Integer idCategoria;

    @Column(name = "NombreCategoria")
    private String NombreCategoria;

    @OneToMany(mappedBy = "idCategoria")
    @ToString.Exclude
    private List<Locales> localesList;
}
