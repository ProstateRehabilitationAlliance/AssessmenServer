package com.prostate.assessmen.controller;

import com.prostate.assessmen.cache.redis.RedisSerive;
import com.prostate.assessmen.entity.Doctor;
import com.prostate.assessmen.entity.PatientScaleScore;
import com.prostate.assessmen.service.ScaleScoreService;
import com.prostate.assessmen.util.ScaleScoreUtils;
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
@RequestMapping(value = "scaleScore")
public class ScaleScoreController extends BaseController {

    @Autowired
    private ScaleScoreService scaleScoreService;

    @Autowired
    private RedisSerive redisSerive;


    /**
     * 添加IPSS评估结果
     * @param scaleScore
     * @param token
     * @return
     */
    @PostMapping(value = "add")
    public Map add(PatientScaleScore scaleScore, String token, Integer patientAge){
        log.info("#########前列腺症状量表评估结果添加############"+scaleScore.getAnswer().length());
        //参数校验
        if(scaleScore==null||scaleScore.getAnswer().length()<80){
            return emptyParamResponse();
        }
        Doctor doctor = redisSerive.getDoctor(token);
        scaleScore.setCreateDoctor(doctor.getId());
        List<Integer> scoreList = ScaleScoreUtils.getScores(scaleScore.getAnswer());
        String caution = ScaleScoreUtils.checkIllnessType(scoreList,'0',patientAge);
        scaleScore.setCaution(caution);
        String optionScore = ScaleScoreUtils.getOptionScore(scoreList);
        scaleScore.setOptionScore(optionScore);
        if(scaleScore.getId()==null||"".equals(scaleScore.getId())){
            scaleScoreService.insertSelective(scaleScore);
        }else{
            PatientScaleScore ss = scaleScoreService.selectById(scaleScore.getId());
            if(ss!=null){
                scaleScoreService.updateSelective(scaleScore);
            }else{
                scaleScoreService.insertSelectiveById(scaleScore);
            }
        }
        resultMap = new LinkedHashMap<>();
        resultMap.put("code","20000");
        resultMap.put("msg","SUCCESS");
        resultMap.put("result",scaleScore);
        resultMap.put("scoreList",scoreList);
        resultMap.put("caution",caution);
        resultMap.put("optionScore",optionScore);
        return resultMap;
    }

}
