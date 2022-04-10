package com.rocktech.hospital.service;

import com.rocktech.hospital.model.Staff;
import com.rocktech.hospital.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;

@Service
public class StaffServiceImpl implements StaffService{

    @Autowired
    private StaffRepository staffRepository;

    @Override
    public String uuidValue(String uuid) {
        Staff staff = staffRepository.findStaffByUuid(uuid);
        return staff.getUuid();
    }

    @Override
    public void addStaff(Staff staff) {
        String staffUuid = UUID.randomUUID().toString();
        staff.setUuid(staffUuid);
        staff.setRegistrationDate(new Date(System.currentTimeMillis()));
        staffRepository.save(staff);
    }

    @Override
    public void updateStaff(Staff staff, Long staffId) {
        Staff existingData = staffRepository.findById(staffId).get();
        if (staff.getName() != null && staff.getName().trim().length() > 1)
            existingData.setName(staff.getName());
        staffRepository.save(existingData);
    }
}
