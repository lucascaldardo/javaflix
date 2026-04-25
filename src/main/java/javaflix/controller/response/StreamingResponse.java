package javaflix.controller.response;

import lombok.Builder;

@Builder
public record StreamingResponse(Long id, String nome) {
}
