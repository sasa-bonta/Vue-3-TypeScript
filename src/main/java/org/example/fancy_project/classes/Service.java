package org.example.fancy_project.classes;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "service")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String location;

    public Service(Integer id, String location) {
        this.id = id;
        this.location = location;
    }

    public Service() {

    }
}
