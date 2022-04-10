package com.rocktech.hospital.repository;

import com.rocktech.hospital.model.Patient;
import com.rocktech.hospital.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;


@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Modifying
    @Transactional
    @Query(value = "delete from Patient where last_visit_date BETWEEN ?1 and ?2", nativeQuery = true)
    void deletePatientByDateRange(Date startDate, Date endDate);

    @Query("select p from Patient p where p.age > 1")
    List<Patient> getPatientByAge();

}
