package com.rocktech.hospital.controller;

import com.rocktech.hospital.exception.PatientNotFound;
import com.rocktech.hospital.model.Patient;
import com.rocktech.hospital.payload.request.DeletePatientRequest;
import com.rocktech.hospital.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/patient/")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("all")
    public List<Patient> patientList() throws PatientNotFound {
        return patientService.getPatientsByAge();
    }

    @DeleteMapping("delete-patients")
    public void deletePatients(@RequestBody DeletePatientRequest deletePatientRequest) throws PatientNotFound {
        patientService.deletePatients(deletePatientRequest.getStartDate(), deletePatientRequest.getEndDate());
    }

    @GetMapping( "{patientId}/download")
    public void writePatientToCsv(HttpServletResponse servletResponse, @PathVariable("patientId") Long patientId)
            throws IOException, PatientNotFound {
        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition","attachment; filename=\"patient-"+ patientId +".csv\"");
        patientService.writePatientToCsv(servletResponse.getWriter(), patientId);
    }
}
