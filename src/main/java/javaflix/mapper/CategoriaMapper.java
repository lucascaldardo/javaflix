package javaflix.mapper;

import javaflix.controller.request.CategoriaRequest;
import javaflix.controller.response.CategoriaResponse;
import javaflix.entity.Categoria;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CategoriaMapper {

    public static Categoria toCategoria(CategoriaRequest categoriaRequest){
        return Categoria.builder()
                .nome(categoriaRequest.nome())
                .build();
    }

    public static CategoriaResponse toCategoriaResponse(Categoria categoria){
        return CategoriaResponse.builder()
                .id(categoria.getId())
                .nome(categoria.getNome())
                .build();
    }

}
