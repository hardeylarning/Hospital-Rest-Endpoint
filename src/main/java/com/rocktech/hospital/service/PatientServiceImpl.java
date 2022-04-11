package com.rocktech.hospital.service;

import com.rocktech.hospital.exception.PatientNotFound;
import com.rocktech.hospital.model.Patient;
import com.rocktech.hospital.repository.PatientRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Writer;
import java.sql.Date;
import java.util.List;
import java.util.Optional;


@Service
public class PatientServiceImpl implements PatientService{

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public void deletePatients(Date startDate, Date endDate) {
        patientRepository.deletePatientByDateRange(startDate, endDate);
    }

    @Override
    public List<Patient> getPatientsByAge() {
        return patientRepository.getPatientByAge();
    }

    @Override
    public Optional<Patient> getPatientById(Long id) {
        return patientRepository.findById(id);
    }

    @Override
    public Patient writePatientToCsv(Writer writer, Long id) throws IOException, PatientNotFound {
        Optional<Patient> confirmPatient = patientRepository.findById(id);
        if (!confirmPatient.isPresent()){
            throw new PatientNotFound("No Profile Found");
        }
        Patient patient = confirmPatient.get();
        CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);
        csvPrinter.printRecord(patient.getId(), patient.getName(), patient.getAge(),
                String.valueOf(patient.getLastVisitDate()));
        return patient;
    }
}
