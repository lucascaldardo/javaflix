package javaflix.controller;

import javaflix.controller.request.FilmeRequest;
import javaflix.controller.response.FilmeResponse;
import javaflix.entity.Filme;
import javaflix.mapper.FilmeMapper;
import javaflix.service.FilmeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/javaflix/filme")
@RequiredArgsConstructor
public class FilmeController {

    private final FilmeService filmeService;

    @PostMapping
    public ResponseEntity<FilmeResponse> salvar(@RequestBody FilmeRequest request){
        Filme filmeSalvo = filmeService.salvar(FilmeMapper.toFilme(request));
        return ResponseEntity.ok(FilmeMapper.toFilmeResponse(filmeSalvo));
    }

    @GetMapping
    public ResponseEntity<List<FilmeResponse>> listar(){
        return ResponseEntity.ok(filmeService.listar()
                .stream()
                .map(FilmeMapper::toFilmeResponse)
                .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilmeResponse> listarPorId(@PathVariable Long id){
       return filmeService.listarPorId(id)
               .map(filme -> ResponseEntity.ok(FilmeMapper.toFilmeResponse(filme)))
               .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<FilmeResponse> atualizar(@RequestBody FilmeRequest request, @PathVariable Long id){
        return filmeService.atualizar(id, FilmeMapper.toFilme(request))
                .map(filme -> ResponseEntity.ok(FilmeMapper.toFilmeResponse(filme)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<FilmeResponse>> listarPorCategoria(@RequestParam Long categoria){
       return ResponseEntity.ok(filmeService.listarPorCategoria(categoria)
               .stream()
               .map(FilmeMapper::toFilmeResponse)
               .toList());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id){
        Optional<Filme> optFilme = filmeService.listarPorId(id);
        if (optFilme.isPresent()){
            filmeService.deletarPorId(id);
            return ResponseEntity.noContent().build();
        }
       return ResponseEntity.notFound().build();

    }
}
