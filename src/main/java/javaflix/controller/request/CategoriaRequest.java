package javaflix.controller.request;

import jakarta.validation.constraints.NotEmpty;

public record CategoriaRequest(@NotEmpty(message = "Nome da Categoria é obrigatório") String nome){
}
