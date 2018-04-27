package com.prostate.assessmen.controller;

import com.prostate.assessmen.entity.PatientAssessment;
import com.prostate.assessmen.service.PatientAssessmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "assessment")
public class PatientAssessmentController extends BaseController {

    @Autowired
    private PatientAssessmentService assessmentService;


    @PostMapping(value = "findByPatientId")
    public Map getPatientAssessmentList(String patientId){
        resultMap = new LinkedHashMap<>();
        //参数校验
        if(patientId==null||"".equals(patientId)){
            resultMap.put("msg","PARAM_EMPTY");
            resultMap.put("code","20001");
            resultMap.put("result",null);
            return resultMap;
        }

        PatientAssessment patientAssessment =new PatientAssessment();
        patientAssessment.setId(patientId);
        List<PatientAssessment> patientAssessmentList =  assessmentService.selectByPatientId(patientAssessment);
        if(patientAssessmentList!=null&&patientAssessmentList.size()>0){
            resultMap.put("code","20000");
            resultMap.put("msg","SUCCESS");
            resultMap.put("result",patientAssessmentList);
            return resultMap;
        }
        resultMap.put("code","20002");
        resultMap.put("msg","EMPTY_RESULT");
        resultMap.put("result",null);
        return resultMap;
    }

    @PostMapping(value = "getById")
    public Map getPatientAssessment(String id){
         log.info("#########前列腺症状评分（IPSS）结果添加############生活质量指数评分（QOL)结果添加############");
         resultMap = new LinkedHashMap<>();
         //参数校验
         if(id==null||"".equals(id)){
             resultMap.put("code","20001");
             resultMap.put("msg","PARAM_EMPTY");
             resultMap.put("result",null);
             return resultMap;
         }
         PatientAssessment patientAssessment =new PatientAssessment();
         patientAssessment.setId(id);
         patientAssessment =  assessmentService.selectById(patientAssessment);
         if(patientAssessment!=null){
             resultMap.put("code","20000");
             resultMap.put("msg","SUCCESS");
             resultMap.put("result",patientAssessment);
             return resultMap;

         }
         resultMap.put("code","20002");
         resultMap.put("msg","EMPTY_RESULT");
         resultMap.put("result",null);
         return resultMap;
    }
}
