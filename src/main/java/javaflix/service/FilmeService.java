package javaflix.service;

import javaflix.entity.Categoria;
import javaflix.entity.Filme;
import javaflix.entity.Streaming;
import javaflix.repository.FilmeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FilmeService {

    private final FilmeRepository repository;
    private final CategoriaService categoriaService;
    private final StreamingService streamingService;

    public Filme salvar(Filme filme){
        filme.setCategorias(this.acharCategorias(filme.getCategorias()));
        filme.setStreamings(this.acharStreaming(filme.getStreamings()));
        return repository.save(filme);
    }

    public List<Filme> listar(){
        return repository.findAll();
    }

    public Optional<Filme> listarPorId(Long id){
        return repository.findById(id);
    }

    private List<Categoria> acharCategorias(List<Categoria> categorias) {
        List<Categoria> categoriasAchadas = new ArrayList<>();
        categorias.forEach(categoria -> categoriaService.listarPorId(categoria.getId()).ifPresent(categoriasAchadas::add));
            return categoriasAchadas;
    }

    private List<Streaming> acharStreaming(List<Streaming> streamings) {
        List<Streaming> streamingsAchados = new ArrayList<>();
        streamings.forEach(streaming -> streamingService.listarPorId(streaming.getId()).ifPresent(streamingsAchados::add));
        return streamingsAchados;
    }
}


