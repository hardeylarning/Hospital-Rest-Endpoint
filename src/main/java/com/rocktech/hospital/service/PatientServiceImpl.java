package com.rocktech.hospital.service;

import com.rocktech.hospital.exception.PatientNotFound;
import com.rocktech.hospital.model.Patient;
import com.rocktech.hospital.payload.response.ResponseMessage;
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


    public void deletePatients(Date startDate, Date endDate) throws PatientNotFound {
        int result = patientRepository.countPatientByLastVisitDateBetween(startDate, endDate);
        if (result < 1){
            throw new PatientNotFound("No patients' record found between date range passed");
        }
        patientRepository.deletePatientByDateRange(startDate, endDate);
    }

    public ResponseMessage deletePatient(Date startDate, Date endDate) {
        int result = patientRepository.countPatientByLastVisitDateBetween(startDate, endDate);
        if (result < 1){
            return new ResponseMessage("No patients' record found between date range passed");
        }
        patientRepository.deletePatientByDateRange(startDate, endDate);
        return new ResponseMessage("Patient Successfully found");
    }

    @Override
    public List<Patient> getPatientsByAge() throws PatientNotFound {
        List<Patient> patients = patientRepository.getPatientByAge();
        if (patients.size() >= 1){
            return patients;
        }
        throw new  PatientNotFound("No Patients' record is found with the age 2 upward");
    }

    @Override
    public Patient writePatientToCsv(Writer writer, Long id) throws IOException, PatientNotFound {
        Optional<Patient> confirmPatient = patientRepository.findById(id);
        if (confirmPatient.isEmpty()){
            throw new PatientNotFound("No Profile Found");
        }
        Patient patient = confirmPatient.get();
        CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);
        csvPrinter.printRecord(patient.getId(), patient.getName(), patient.getAge(),
                String.valueOf(patient.getLastVisitDate()));
        return patient;
    }
}
