package javaflix.mapper;

import javaflix.controller.request.UsuarioRequest;
import javaflix.controller.response.StreamingResponse;
import javaflix.controller.response.UsuarioResponse;
import javaflix.entity.Usuario;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UsuarioMapper {

    public static Usuario toUsuario(UsuarioRequest usuarioRequest){
        return Usuario.builder()
                .nome(usuarioRequest.nome())
                .email(usuarioRequest.email())
                .password(usuarioRequest.password())
                .build();
    }

    public static UsuarioResponse toUsuarioResponse(Usuario usuario){
        return UsuarioResponse.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .build();
    }

}
