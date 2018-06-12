package com.prostate.assessmen.service.impl;

import com.prostate.assessmen.entity.ProstaticMedicalExamination;
import com.prostate.assessmen.mapper.ProstaticMedicalExaminationMapper;
import com.prostate.assessmen.service.ProstaticMedicalExaminationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProstaticMedicalExaminationServiceImpl implements ProstaticMedicalExaminationService {

    @Autowired
    private ProstaticMedicalExaminationMapper prostaticMedicalExaminationMapper;

    @Override
    public int insertSelective(ProstaticMedicalExamination prostaticMedicalExamination) {
        return prostaticMedicalExaminationMapper.insertSelective(prostaticMedicalExamination);
    }

    @Override
    public int updateSelective(ProstaticMedicalExamination prostaticMedicalExamination) {
        return prostaticMedicalExaminationMapper.updateSelective(prostaticMedicalExamination);
    }

    @Override
    public ProstaticMedicalExamination selectById(String id) {
        return prostaticMedicalExaminationMapper.selectById(id);
    }

    @Override
    public List<ProstaticMedicalExamination> selectByParams(ProstaticMedicalExamination prostaticMedicalExamination) {
        return prostaticMedicalExaminationMapper.selectByParams(prostaticMedicalExamination);
    }

    @Override
    public int deleteById(String id) {
        return 0;
    }
}
