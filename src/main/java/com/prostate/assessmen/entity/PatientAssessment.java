package com.prostate.assessmen.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatientAssessment {

    private String id;

    private int nihCpsiScore;

    private int ipssScore;

    private int scaleScore;

    private PatientIpssScore patientIpssScore;

    private PatientNihCpsiScore patientNihNpsiScore;

    private PatientScaleScore patientScaleScore;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIpssScore() {
        return ipssScore;
    }

    public void setIpssScore(int ipssScore) {
        this.ipssScore = ipssScore;
    }

    public int getNihCpsiScore() {
        return nihCpsiScore;
    }

    public void setNihCpsiScore(int nihCpsiScore) {
        this.nihCpsiScore = nihCpsiScore;
    }

    public int getScaleScore() {
        return scaleScore;
    }

    public void setScaleScore(int scaleScore) {
        this.scaleScore = scaleScore;
    }

    public PatientIpssScore getPatientIpssScore() {
        return patientIpssScore;
    }

    public void setPatientIpssScore(PatientIpssScore patientIpssScore) {
        this.patientIpssScore = patientIpssScore;
    }

    public PatientNihCpsiScore getPatientNihNpsiScore() {
        return patientNihNpsiScore;
    }

    public void setPatientNihNpsiScore(PatientNihCpsiScore patientNihNpsiScore) {
        this.patientNihNpsiScore = patientNihNpsiScore;
    }

    public PatientScaleScore getPatientScaleScore() {
        return patientScaleScore;
    }

    public void setPatientScaleScore(PatientScaleScore patientScaleScore) {
        this.patientScaleScore = patientScaleScore;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
