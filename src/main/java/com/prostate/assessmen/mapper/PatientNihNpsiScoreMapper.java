package com.prostate.assessmen.mapper;

import com.prostate.assessmen.entity.PatientNihNpsiScore;

public interface PatientNihNpsiScoreMapper extends BaseMapper<PatientNihNpsiScore>{

    int insertSelectiveById(PatientNihNpsiScore patientNihNpsiScore);
}