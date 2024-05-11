package org.example.fancy_project.classes;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "service_appointment")
public class ServiceAppointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String tel;
    private String vin;
    private String details;

    public ServiceAppointment(Integer id, String tel, String vin, String details) {
        this.id = id;
        this.tel = tel;
        this.vin = vin;
        this.details = details;
    }

    public ServiceAppointment() {

    }
}
