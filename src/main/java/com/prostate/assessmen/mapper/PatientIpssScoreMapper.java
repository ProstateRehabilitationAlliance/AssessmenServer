package com.prostate.assessmen.mapper;

import com.prostate.assessmen.entity.PatientIpssScore;

public interface PatientIpssScoreMapper extends BaseMapper<PatientIpssScore>{

    int insertSelectiveById(PatientIpssScore patientIpssScore);
}