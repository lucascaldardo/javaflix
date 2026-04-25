package javaflix.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
public record CategoriaResponse (Long id, String nome) {
}
