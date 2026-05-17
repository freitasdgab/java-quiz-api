package com.javaquiz.model;

import com.javaquiz.model.enums.Nivel;
import com.javaquiz.model.enums.TipoPergunta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 500)
    private String enunciado;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoPergunta tipo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Nivel nivel;

    @Column(nullable = false)
    private String categoria;

    @OneToMany(mappedBy = "pergunta", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Opcao> opcoes;
}
