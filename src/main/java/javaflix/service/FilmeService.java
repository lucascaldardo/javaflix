package javaflix.service;

import javaflix.entity.Filme;
import javaflix.repository.FilmeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FilmeService {

    private final FilmeRepository repository;

    public Filme salvar(Filme filme){
        return repository.save(filme);
    }

    public List<Filme> listar(){
        return repository.findAll();
    }
}
