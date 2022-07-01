package com.rocktech.hospital.controller;

import com.rocktech.hospital.exception.StaffNotFound;
import com.rocktech.hospital.model.Staff;
import com.rocktech.hospital.payload.response.ResponseMessage;
import com.rocktech.hospital.payload.response.StaffCreationResponse;
import com.rocktech.hospital.service.StaffService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/staff/")
public class StaffController {

    final Logger LOGGER = LoggerFactory.getLogger(StaffController.class);

    @Autowired
    private StaffService staffService;

    @PostMapping("add-staff")
    public ResponseEntity<StaffCreationResponse> addStaff(@RequestBody @Valid Staff staff) {
        Staff createdStaff = staffService.addStaff(staff);
        StaffCreationResponse staffCreationResponse = new StaffCreationResponse(
                createdStaff.getName() +" has been added successfully, kindly write down your uuid.",
                        createdStaff.getUuid());
        LOGGER.info(createdStaff.getName() +" has been added successfully");
        return ResponseEntity.ok(staffCreationResponse);
    }

    @PutMapping("{staffId}/update-staff")
    public ResponseEntity<ResponseMessage> updateStaff(@RequestBody Staff staff, @PathVariable("staffId") Long staffId)
            throws StaffNotFound {
          staffService.updateStaff(staff, staffId);
        LOGGER.info("Staff record is updated successfully!");
          return ResponseEntity.ok(new ResponseMessage("Staff record is updated successfully!"));
    }
}
