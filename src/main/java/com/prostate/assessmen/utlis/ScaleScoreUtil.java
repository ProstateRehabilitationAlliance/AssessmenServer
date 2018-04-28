package com.prostate.assessmen.utlis;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.List;

@Slf4j
public class ScaleScoreUtil {

    public final static int[] answerList = {1, 1, 1, 1, 1, 1, 0, 1, 2, 3, 4, 5, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 0, 1, 2, 3, 4, 5, 0, 1, 2, 3, 4, 5, 0, 1, 2, 3, 4, 5, 0, 1, 2, 3, 4, 5, 0, 1, 2, 3, 4, 5, 0, 1, 2, 3, 4, 5, 0, 1, 2, 3, 4, 5, 0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 4, 5, 6};

    /**
     * 量表评估 获取所有评估得分
     *
     * @param scaleScore
     * @return
     */
    public static List<Integer> getScores(String scaleScore) {
        log.info("######量表评估 获取所有评估得分#####");
        log.info(scaleScore);
        int ia[] = new int[scaleScore.length()];
        for(int i=0;i<scaleScore.length();i++){
            char c = scaleScore.charAt(i);//逐个获取字符串中的字符
            ia[i]=(int)(c-'0');
        }
//        char[] scaleScores = scaleScore.toCharArray();
        List<Integer> scoreList = new LinkedList();
        log.info(ia.toString());
        int i = 0;
        for (int score : ia) {
            switch (score) {
                case 1:
                    scoreList.add(answerList[i]);
                    break;
                case 0:
                    scoreList.add(0);
                    break;
            }
            i++;
        }
        log.info(scoreList.toString());
        log.info("i="+i);
        return scoreList;
    }

    /**
     * 判断疾病类型
     *
     * @param scoreList
     * @param type
     * @return
     */
    public static String checkIllnessType(List<Integer> scoreList, char type, Integer patientAge) {

        int result = 0;
        List<Integer> newScoreList = new LinkedList<>();
        switch (type) {
            case 0:
                newScoreList.addAll(scoreList.subList(0, 1));
                for (Integer num : newScoreList) {
                    result += num;
                }
                if (result > 0 && patientAge < 40) {
                    //前列腺炎
                    return checkDegree(scoreList,'0');
                } else if (result == 0 && patientAge < 40) {
                    checkIllnessType(scoreList, '1', patientAge);
                } else if (result > 0 && patientAge > 39) {
                    checkIllnessType(scoreList, '2', patientAge);
                } else if (result == 0 && patientAge > 39) {
                    checkIllnessType(scoreList, '3', patientAge);
                }
                break;
            case 1:
                newScoreList.addAll(scoreList.subList(0, 1));
                for (Integer num : newScoreList) {
                    result += num;
                }
                if (result == 0) {
                    //正常
                    return "正常";
                } else if (result > 0) {
                    //前列腺增生
                    return checkDegree(scoreList,'1');
                }
                break;
            case 2:
                newScoreList.addAll(scoreList.subList(0, 1));
                for (Integer num : newScoreList) {
                    result += num;
                }
                if (result == 0) {
                    //不确定
                    return "不确定";
                } else if (result > 0) {
                    //前列腺炎
                    return checkDegree(scoreList,'0');
                }
                break;
            case 3:
                newScoreList.addAll(scoreList.subList(0, 1));
                for (Integer num : newScoreList) {
                    result += num;
                }
                if (result == 0) {
                    //正常
                    return "正常";
                } else if (result > 0) {
                    //前列腺增生
                    return checkDegree(scoreList,'1');
                }
                break;
            default:
                break;
        }
        return "不确定";
    }


    /**
     * 病情严重程度查询
     *
     * @param scoreList
     * @param type      0:前列腺炎 ;1:前列腺增生
     * @return
     */
    private static String checkDegree(List<Integer> scoreList, char type) {
        int result = 0;
        List<Integer> newScoreList = new LinkedList<>();
        switch (type) {
            case 0:
                newScoreList.addAll(scoreList.subList(0, 1));
                for (Integer num : newScoreList) {
                    result += num;
                }
                if (result < 15) {
                    //轻度
                    return "轻度前列腺炎";
                } else if (result > 29) {
                    //重度
                    return "重度前列腺炎";
                } else {
                    //中度
                    return "中度前列腺炎";
                }
            case 1:
                newScoreList.addAll(scoreList.subList(0, 1));
                for (Integer num : newScoreList) {
                    result += num;
                }
                if (result < 8) {
                    //轻度
                    return "轻度前列腺增生";
                } else if (result > 19) {
                    //重度
                    return "重度前列腺增生";
                } else {
                    //中度
                    return "中度前列腺增生";
                }
            default:
                break;
        }
        return "不确定";
    }
}
