package com.prostate.assessmen.controller;

import com.prostate.assessmen.cache.redis.RedisSerive;
import com.prostate.assessmen.entity.ProstaticMedicalExamination;
import com.prostate.assessmen.entity.WechatUser;
import com.prostate.assessmen.service.ProstaticMedicalExaminationService;
import com.prostate.assessmen.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * 医学 检查 检验 接口
 */
@Slf4j
@RestController
@RequestMapping(value = "medicalExamination")
public class MedicalExaminationController extends BaseController {

    @Autowired
    private ProstaticMedicalExaminationService prostaticMedicalExaminationService;
    @Autowired
    private RedisSerive redisSerive;

    /**
     * 添加化验单解读结果
     *
     * @return
     */
    @RequestMapping(value = "add")
    public Map add(ProstaticMedicalExamination prostaticMedicalExamination, String patientAge, String token) {
        String patientId = prostaticMedicalExamination.getPatientId();
        if ((patientId == null || "".equals(patientId)) && token != null && !"".equals(token)) {
            WechatUser wechatUser = redisSerive.getWechatUser(token);
            prostaticMedicalExamination.setPatientId(wechatUser.getPatientId());
        } else if (patientId != null && !"".equals(patientId) && token != null && !"".equals(token)) {
            prostaticMedicalExamination.setPatientId(patientId);
        } else {
            return emptyParamResponse();
        }
        int i = 0;
        List<Integer> bloodRoutineList = new LinkedList<>();
        List<Integer> digitalRectalList = new LinkedList<>();
        List<Integer> expressedProstaticSecretionList = new LinkedList<>();
        List<Integer> specificAntigenList = new LinkedList<>();
        List<Integer> ultrasonographyBList = new LinkedList<>();
        List<Integer> urineFlowRateList = new LinkedList<>();
        List<Integer> urineRoutineList = new LinkedList<>();

        //客户端答案解读
        if (prostaticMedicalExamination.getBloodRoutineAnswer() != null || !"".equals(prostaticMedicalExamination.getBloodRoutineAnswer())) {
            bloodRoutineList = BloodRoutineUtils.getBloodRoutineScores(prostaticMedicalExamination.getBloodRoutineAnswer());
            i++;
        }
        if (prostaticMedicalExamination.getDigitalRectalAnswer() != null || !"".equals(prostaticMedicalExamination.getDigitalRectalAnswer())) {
            digitalRectalList = DigitalRectalUtils.getDigitalRectalList(prostaticMedicalExamination.getDigitalRectalAnswer());
            i++;
        }
        if (prostaticMedicalExamination.getExpressedProstaticSecretionAnswer() != null || !"".equals(prostaticMedicalExamination.getExpressedProstaticSecretionAnswer())) {
            expressedProstaticSecretionList = ExpressedProstaticSecretionUtils.getExpressedProstaticSecretionList(prostaticMedicalExamination.getExpressedProstaticSecretionAnswer());
            i++;
        }
        if (prostaticMedicalExamination.getSpecificAntigenAnswer() != null || !"".equals(prostaticMedicalExamination.getSpecificAntigenAnswer())) {
            specificAntigenList = SpecificAntigenUtils.getSpecificAntigenList(prostaticMedicalExamination.getSpecificAntigenAnswer());
            i++;
        }
        if (prostaticMedicalExamination.getUltrasonographyBAnswer() != null || !"".equals(prostaticMedicalExamination.getUltrasonographyBAnswer())) {
            ultrasonographyBList = UltrasonographyBUtils.getUltrasonographyBList(prostaticMedicalExamination.getUltrasonographyBAnswer());
            i++;
        }
        if (prostaticMedicalExamination.getUrineFlowRateAnswer() != null || !"".equals(prostaticMedicalExamination.getUrineFlowRateAnswer())) {
            urineFlowRateList = UrineFlowRateUtils.getUrineFlowRateList(prostaticMedicalExamination.getUrineFlowRateAnswer());
            i++;
        }
        if (prostaticMedicalExamination.getUrineRoutineAnswer() != null || !"".equals(prostaticMedicalExamination.getUrineRoutineAnswer())) {
            urineRoutineList = UrineRoutineUtils.getUrineRoutineList(prostaticMedicalExamination.getUrineRoutineAnswer());
            i++;
        }
        //
        if (i == 0) {
            return emptyParamResponse();
        }


        //获取患者年龄
        int age = Integer.parseInt(patientAge);

        //添加异常检查项提示
        ProstateMedicalExaminationUtils.setAnomalyItem(bloodRoutineList, digitalRectalList, expressedProstaticSecretionList, specificAntigenList, ultrasonographyBList, urineFlowRateList, urineRoutineList, prostaticMedicalExamination);

        //根据年龄计算化验单解读提示
        if (age < 50) {
            ProstateMedicalExaminationUtils.setProstatitis(bloodRoutineList, expressedProstaticSecretionList, ultrasonographyBList, urineRoutineList, prostaticMedicalExamination);
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

    /**
     * 根据患者Id 查询 化验单解读记录
     */
    @RequestMapping(value = "getByPatientId")
    public LinkedHashMap getByPatientId(String patientId, String token) {
        ProstaticMedicalExamination prostaticMedicalExamination = new ProstaticMedicalExamination();
        if ((patientId == null || "".equals(patientId)) && token != null && !"".equals(token)) {
            WechatUser wechatUser = redisSerive.getWechatUser(token);
            prostaticMedicalExamination.setPatientId(wechatUser.getPatientId());
        } else if (patientId != null && !"".equals(patientId) && token != null && !"".equals(token)) {
            prostaticMedicalExamination.setPatientId(patientId);
        } else {
            return emptyParamResponse();
        }

        List<ProstaticMedicalExamination> prostaticMedicalExaminationList = prostaticMedicalExaminationService.selectByParams(prostaticMedicalExamination);
        if (prostaticMedicalExaminationList == null || prostaticMedicalExaminationList.size() == 0) {
            return queryEmptyResponse();
        }

        LinkedHashMap<String, LinkedHashMap<String, List<ProstaticMedicalExamination>>> mp = new LinkedHashMap<>();

        for (ProstaticMedicalExamination medicalExamination : prostaticMedicalExaminationList) {
            Date date = medicalExamination.getCreateTime();
            String year = String.valueOf(date.getYear() + 1900);
            String month = String.valueOf(date.getMonth() + 1);
            LinkedHashMap<String, List<ProstaticMedicalExamination>> y = mp.get(year);
            if (y != null) {
                List<ProstaticMedicalExamination> m = y.get(month);
                if (m != null) {
                    m.add(medicalExamination);
                } else {
                    List<ProstaticMedicalExamination> l = new LinkedList<>();
                    l.add(medicalExamination);
                    y.put(month, l);
                }
            } else {
                LinkedHashMap<String, List<ProstaticMedicalExamination>> s = new LinkedHashMap<>();
                List<ProstaticMedicalExamination> l = new LinkedList<>();
                l.add(medicalExamination);
                s.put(month, l);
                mp.put(year, s);
            }

        }
//        return querySuccessResponse(JSONObject.toJSONString(mp, SerializerFeature.SortField));
        return querySuccessResponse(mp);
    }
}
