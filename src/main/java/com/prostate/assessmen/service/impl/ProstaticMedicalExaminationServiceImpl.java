package com.prostate.assessmen.service.impl;

import com.prostate.assessmen.entity.ProstaticMedicalExamination;
import com.prostate.assessmen.mapper.pra.write.ProstaticMedicalExaminationWriteMapper;
import com.prostate.assessmen.mapper.pra.read.ProstaticMedicalExaminationReadMapper;
import com.prostate.assessmen.service.ProstaticMedicalExaminationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProstaticMedicalExaminationServiceImpl implements ProstaticMedicalExaminationService {

    @Autowired
    private ProstaticMedicalExaminationWriteMapper prostaticMedicalExaminationWriteMapper;

    @Autowired
    private ProstaticMedicalExaminationReadMapper prostaticMedicalExaminationReadMapper;

    @Override
    public int insertSelective(ProstaticMedicalExamination prostaticMedicalExamination) {
        return prostaticMedicalExaminationWriteMapper.insertSelective(prostaticMedicalExamination);
    }

    @Override
    public int deleteById(String id) {
        return 0;
    }

    @Override
    public int updateSelective(ProstaticMedicalExamination prostaticMedicalExamination) {
        return prostaticMedicalExaminationWriteMapper.updateSelective(prostaticMedicalExamination);
    }

    @Override
    public ProstaticMedicalExamination selectById(String id) {
        return prostaticMedicalExaminationReadMapper.selectById(id);
    }

    @Override
    public List<ProstaticMedicalExamination> selectByParams(ProstaticMedicalExamination prostaticMedicalExamination) {
        return prostaticMedicalExaminationReadMapper.selectByParams(prostaticMedicalExamination);
    }

    @Override
    public ProstaticMedicalExamination selectByCreateTimeAndPatientId(ProstaticMedicalExamination prostaticMedicalExamination) {
        return prostaticMedicalExaminationReadMapper.selectByCreateTimeAndPatientId(prostaticMedicalExamination);
    }

    @Override
    public ProstaticMedicalExamination selectByPatientAndData(ProstaticMedicalExamination prostaticMedicalExamination) {
        return prostaticMedicalExaminationReadMapper.selectByPatientAndData(prostaticMedicalExamination);
    }
}
