package javaflix.controller.response;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record FilmeResponse (Long id,
                            String titulo,
                            String description,
                            LocalDate releaseDate,
                            double rating,
                            List<CategoriaResponse> categorias,
                            List<StreamingResponse> streamings
){
}
