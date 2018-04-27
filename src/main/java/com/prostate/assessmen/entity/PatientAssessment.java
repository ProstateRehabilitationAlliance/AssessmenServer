package com.prostate.assessmen.entity;


import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatientAssessment {

    private String id;

    private String ipssScore;

    private String nihNpsiScore;

    private PatientIpssScore patientIpssScore;

    private PatientNihNpsiScore patientNihNpsiScore;

    private Date createTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIpssScore() {
        return ipssScore;
    }

    public void setIpssScore(String ipssScore) {
        this.ipssScore = ipssScore;
    }

    public String getNihNpsiScore() {
        return nihNpsiScore;
    }

    public void setNihNpsiScore(String nihNpsiScore) {
        this.nihNpsiScore = nihNpsiScore;
    }

    public PatientIpssScore getPatientIpssScore() {
        return patientIpssScore;
    }

    public void setPatientIpssScore(PatientIpssScore patientIpssScore) {
        this.patientIpssScore = patientIpssScore;
    }

    public PatientNihNpsiScore getPatientNihNpsiScore() {
        return patientNihNpsiScore;
    }

    public void setPatientNihNpsiScore(PatientNihNpsiScore patientNihNpsiScore) {
        this.patientNihNpsiScore = patientNihNpsiScore;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
