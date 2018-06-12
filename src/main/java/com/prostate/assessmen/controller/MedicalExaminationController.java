package com.prostate.assessmen.controller;

import com.prostate.assessmen.beans.MedicalExaminationParam;
import com.prostate.assessmen.entity.ProstaticMedicalExamination;
import com.prostate.assessmen.service.ProstaticMedicalExaminationService;
import com.prostate.assessmen.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 医学检查检验 接口
 */
@RestController
@RequestMapping(value = "medicalExamination")
public class MedicalExaminationController extends BaseController {

    @Autowired
    private ProstaticMedicalExaminationService prostaticMedicalExaminationService;

    /**
     * 添加化验单解读结果
     *
     * @param medicalExaminationParam
     * @return
     */
    @RequestMapping(value = "add")
    public Map add(MedicalExaminationParam medicalExaminationParam) {
        int i = 0;

        ProstaticMedicalExamination prostaticMedicalExamination = new ProstaticMedicalExamination();

        List<Integer> bloodRoutineList = new LinkedList<>();
        List<Integer> digitalRectalList = new LinkedList<>();
        List<Integer> expressedProstaticSecretionList = new LinkedList<>();
        List<Integer> specificAntigenList = new LinkedList<>();
        List<Integer> ultrasonographyBList = new LinkedList<>();
        List<Integer> urineFlowRateList = new LinkedList<>();
        List<Integer> urineRoutineList = new LinkedList<>();

        //客户端答案解读
        if (medicalExaminationParam.getBloodRoutineAnswer() != null || !"".equals(medicalExaminationParam.getBloodRoutineAnswer())) {
            prostaticMedicalExamination.setBloodRoutineAnswer(medicalExaminationParam.getBloodRoutineAnswer());
            bloodRoutineList = BloodRoutineUtils.getBloodRoutineScores(medicalExaminationParam.getBloodRoutineAnswer());
            i++;
        } else if (medicalExaminationParam.getDigitalRectalAnswer() != null || !"".equals(medicalExaminationParam.getDigitalRectalAnswer())) {
            prostaticMedicalExamination.setDigitalRectalAnswer(medicalExaminationParam.getDigitalRectalAnswer());
            digitalRectalList = DigitalRectalUtils.getDigitalRectalList(medicalExaminationParam.getDigitalRectalAnswer());
            i++;
        } else if (medicalExaminationParam.getExpressedProstaticSecretionAnswer() != null || !"".equals(medicalExaminationParam.getExpressedProstaticSecretionAnswer())) {
            prostaticMedicalExamination.setExpressedProstaticSecretionAnswer(medicalExaminationParam.getExpressedProstaticSecretionAnswer());
            expressedProstaticSecretionList = ExpressedProstaticSecretionUtils.getExpressedProstaticSecretionList(medicalExaminationParam.getExpressedProstaticSecretionAnswer());
            i++;
        } else if (medicalExaminationParam.getSpecificAntigenAnswer() != null || !"".equals(medicalExaminationParam.getSpecificAntigenAnswer())) {
            prostaticMedicalExamination.setSpecificAntigenAnswer(medicalExaminationParam.getSpecificAntigenAnswer());
            specificAntigenList = SpecificAntigenUtils.getSpecificAntigenList(medicalExaminationParam.getSpecificAntigenAnswer());
            i++;
        } else if (medicalExaminationParam.getUltrasonographyBAnswer() != null || !"".equals(medicalExaminationParam.getUltrasonographyBAnswer())) {
            prostaticMedicalExamination.setUltrasonographyBAnswer(medicalExaminationParam.getUltrasonographyBAnswer());
            ultrasonographyBList = UltrasonographyBUtils.getUltrasonographyBList(medicalExaminationParam.getUltrasonographyBAnswer());
            i++;
        } else if (medicalExaminationParam.getUrineFlowRateAnswer() != null || !"".equals(medicalExaminationParam.getUrineFlowRateAnswer())) {
            prostaticMedicalExamination.setUrineFlowRateAnswer(medicalExaminationParam.getUrineFlowRateAnswer());
            urineFlowRateList = UrineFlowRateUtils.getUrineFlowRateList(medicalExaminationParam.getUrineFlowRateAnswer());
            i++;
        } else if (medicalExaminationParam.getUrineRoutineAnswer() != null || !"".equals(medicalExaminationParam.getUrineRoutineAnswer())) {
            prostaticMedicalExamination.setBloodRoutineAnswer(medicalExaminationParam.getUrineRoutineAnswer());
            urineRoutineList = UrineRoutineUtils.getUrineRoutineList(medicalExaminationParam.getUrineRoutineAnswer());
            i++;
        }
        //
        if (i == 0) {
            return emptyParamResponse();
        }

        //获取患者年龄
        int age = Integer.parseInt(medicalExaminationParam.getPatientAge());

        //添加异常检查项提示
        ProstateMedicalExaminationUtils.setAnomalyItem(bloodRoutineList, digitalRectalList, expressedProstaticSecretionList, specificAntigenList, ultrasonographyBList, urineFlowRateList, urineRoutineList, prostaticMedicalExamination);

        //根据年龄计算化验单解读提示
        if (age < 50) {
            prostaticMedicalExamination = ProstateMedicalExaminationUtils.getReport(bloodRoutineList, expressedProstaticSecretionList, ultrasonographyBList, urineRoutineList, prostaticMedicalExamination);
            prostaticMedicalExaminationService.insertSelective(prostaticMedicalExamination);
            return insertSuccseeResponse(prostaticMedicalExamination);
        } else if (age >= 50) {
            //计算前列腺增生风险 添加前列腺增生风险提示
            ProstateMedicalExaminationUtils.setBPHRemark(bloodRoutineList, digitalRectalList, expressedProstaticSecretionList, specificAntigenList, ultrasonographyBList, urineFlowRateList, urineRoutineList, prostaticMedicalExamination);
            prostaticMedicalExaminationService.insertSelective(prostaticMedicalExamination);
            return insertSuccseeResponse(prostaticMedicalExamination);
        }
        return insertFailedResponse();
    }
}
