package com.prostate.assessmen.service.impl;

import com.prostate.assessmen.entity.PatientScaleScore;
import com.prostate.assessmen.mapper.ScaleScoreMapper;
import com.prostate.assessmen.service.ScaleScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScaleScoreServiceImpl implements ScaleScoreService {

    @Autowired
    private ScaleScoreMapper scaleScoreMapper;

    @Override
    public int insertSelective(PatientScaleScore scaleScore) {
        return scaleScoreMapper.insertSelective(scaleScore);
    }

    @Override
    public int updateSelective(PatientScaleScore scaleScore) {
        return scaleScoreMapper.updateSelective(scaleScore);
    }

    @Override
    public PatientScaleScore selectById(String id) {
        return scaleScoreMapper.selectById(id);
    }

    @Override
    public List<PatientScaleScore> selectByParams(PatientScaleScore scaleScore) {
        return scaleScoreMapper.selectByParams(scaleScore);
    }

    @Override
    public int deleteById(String id) {
        return 0;
    }

    @Override
    public int insertSelectiveById(PatientScaleScore scaleScore) {
        return scaleScoreMapper.insertSelectiveById(scaleScore);
    }
}
