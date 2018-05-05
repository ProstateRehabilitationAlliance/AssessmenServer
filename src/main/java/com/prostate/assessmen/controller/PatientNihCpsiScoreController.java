package com.prostate.assessmen.controller;

import com.prostate.assessmen.cache.redis.RedisSerive;
import com.prostate.assessmen.entity.Doctor;
import com.prostate.assessmen.entity.PatientNihCpsiScore;
import com.prostate.assessmen.service.PatientNihCpsiScoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "patientNihCpsiScore")
public class PatientNihCpsiScoreController extends BaseController {

    @Autowired
    private PatientNihCpsiScoreService patientNihCpsiScoreService;

    @Autowired
    private RedisSerive redisSerive;

    @RequestMapping(value = "add")
    public Map add(PatientNihCpsiScore patientNihNpsiScore, String token){
        log.info("#############慢性前列腺炎症状评分（NIH-CPSI）结果添加##############");
        //参数校验
        if(patientNihNpsiScore==null){
            return emptyParamResponse();
        }
        Doctor doctor = redisSerive.getDoctor(token);
        patientNihNpsiScore.setCreateDoctor(doctor.getId());

        if(patientNihNpsiScore.getId()==null||"".equals(patientNihNpsiScore.getId())){
            patientNihCpsiScoreService.insertSelective(patientNihNpsiScore);
        }else{
            PatientNihCpsiScore pnns = patientNihCpsiScoreService.selectById(patientNihNpsiScore.getId());
            if(pnns!=null){
                patientNihCpsiScoreService.updateSelective(patientNihNpsiScore);
            }else{

                patientNihCpsiScoreService.insertSelectiveById(patientNihNpsiScore);
            }
        }
        return insertSuccseeResponse(patientNihNpsiScore);
    }


    /**
     * 根据ID查询一条NIH-NPSI评估记录
     * @param nihCpsiScoreId
     * @return
     */
    @PostMapping(value = "getById")
    public Map getById(String nihCpsiScoreId){
        log.info("########查询一条性前列腺炎症状评分（NIH-CPSI）结果############");
        //参数校验
        if(nihCpsiScoreId==null){

            return emptyParamResponse();
        }
        PatientNihCpsiScore patientNihCpsiScore = patientNihCpsiScoreService.selectById(nihCpsiScoreId);
        if(patientNihCpsiScore!=null){

            return querySuccessResponse(patientNihCpsiScore);
        }
        return queryEmptyResponse();
    }
}