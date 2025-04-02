package com.example.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Members {

    @Id
    private Long id;
    private String name;
    private String title;

}