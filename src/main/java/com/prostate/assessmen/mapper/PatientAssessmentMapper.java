package com.prostate.assessmen.mapper;

import com.prostate.assessmen.entity.PatientAssessment;

import java.util.List;

public interface PatientAssessmentMapper {
    PatientAssessment selectById(PatientAssessment patientAssessment);

    List<PatientAssessment> selectByPatientId(PatientAssessment patientAssessment);
}
