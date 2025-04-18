package com.utp.piuratours.Repository;

import com.utp.piuratours.Models.Categoria;
import com.utp.piuratours.Models.Locales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria,Integer> {

}
