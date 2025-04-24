package com.libro.libroservice.controller;

import com.libro.libroservice.model.Libro;
import com.libro.libroservice.repository.LibroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.when;

@WebFluxTest(controllers = LibroController.class)
class LibroControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private LibroRepository libroRepository;

    private Libro libro;

    @BeforeEach
    void setUp() {
        libro = new Libro("1", "Clean Code", "123");
    }

    @Test
    void testCrearLibro() {
        when(libroRepository.save(libro)).thenReturn(Mono.just(libro));

        webTestClient.post()
                .uri("/libros")
                .bodyValue(libro)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.titulo").isEqualTo("Clean Code");
    }

    @Test
    void testListarLibros() {
        when(libroRepository.findAll()).thenReturn(Flux.just(libro));

        webTestClient.get()
                .uri("/libros")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].titulo").isEqualTo("Clean Code");
    }

    @Test
    void testBuscarLibroPorId() {
        when(libroRepository.findById("1")).thenReturn(Mono.just(libro));

        webTestClient.get()
                .uri("/libros/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.titulo").isEqualTo("Clean Code");
    }
}
