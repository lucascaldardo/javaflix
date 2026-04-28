package javaflix.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record FilmeResponse (Long id,
                            String titulo,
                            String description,

                             @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
                            LocalDate releaseDate,

                            double rating,
                            List<CategoriaResponse> categorias,
                            List<StreamingResponse> streamings
){
}
