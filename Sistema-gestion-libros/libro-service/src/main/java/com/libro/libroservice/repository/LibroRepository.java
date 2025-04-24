package com.libro.libroservice.repository;

import com.libro.libroservice.model.Libro;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface LibroRepository extends ReactiveMongoRepository<Libro, String> {
}
