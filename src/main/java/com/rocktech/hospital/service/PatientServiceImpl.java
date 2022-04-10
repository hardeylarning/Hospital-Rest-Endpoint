package com.rocktech.hospital.service;

import com.rocktech.hospital.model.Patient;
import com.rocktech.hospital.repository.PatientRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Writer;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

@Service
public class PatientServiceImpl implements PatientService{

    @Autowired
    private PatientRepository patientRepository;

//    @Override
//    public void addPatient(Patient patient) throws ParseException {
//        SimpleDateFormat formatter = new SimpleDateFormat(
//                "yyyy-MM-dd");
//        patient.setLastVisitDate(formatter.parse(formatter.format(new Date())));
//        patientRepository.save(patient);
//    }

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
    public void writePatientToCsv(Writer writer, Long id) throws IOException {
        Patient patient = patientRepository.findById(id).get();

            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);
            csvPrinter.printRecord(patient.getId(), patient.getName(), patient.getAge(),
                    String.valueOf(patient.getLastVisitDate()));
    }
}
