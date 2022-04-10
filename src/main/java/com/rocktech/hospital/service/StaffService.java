package com.rocktech.hospital.service;

import com.rocktech.hospital.model.Staff;

import java.text.ParseException;


public interface StaffService {

    String uuidValue(String uuid);

    void addStaff(Staff staff) throws ParseException;

    void updateStaff(Staff staff, Long staffId);
}
