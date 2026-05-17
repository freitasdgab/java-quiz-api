package com.javaquiz.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

public class RespostaDTO {

    @Data
    public static class Request {
        @NotNull(message = "ID da opção escolhida é obrigatório")
        private Long opcaoId;
    }

    @Data
    public static class Response {
        private boolean correta;
        private String mensagem;
        private Long opcaoCorretaId;
        private String textoOpcaoCorreta;
    }
}
