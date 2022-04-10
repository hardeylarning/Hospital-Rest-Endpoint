package com.rocktech.hospital.service;

import com.rocktech.hospital.model.Patient;
import com.rocktech.hospital.model.Staff;

import java.io.IOException;
import java.io.Writer;
import java.sql.Date;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;


public interface PatientService {


    void deletePatients(Date startDate, Date endDate);

    List<Patient> getPatientsByAge();

    Optional<Patient> getPatientById(Long id);

    void writePatientToCsv(Writer writer, Long id) throws IOException;
}
