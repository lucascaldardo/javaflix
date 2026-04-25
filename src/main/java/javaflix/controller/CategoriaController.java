package javaflix.controller;

import javaflix.controller.request.CategoriaRequest;
import javaflix.controller.response.CategoriaResponse;
import javaflix.entity.Categoria;
import javaflix.mapper.CategoriaMapper;
import javaflix.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/javaflix/categoria")
@RequiredArgsConstructor
public class CategoriaController {


    private final CategoriaService categoriaService;


    @GetMapping
    public ResponseEntity<List<CategoriaResponse>>TodasCategorias(){
        return ResponseEntity.ok(categoriaService.TodasCategorias().stream()
                .map(CategoriaMapper::toCategoriaResponse)
                .toList());
    }

    @PostMapping
    public ResponseEntity<CategoriaResponse> criarCategoria(@RequestBody CategoriaRequest request){
        Categoria newCategoria = CategoriaMapper.toCategoria(request);
        Categoria savedCategoria = categoriaService.criarCategoria(newCategoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(CategoriaMapper.toCategoriaResponse(savedCategoria));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponse> listarPorId(@PathVariable Long id){
        return categoriaService.listarPorId(id)
                .map(categoria -> ResponseEntity.ok(CategoriaMapper.toCategoriaResponse(categoria)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id) {
        categoriaService.deletarPorId(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
