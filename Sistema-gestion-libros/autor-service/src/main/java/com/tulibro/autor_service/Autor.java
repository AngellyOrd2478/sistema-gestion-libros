package main.java.com.tulibro.autor_service;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Autor {
    @Id
    private String id;
    private String nombre;
    private String nacionalidad;
}
