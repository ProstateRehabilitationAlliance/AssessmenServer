package com.prostate.assessmen.service;

import com.prostate.assessmen.entity.PatientIpssScore;

public interface PatientIpssScoreService extends BaseService<PatientIpssScore> {
    int insertSelectiveById(PatientIpssScore patientIpssScore);
}
