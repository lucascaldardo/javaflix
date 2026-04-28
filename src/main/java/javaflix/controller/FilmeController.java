package javaflix.controller;

import javaflix.controller.request.FilmeRequest;
import javaflix.controller.response.FilmeResponse;
import javaflix.entity.Filme;
import javaflix.mapper.FilmeMapper;
import javaflix.service.FilmeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
                .map(filme -> FilmeMapper.toFilmeResponse(filme))
                .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilmeResponse> listarPorId(@PathVariable Long id){
       return filmeService.listarPorId(id)
               .map(filme -> ResponseEntity.ok(FilmeMapper.toFilmeResponse(filme)))
               .orElse(ResponseEntity.notFound().build());

    }

}
