package com.prostate.assessmen.service.impl;

import com.prostate.assessmen.entity.PatientNihCpsiScore;
import com.prostate.assessmen.mapper.PatientNihCpsiScoreMapper;
import com.prostate.assessmen.service.PatientNihCpsiScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientNihCpsiScoreServiceImpl implements PatientNihCpsiScoreService {

    @Autowired
    private PatientNihCpsiScoreMapper patientNihNpsiScoreMapper;

    @Override
    public int insertSelective(PatientNihCpsiScore patientNihNpsiScore) {
        return patientNihNpsiScoreMapper.insertSelective(patientNihNpsiScore);
    }

    @Override
    public int updateSelective(PatientNihCpsiScore patientNihNpsiScore) {
        return patientNihNpsiScoreMapper.updateSelective(patientNihNpsiScore);
    }

    @Override
    public PatientNihCpsiScore selectById(String id) {
        return patientNihNpsiScoreMapper.selectById(id);
    }

    @Override
    public List<PatientNihCpsiScore> selectByParams(PatientNihCpsiScore patientNihNpsiScore) {
        return null;
    }

    @Override
    public int deleteById(String id) {
        return 0;
    }

    @Override
    public int insertSelectiveById(PatientNihCpsiScore patientNihNpsiScore) {
        return patientNihNpsiScoreMapper.insertSelectiveById(patientNihNpsiScore);
    }
}
