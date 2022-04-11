package com.rocktech.hospital.service;

import com.rocktech.hospital.exception.StaffNotFound;
import com.rocktech.hospital.model.Staff;


public interface StaffService {

    String uuidValue(String uuid);

    Staff addStaff(Staff staff);

    void updateStaff(Staff staff, Long staffId) throws StaffNotFound;
}
