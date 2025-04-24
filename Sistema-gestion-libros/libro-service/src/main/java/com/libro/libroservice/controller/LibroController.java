package com.libro.libroservice.controller;

import com.libro.libroservice.dto.AsignarAutorRequest;
import com.libro.libroservice.model.Libro;
import com.libro.libroservice.service.LibroService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/libros")
@RequiredArgsConstructor
public class LibroController {

    private final LibroService service;

    @PostMapping
    public Mono<Libro> crearLibro(@RequestBody Libro libro) {
        return service.crearLibro(libro);
    }

    @GetMapping
    public Flux<Libro> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Mono<Libro> buscar(@PathVariable String id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}/autor")
    public Mono<Libro> asignarAutor(@PathVariable String id, @RequestBody String autorId) {
        return service.asignarAutor(id, autorId);
    }

    @PutMapping("/{id}/autor")
    public Mono<Libro> asignarAutor(@PathVariable String id, @RequestBody AsignarAutorRequest request) {
        return service.asignarAutor(id, request.getAutorId());
    }

}
