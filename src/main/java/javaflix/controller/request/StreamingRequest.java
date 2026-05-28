package javaflix.controller.request;

import jakarta.validation.constraints.NotEmpty;

public record StreamingRequest(@NotEmpty(message = "Nome da Streaming é Obrigatório.") String nome) {
}
