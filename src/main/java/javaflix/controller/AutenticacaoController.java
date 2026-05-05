package javaflix.controller;

import javaflix.controller.request.UsuarioRequest;
import javaflix.controller.response.UsuarioResponse;
import javaflix.entity.Usuario;
import javaflix.mapper.UsuarioMapper;
import javaflix.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/javaflix/autenticacao")
@RequiredArgsConstructor
public class AutenticacaoController {

    private final UsuarioService usuarioService;

    @PostMapping("/registrar")
    public ResponseEntity<UsuarioResponse> registro(@RequestBody UsuarioRequest request){
        Usuario usuarioSalvo = usuarioService.salvar(UsuarioMapper.toUsuario(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioMapper.toUsuarioResponse(usuarioSalvo));
    }

}
