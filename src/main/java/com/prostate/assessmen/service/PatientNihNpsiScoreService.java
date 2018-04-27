package com.prostate.assessmen.service;

import com.prostate.assessmen.entity.PatientNihNpsiScore;

public interface PatientNihNpsiScoreService extends BaseService<PatientNihNpsiScore> {

    int insertSelectiveById(PatientNihNpsiScore patientNihNpsiScore);
}
