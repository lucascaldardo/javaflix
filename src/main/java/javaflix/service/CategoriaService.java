package javaflix.service;

import jakarta.persistence.Id;
import jakarta.validation.OverridesAttribute;
import javaflix.entity.Categoria;
import javaflix.repository.CategoriaRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository repository;

    public List<Categoria> TodasCategorias(){
        return repository.findAll();
    }

    public Categoria criarCategoria(Categoria categoria){
        return repository.save(categoria);
    }

    public Optional<Categoria> listarPorId(@PathVariable Long id){
        return repository.findById(id);
    }

    public void deletarPorId(@PathVariable Long id){
        repository.deleteById(id);
    }
}
