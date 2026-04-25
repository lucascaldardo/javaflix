package javaflix.service;


import javaflix.entity.Streaming;
import javaflix.repository.StreamingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StreamingService {

    private final StreamingRepository repository;

    public List<Streaming> TodosStreaming(){
        return repository.findAll();
    }

    public Streaming criarStreaming(Streaming streaming){
        return repository.save(streaming);
    }

    public Optional<Streaming> listarPorId(@PathVariable Long id){
        return repository.findById(id);
    }

    public void deletarPorId(@PathVariable Long id){
        repository.deleteById(id);
    }

}
