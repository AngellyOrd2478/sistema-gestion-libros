package main.java.com.tulibro.autor_service;

import com.tulibro.autorservice.model.Autor;
import com.tulibro.autorservice.repository.AutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AutorService {

    private final AutorRepository repository;

    public Mono<Autor> crearAutor(Autor autor) {
        return repository.save(autor);
    }

    public Flux<Autor> listar() {
        return repository.findAll();
    }

    public Mono<Autor> buscarPorId(String id) {
        return repository.findById(id);
    }
}
