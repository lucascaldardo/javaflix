package javaflix.service;

import javaflix.entity.Usuario;
import javaflix.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public Usuario salvar(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

}
