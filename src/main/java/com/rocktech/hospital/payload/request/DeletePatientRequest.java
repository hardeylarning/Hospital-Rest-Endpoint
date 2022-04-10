package com.rocktech.hospital.payload.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@RequiredArgsConstructor
@Getter
@Setter
public class DeletePatientRequest {
    private Date startDate;
    private Date endDate;
}
