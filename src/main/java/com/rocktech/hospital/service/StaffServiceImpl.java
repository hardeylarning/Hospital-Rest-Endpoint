package com.rocktech.hospital.service;

import com.rocktech.hospital.exception.StaffNotFound;
import com.rocktech.hospital.model.Staff;
import com.rocktech.hospital.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class StaffServiceImpl implements StaffService{

    @Autowired
    private StaffRepository staffRepository;

    @Override
    public String uuidValue(String uuid)  {
        Optional<Staff> staff = staffRepository.findStaffByUuid(uuid);
        if (staff.isPresent() && uuid.equals(staff.get().getUuid())){
            return staff.get().getUuid();
        }
        return "Invalid uuid";

    }

    @Override
    public Staff addStaff(Staff staff) {
        String staffUuid = UUID.randomUUID().toString();
        staff.setUuid(staffUuid);
        staff.setRegistrationDate(new Date(System.currentTimeMillis()));
        return staffRepository.save(staff);
    }

    @Override
    public void updateStaff(Staff staff, Long staffId) throws StaffNotFound {
        Optional<Staff> existingData = staffRepository.findById(staffId);
        if (!existingData.isPresent()){
            throw new StaffNotFound("No record found");
        }
        if (staff.getName() != null && staff.getName().trim().length() > 1){
            existingData.get().setName(staff.getName());
            staffRepository.save(existingData.get());
        }
    }
}
