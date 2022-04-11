package com.rocktech.hospital.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    @NotBlank(message = "Staff name is required")
    private String name;


    @Column(nullable = false, unique = true)
    @JsonIgnoreProperties(allowGetters = true)
    private String uuid;

    @JsonIgnoreProperties(allowGetters = true)
    @Column(name = "registration_date")
    private Date registrationDate;
}
