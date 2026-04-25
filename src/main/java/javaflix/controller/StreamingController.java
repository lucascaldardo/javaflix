package javaflix.controller;

import javaflix.controller.request.CategoriaRequest;
import javaflix.controller.request.StreamingRequest;
import javaflix.controller.response.CategoriaResponse;
import javaflix.controller.response.StreamingResponse;
import javaflix.entity.Categoria;
import javaflix.entity.Streaming;
import javaflix.mapper.CategoriaMapper;
import javaflix.mapper.StreamingMapper;
import javaflix.service.StreamingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("javaflix/streaming")
@RequiredArgsConstructor
public class StreamingController {

    private final StreamingService streamingService;

    @GetMapping
    public ResponseEntity<List<StreamingResponse>> listarStreaming(){
        return ResponseEntity.ok(streamingService.TodosStreaming().stream()
                .map(StreamingMapper::toStreamingResponse)
                .toList());
    }

    @PostMapping
    public ResponseEntity<StreamingResponse> criarStreaming(@RequestBody StreamingRequest request){
        Streaming newStreaming = StreamingMapper.toStreaming(request);
        Streaming savedStreaming = streamingService.criarStreaming(newStreaming);
        return ResponseEntity.status(HttpStatus.CREATED).body(StreamingMapper.toStreamingResponse(savedStreaming));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StreamingResponse> listarPorId(@PathVariable Long id){
        return streamingService.listarPorId(id)
                .map(streaming -> ResponseEntity.ok(StreamingMapper.toStreamingResponse(streaming)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id) {
        streamingService.deletarPorId(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
