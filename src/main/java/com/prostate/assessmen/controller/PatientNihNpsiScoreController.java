package com.prostate.assessmen.controller;

import com.prostate.assessmen.cache.redis.RedisSerive;
import com.prostate.assessmen.entity.Doctor;
import com.prostate.assessmen.entity.PatientNihNpsiScore;
import com.prostate.assessmen.service.PatientNihNpsiScoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "patientNihNpsiScore")
public class PatientNihNpsiScoreController extends BaseController {

    @Autowired
    private PatientNihNpsiScoreService patientNihNpsiScoreService;

    @Autowired
    private RedisSerive redisSerive;

    @RequestMapping(value = "add")
    public Map add(PatientNihNpsiScore patientNihNpsiScore,String token){
        log.info("#############慢性前列腺炎症状评分（NIH-CPSI）结果添加##############");
        resultMap = new LinkedHashMap<>();
        //参数校验
        if(patientNihNpsiScore==null){
            resultMap.put("code","20001");
            resultMap.put("msg","PARAM_EMPTY");
            resultMap.put("result",null);
            return resultMap;
        }
        Doctor doctor = redisSerive.getDoctor(token);
        patientNihNpsiScore.setCreateDoctor(doctor.getId());

        if(patientNihNpsiScore.getId()==null||"".equals(patientNihNpsiScore.getId())){
            log.info("#############慢性前列腺炎症状评分（NIH-CPSI）结果添加##############");
            patientNihNpsiScoreService.insertSelective(patientNihNpsiScore);
        }else{
            PatientNihNpsiScore pnns = patientNihNpsiScoreService.selectById(patientNihNpsiScore.getId());
            if(pnns!=null){
                log.info("#############慢性前列腺炎症状评分（NIH-CPSI）结果修改##############");
                patientNihNpsiScoreService.updateSelective(patientNihNpsiScore);
            }else{
                log.info("#############慢性前列腺炎症状评分（NIH-CPSI）结果添加ID##############");

                patientNihNpsiScoreService.insertSelectiveById(patientNihNpsiScore);
            }
        }
        resultMap.put("code","20000");
        resultMap.put("msg","SUCCESS");
        resultMap.put("result",patientNihNpsiScore.getId());
        return resultMap;
    }
}
