package com.example.CrudApp.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name="Games")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private  String title;

    private String studio;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
