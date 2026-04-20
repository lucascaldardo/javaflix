package javaflix.controller;

import javaflix.entity.Categoria;
import javaflix.service.CategoriaService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/javaflix/categoria")
@RequiredArgsConstructor
public class CategoriaController {


    private final CategoriaService categoriaService;


    @GetMapping
    public List<Categoria> TodasCategorias(){
        return categoriaService.TodasCategorias();
    }

    @PostMapping
    public Categoria criarCategoria(@RequestBody Categoria categoria){
        return categoriaService.criarCategoria(categoria);
    }

    @GetMapping("/{id}")
    public Categoria listarPorId(@PathVariable Long id){
        Optional<Categoria> optCategoria = categoriaService.listarPorId(id);
        if (optCategoria.isPresent()){
            return optCategoria.get();
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deletarPorId(@PathVariable Long id) {
        categoriaService.deletarPorId(id);
    }
}
