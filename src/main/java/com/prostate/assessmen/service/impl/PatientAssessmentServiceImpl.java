package com.prostate.assessmen.service.impl;

import com.prostate.assessmen.entity.PatientAssessment;
import com.prostate.assessmen.mapper.PatientAssessmentMapper;
import com.prostate.assessmen.service.PatientAssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PatientAssessmentServiceImpl implements PatientAssessmentService {

    @Autowired
    private PatientAssessmentMapper assessmentMapper;


    @Override
    public PatientAssessment selectById(PatientAssessment patientAssessment) {
        return assessmentMapper.selectById(patientAssessment);
    }

    @Override
    public List<PatientAssessment> selectByPatientId(PatientAssessment patientAssessment) {
        return assessmentMapper.selectByPatientId(patientAssessment);
    }
}
