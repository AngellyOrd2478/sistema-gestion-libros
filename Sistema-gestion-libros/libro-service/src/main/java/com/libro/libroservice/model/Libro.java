package com.libro.libroservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Libro {
    @Id
    private String id;
    private String titulo;
    private String genero;
    private String autorId; // 👈 ¡Aquí!
}
