package com.libro.libroservice.service;

import com.libro.libroservice.model.Libro;
import com.libro.libroservice.repository.LibroRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class LibroServiceTest {

    @Mock
    private LibroRepository libroRepository;

    @InjectMocks
    private LibroService libroService;

    @Test
    void testGuardarLibro() {
        Libro libro = new Libro("1", "Clean Code", "123");

        Mockito.when(libroRepository.save(Mockito.any())).thenReturn(Mono.just(libro));

        StepVerifier.create(libroService.guardarLibro(libro))
                .expectNextMatches(l -> l.getTitulo().equals("Clean Code"))
                .verifyComplete();
    }

    @Test
    void testObtenerLibroPorId() {
        Libro libro = new Libro("1", "Clean Code", "123");

        Mockito.when(libroRepository.findById("1")).thenReturn(Mono.just(libro));

        StepVerifier.create(libroService.obtenerLibroPorId("1"))
                .expectNextMatches(l -> l.getTitulo().equals("Clean Code"))
                .verifyComplete();
    }
}
