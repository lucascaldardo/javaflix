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

    public List<Filme> listar(){
        return repository.findAll();
    }

    public Optional<Filme> listarPorId(Long id){
        return repository.findById(id);
    }

    public List<Filme> listarPorCategoria(Long categoriaId) {
        return repository.findByCategoriasIn(List.of(Categoria.builder().id(categoriaId).build()));
    }

    public Filme salvar(Filme filme){
        filme.setCategorias(this.listarCategorias(filme.getCategorias()));
        filme.setStreamings(this.listarStreaming(filme.getStreamings()));
        return repository.save(filme);
    }

    public Optional<Filme> atualizar(Long filmeId,Filme filmeAtualizado){
        Optional<Filme> optFilme = listarPorId(filmeId);

        if (optFilme.isPresent()){
            List<Categoria> categorias = this.listarCategorias(filmeAtualizado.getCategorias());
            List<Streaming> streaming = this.listarStreaming(filmeAtualizado.getStreamings());

            Filme filme = optFilme.get();
            filme.setTitulo(filmeAtualizado.getTitulo());
            filme.setDescription(filmeAtualizado.getDescription());
            filme.setRating(filmeAtualizado.getRating());
            filme.setReleaseDate(filmeAtualizado.getReleaseDate());

            filme.getCategorias().clear();
            filme.getCategorias().addAll(categorias);

            filme.getStreamings().clear();
            filme.getStreamings().addAll(streaming);

            repository.save(filme);

            return Optional.of(filme);
        }

        return Optional.empty();
    }

    public void deletarPorId(Long filmeId){
        repository.deleteById(filmeId);

    }
    private List<Streaming> listarStreaming(List<Streaming> streamings) {
        List<Streaming> streamingsAchados = new ArrayList<>();
        streamings.forEach(streaming -> streamingService.listarPorId(streaming.getId()).ifPresent(streamingsAchados::add));
        return streamingsAchados;
    }

    private List<Categoria> listarCategorias(List<Categoria> categorias) {
        List<Categoria> categoriasAchadas = new ArrayList<>();
        categorias.forEach(categoria -> categoriaService.listarPorId(categoria.getId()).ifPresent(categoriasAchadas::add));
        return categoriasAchadas;
    }
}


