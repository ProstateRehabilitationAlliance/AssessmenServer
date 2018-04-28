package com.prostate.assessmen.mapper;

import com.prostate.assessmen.entity.PatientScaleScore;

public interface ScaleScoreMapper extends BaseMapper<PatientScaleScore>{

    int insertSelectiveById(PatientScaleScore scaleScore);
}