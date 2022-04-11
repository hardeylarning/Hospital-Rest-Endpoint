package com.rocktech.hospital.controller;

import com.rocktech.hospital.model.Patient;
import com.rocktech.hospital.service.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.servlet.http.HttpServletResponse;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@TestPropertySource(locations="classpath:application-test.properties")
class PatientControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private HttpServletResponse servletResponse;

    @MockBean
    private PatientService patientService;

    private Patient patient;

    @BeforeEach
    void setUp() {
        patient = new Patient();
        patient.setId(1L);
        patient.setAge(20);
        patient.setName("Test Patient");
    }

    @Test
    @DisplayName("Check for status code if it is ok")
    void downLoadPatientTest1() throws Exception {
        Mockito.when(patientService.writePatientToCsv(servletResponse.getWriter(), 1L))
                .thenReturn(patient);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/patient/1/download")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("check for content type value if its text/csv")
    void downLoadPatientTest2() throws Exception {
        Mockito.when(patientService.writePatientToCsv(servletResponse.getWriter(), 1L))
                .thenReturn(patient);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/patient/1/download")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().contentType("text/csv"));
    }
}