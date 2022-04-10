package com.rocktech.hospital.controller;

import com.rocktech.hospital.model.Staff;
import com.rocktech.hospital.payload.ResponseMessage;
import com.rocktech.hospital.payload.StaffResponse;
import com.rocktech.hospital.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("api/staff/")
public class StaffController {

    @Autowired
    private StaffService staffService;

    @PostMapping("add-staff")
    public ResponseEntity<StaffResponse> addStaff(@RequestBody Staff staff) throws ParseException {
        staffService.addStaff(staff);
        StaffResponse staffResponse = new StaffResponse(
                staff.getName() +" has been added successfully, kindly write down your uuid.",
                        staff.getUuid());
        return ResponseEntity.ok(staffResponse);
    }

    @PutMapping("{staffId}/update-staff")
    public ResponseEntity<ResponseMessage> updateStaff(@RequestBody Staff staff, @PathVariable("staffId") Long staffId){
          staffService.updateStaff(staff, staffId);
          return ResponseEntity.ok(new ResponseMessage("Staff record is updated successfully!"));
    }
}
