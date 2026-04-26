package javaflix.controller.request;

import java.time.LocalDate;
import java.util.List;

public record FilmeRequest (String titulo,
                            String description,
                            LocalDate releaseDate,
                            double rating,
                            List<Long> categorias,
                            List<Long> streamings
                            ){
}
