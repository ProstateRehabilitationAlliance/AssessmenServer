package com.prostate.assessmen.mapper;

import com.prostate.assessmen.entity.PatientNihCpsiScore;

public interface PatientNihCpsiScoreMapper extends BaseMapper<PatientNihCpsiScore>{

    int insertSelectiveById(PatientNihCpsiScore patientNihNpsiScore);
}