package com.prostate.assessmen.service;

import com.prostate.assessmen.entity.PatientNihCpsiScore;

public interface PatientNihCpsiScoreService extends BaseService<PatientNihCpsiScore> {

    int insertSelectiveById(PatientNihCpsiScore patientNihNpsiScore);
}
