package javaflix.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "categoria")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(length = 100, nullable = false)
    private String nome;


}
