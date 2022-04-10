package com.rocktech.hospital.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    @JsonIgnoreProperties(allowGetters = true)
    private String uuid;

    @JsonIgnoreProperties(allowGetters = true)
    @Column(name = "registration_date")
    private Date registrationDate;
}
