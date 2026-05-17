package com.javaquiz.repository;

import com.javaquiz.model.Pergunta;
import com.javaquiz.model.enums.Nivel;
import com.javaquiz.model.enums.TipoPergunta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerguntaRepository extends JpaRepository<Pergunta, Long> {

    List<Pergunta> findByNivel(Nivel nivel);

    List<Pergunta> findByTipo(TipoPergunta tipo);

    List<Pergunta> findByCategoria(String categoria);

    List<Pergunta> findByNivelAndCategoria(Nivel nivel, String categoria);

    @Query("SELECT DISTINCT p.categoria FROM Pergunta p")
    List<String> findAllCategorias();

    @Query("SELECT p FROM Pergunta p ORDER BY FUNCTION('RAND')")
    List<Pergunta> findAllAleatorio();
}
