package com.libro.libroservice.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class AutorClient {

    private final WebClient webClient;

    public AutorClient(@Value("${autor.service.url}") String baseUrl) {
        this.webClient = WebClient.builder().baseUrl(baseUrl).build();
    }

    public Mono<Boolean> validarAutorExiste(String autorId) {
        return webClient.get()
                .uri("/autores/{id}", autorId)
                .retrieve()
                .bodyToMono(String.class) // si el servicio devuelve 200, el autor existe
                .map(resp -> true)
                .onErrorResume(e -> Mono.just(false)); // si no existe, devuelve false
    }
}
