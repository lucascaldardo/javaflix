package javaflix.controller;

import javaflix.config.TokenService;
import javaflix.controller.request.UsuarioRequest;
import javaflix.controller.response.LoginResponse;
import javaflix.controller.response.UsuarioResponse;
import javaflix.entity.Usuario;
import javaflix.mapper.UsuarioMapper;
import javaflix.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/javaflix/autenticacao")
@RequiredArgsConstructor
public class AutenticacaoController {

    private final UsuarioService usuarioService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;


    @PostMapping("/registrar")
    public ResponseEntity<UsuarioResponse> registro(@RequestBody UsuarioRequest request){
        Usuario usuarioSalvo = usuarioService.salvar(UsuarioMapper.toUsuario(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioMapper.toUsuarioResponse(usuarioSalvo));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody UsuarioRequest request){
        UsernamePasswordAuthenticationToken usuarioSenha = new UsernamePasswordAuthenticationToken(request.email(), request.password());
        Authentication authenticate = authenticationManager.authenticate(usuarioSenha);

        Usuario usuario = (Usuario) authenticate.getPrincipal();

        String token = tokenService.generateToken(usuario);

        return ResponseEntity.ok(new LoginResponse(token));

    }

}
