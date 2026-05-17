package com.javaquiz.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

public class OpcaoDTO {

    @Data
    public static class Request {
        @NotBlank(message = "Texto da opção é obrigatório")
        private String texto;
        private boolean correta;
    }

    // Response completo (com campo "correta") - para admin
    @Data
    public static class Response {
        private Long id;
        private String texto;
        private boolean correta;
    }

    // Response para quiz (sem revelar a resposta correta)
    @Data
    public static class QuizOpcao {
        private Long id;
        private String texto;
    }
}
