package com.prostate.assessmen.controller;

import com.prostate.assessmen.cache.redis.RedisSerive;
import com.prostate.assessmen.entity.Doctor;
import com.prostate.assessmen.entity.PatientIpssScore;
import com.prostate.assessmen.service.PatientIpssScoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "patientIpssScore")
public class PatientIpssScoreController extends BaseController {

    @Autowired
    private PatientIpssScoreService patientIpssScoreService;

    @Autowired
    private RedisSerive redisSerive;

    /**
     * 添加IPSS评估结果
     * @param patientIpssScore
     * @param token
     * @return
     */
    @PostMapping(value = "add")
    public Map add(PatientIpssScore patientIpssScore, String token){
        log.info("#########前列腺症状评分（IPSS）结果添加############生活质量指数评分（QOL)结果添加############");
        //参数校验
        if(patientIpssScore==null){
            return emptyParamResponse();
        }
        Doctor doctor = redisSerive.getDoctor(token);
        patientIpssScore.setCreateDoctor(doctor.getId());

        if(patientIpssScore.getId()==null||"".equals(patientIpssScore.getId())){
            patientIpssScoreService.insertSelective(patientIpssScore);
        }else{
            PatientIpssScore pis = patientIpssScoreService.selectById(patientIpssScore.getId());
            if(pis!=null){
                patientIpssScoreService.updateSelective(patientIpssScore);
            }else{
                patientIpssScoreService.insertSelectiveById(patientIpssScore);
            }
        }
        return insertSuccseeResponse(patientIpssScore);
    }


    /**
     * 根据ID查询一条IPSS评估记录
     * @param ipssScoreId
     * @return
     */
    @PostMapping(value = "getById")
    public Map getById(String ipssScoreId){
        log.info("########查询一条前列腺症状评分（IPSS）结果添加############生活质量指数评分（QOL)结果############");
        //参数校验
        if(ipssScoreId==null){

            return emptyParamResponse();
        }
        PatientIpssScore patientIpssScore = patientIpssScoreService.selectById(ipssScoreId);
        if(patientIpssScore!=null){
            return querySuccessResponse(patientIpssScore);
        }
        return queryEmptyResponse();
    }
}
