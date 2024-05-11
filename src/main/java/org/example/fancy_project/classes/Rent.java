package org.example.fancy_project.classes;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;


@Data
@Getter
@MappedSuperclass
public abstract class Rent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String startDate;
    private String finishDate;
    private Integer numberOfDays;
    private Integer pricePerDay;
    private String idnp;
    private String tel;
    private String email;

    public Rent(String startDate, String finishDate, Integer numberOfDays,
                Integer pricePerDay, String idnp, String tel, String email) {
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.numberOfDays = numberOfDays;
        this.pricePerDay = pricePerDay;
        this.idnp = idnp;
        this.tel = tel;
        this.email = email;
    }

    public Rent() {

    }
}
