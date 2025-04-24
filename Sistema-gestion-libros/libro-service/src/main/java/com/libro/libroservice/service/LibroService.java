package com.libro.libroservice.service;

import com.libro.libroservice.client.AutorClient;
import com.libro.libroservice.model.Libro;
import com.libro.libroservice.repository.LibroRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class LibroService {

    private final LibroRepository repository;

    public Mono<Libro> crearLibro(Libro libro) {
        return repository.save(libro);
    }

    public Flux<Libro> listar() {
        return repository.findAll();
    }

    public Mono<Libro> buscarPorId(String id) {
        return repository.findById(id);
    }

    public Mono<Libro> asignarAutor(String id, String autorId) {
        return repository.findById(id)
                .flatMap(libro -> {
                    libro.setAutorId(autorId);
                    return repository.save(libro);
                });
    }

    public Mono<Libro> asignarAutor(String libroId, String autorId) {
        return repository.findById(libroId)
                .flatMap(libro -> {
                    libro.setAutorId(autorId);
                    return repository.save(libro);
                });
    }
    

    @Autowired
private AutorClient autorClient;

public Mono<Libro> asignarAutor(String libroId, String autorId) {
    return autorClient.validarAutorExiste(autorId)
        .flatMap(existe -> {
            if (!existe) {
                return Mono.error(new RuntimeException("Autor no encontrado"));
            }
            return repository.findById(libroId)
                    .flatMap(libro -> {
                        libro.setAutorId(autorId);
                        return repository.save(libro);
                    });
        });
}

}






