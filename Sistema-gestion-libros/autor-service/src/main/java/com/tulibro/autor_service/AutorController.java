package main.java.com.tulibro.autor_service;

import com.tulibro.autorservice.model.Autor;
import com.tulibro.autorservice.service.AutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/autores")
@RequiredArgsConstructor
public class AutorController {

    private final AutorService service;

    @PostMapping
    public Mono<Autor> crear(@RequestBody Autor autor) {
        return service.crearAutor(autor);
    }

    @GetMapping
    public Flux<Autor> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Mono<Autor> buscar(@PathVariable String id) {
        return service.buscarPorId(id);
    }
}
