package com.prostate.assessmen.service.impl;

import com.prostate.assessmen.entity.PatientIpssScore;
import com.prostate.assessmen.mapper.PatientIpssScoreMapper;
import com.prostate.assessmen.mapper.PatientNihNpsiScoreMapper;
import com.prostate.assessmen.service.PatientIpssScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PatientIpssScoreServiceImpl implements PatientIpssScoreService {
    @Autowired
    private PatientIpssScoreMapper patientIpssScoreMapper;


    @Override
    public int insertSelective(PatientIpssScore patientIpssScore) {
        return patientIpssScoreMapper.insertSelective(patientIpssScore);
    }

    @Override
    public int updateSelective(PatientIpssScore patientIpssScore) {
        return patientIpssScoreMapper.updateSelective(patientIpssScore);
    }

    @Override
    public PatientIpssScore selectById(String id) {
        return patientIpssScoreMapper.selectById(id);
    }

    @Override
    public List<PatientIpssScore> selectByParams(PatientIpssScore patientIpssScore) {
        return null;
    }

    @Override
    public int deleteById(String id) {
        return 0;
    }

    @Override
    public int insertSelectiveById(PatientIpssScore patientIpssScore) {
        return patientIpssScoreMapper.insertSelectiveById(patientIpssScore);
    }
}
