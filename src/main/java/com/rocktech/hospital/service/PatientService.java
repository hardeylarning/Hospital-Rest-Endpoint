package com.rocktech.hospital.service;

import com.rocktech.hospital.exception.PatientNotFound;
import com.rocktech.hospital.model.Patient;

import java.io.IOException;
import java.io.Writer;
import java.sql.Date;
import java.util.List;

public interface PatientService {


    void deletePatients(Date startDate, Date endDate) throws PatientNotFound;

    List<Patient> getPatientsByAge() throws PatientNotFound;

    Patient writePatientToCsv(Writer writer, Long id) throws IOException, PatientNotFound;

}
