package test;

import com.tulibro.autorservice.model.Autor;
import com.tulibro.autorservice.repository.AutorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class AutorServiceTest {

    @Mock
    private AutorRepository autorRepository;

    @InjectMocks
    private AutorService autorService;

    @Test
    void testGuardarAutor() {
        Autor autor = new Autor("1", "Robert C. Martin");

        Mockito.when(autorRepository.save(Mockito.any())).thenReturn(Mono.just(autor));

        StepVerifier.create(autorService.guardarAutor(autor))
            .expectNextMatches(a -> a.getNombre().equals("Robert C. Martin"))
            .verifyComplete();
    }

    @Test
    void testObtenerAutorPorId() {
        Autor autor = new Autor("1", "Robert C. Martin");

        Mockito.when(autorRepository.findById("1")).thenReturn(Mono.just(autor));

        StepVerifier.create(autorService.obtenerAutorPorId("1"))
            .expectNextMatches(a -> a.getNombre().equals("Robert C. Martin"))
            .verifyComplete();
    }
}
