package com.rocktech.hospital.repository;

import com.rocktech.hospital.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {

    Optional<Staff> findStaffByUuid(String uuid);
}
