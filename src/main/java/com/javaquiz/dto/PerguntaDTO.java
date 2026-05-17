package com.javaquiz.dto;

import com.javaquiz.model.enums.Nivel;
import com.javaquiz.model.enums.TipoPergunta;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

// DTO para criar pergunta
public class PerguntaDTO {

    @Data
    public static class Request {

        @NotBlank(message = "Enunciado é obrigatório")
        private String enunciado;

        @NotNull(message = "Tipo é obrigatório")
        private TipoPergunta tipo;

        @NotNull(message = "Nível é obrigatório")
        private Nivel nivel;

        @NotBlank(message = "Categoria é obrigatória")
        private String categoria;

        @Size(min = 2, message = "Deve ter pelo menos 2 opções")
        private List<OpcaoDTO.Request> opcoes;
    }

    @Data
    public static class Response {
        private Long id;
        private String enunciado;
        private TipoPergunta tipo;
        private Nivel nivel;
        private String categoria;
        private List<OpcaoDTO.Response> opcoes;
    }

    // Resposta sem indicar qual é a correta (para o quiz)
    @Data
    public static class QuizResponse {
        private Long id;
        private String enunciado;
        private TipoPergunta tipo;
        private Nivel nivel;
        private String categoria;
        private List<OpcaoDTO.QuizOpcao> opcoes;
    }
}
