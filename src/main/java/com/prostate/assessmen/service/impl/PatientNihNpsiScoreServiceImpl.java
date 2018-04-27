package com.prostate.assessmen.service.impl;

import com.prostate.assessmen.entity.PatientNihNpsiScore;
import com.prostate.assessmen.mapper.PatientNihNpsiScoreMapper;
import com.prostate.assessmen.service.PatientNihNpsiScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientNihNpsiScoreServiceImpl implements PatientNihNpsiScoreService {

    @Autowired
    private PatientNihNpsiScoreMapper patientNihNpsiScoreMapper;

    @Override
    public int insertSelective(PatientNihNpsiScore patientNihNpsiScore) {
        return patientNihNpsiScoreMapper.insertSelective(patientNihNpsiScore);
    }

    @Override
    public int updateSelective(PatientNihNpsiScore patientNihNpsiScore) {
        return patientNihNpsiScoreMapper.updateSelective(patientNihNpsiScore);
    }

    @Override
    public PatientNihNpsiScore selectById(String id) {
        return patientNihNpsiScoreMapper.selectById(id);
    }

    @Override
    public List<PatientNihNpsiScore> selectByParams(PatientNihNpsiScore patientNihNpsiScore) {
        return null;
    }

    @Override
    public int deleteById(String id) {
        return 0;
    }

    @Override
    public int insertSelectiveById(PatientNihNpsiScore patientNihNpsiScore) {
        return patientNihNpsiScoreMapper.insertSelectiveById(patientNihNpsiScore);
    }
}
