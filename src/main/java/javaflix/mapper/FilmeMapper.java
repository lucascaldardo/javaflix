package javaflix.mapper;

import javaflix.controller.request.FilmeRequest;
import javaflix.controller.response.CategoriaResponse;
import javaflix.controller.response.FilmeResponse;
import javaflix.controller.response.StreamingResponse;
import javaflix.entity.Categoria;
import javaflix.entity.Filme;
import javaflix.entity.Streaming;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class FilmeMapper {

    public static Filme toFilme(FilmeRequest request){

        List<Categoria> categorias = request.categorias().stream().map(categoriaId -> Categoria.builder().id(categoriaId).build())
                .toList();

        List<Streaming> streamings = request.streamings().stream().map(streamingId -> Streaming.builder().id(streamingId).build())
                .toList();

        return Filme.builder()
                .titulo(request.titulo())
                .description(request.description())
                .releaseDate(request.releaseDate())
                .rating(request.rating())
                .categorias(categorias)
                .streamings(streamings)
                .build();
    }



    public static FilmeResponse toFilmeResponse(Filme filme){

        List<CategoriaResponse> categorias = filme.getCategorias().stream().map(categoria -> CategoriaMapper.toCategoriaResponse(categoria))
                .toList();

        List<StreamingResponse> streamings = filme.getStreamings().stream().map(streaming -> StreamingMapper.toStreamingResponse(streaming))
                .toList();

        return FilmeResponse.builder()
                .id(filme.getId())
                .titulo(filme.getTitulo())
                .description(filme.getDescription())
                .releaseDate(filme.getReleaseDate())
                .rating(filme.getRating())
                .categorias(categorias)
                .streamings(streamings)
                .build();
    }

}
