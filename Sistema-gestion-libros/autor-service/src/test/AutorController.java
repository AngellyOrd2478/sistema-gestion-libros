package test;

import com.tulibro.autorservice.model.Autor;
import com.tulibro.autorservice.repository.AutorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.when;

@WebFluxTest(controllers = AutorController.class)
class AutorControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private AutorRepository autorRepository;

    private Autor autor;

    @BeforeEach
    void setUp() {
        autor = new Autor("1", "Robert C. Martin");
    }

    @Test
    void testCrearAutor() {
        when(autorRepository.save(autor)).thenReturn(Mono.just(autor));

        webTestClient.post()
            .uri("/autores")
            .bodyValue(autor)
            .exchange()
            .expectStatus().isOk()
            .expectBody()
            .jsonPath("$.nombre").isEqualTo("Robert C. Martin");
    }

    @Test
    void testListarAutores() {
        when(autorRepository.findAll()).thenReturn(Flux.just(autor));

        webTestClient.get()
            .uri("/autores")
            .exchange()
            .expectStatus().isOk()
            .expectBody()
            .jsonPath("$[0].nombre").isEqualTo("Robert C. Martin");
    }

    @Test
    void testBuscarAutorPorId() {
        when(autorRepository.findById("1")).thenReturn(Mono.just(autor));

        webTestClient.get()
            .uri("/autores/1")
            .exchange()
            .expectStatus().isOk()
            .expectBody()
            .jsonPath("$.nombre").isEqualTo("Robert C. Martin");
    }
}
