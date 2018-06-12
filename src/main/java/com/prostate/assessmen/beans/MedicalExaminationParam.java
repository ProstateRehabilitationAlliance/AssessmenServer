package com.prostate.assessmen.beans;

public class MedicalExaminationParam {


    private String patientAge;

    private String bloodRoutineAnswer;

    private String digitalRectalAnswer;

    private String expressedProstaticSecretionAnswer;

    private String specificAntigenAnswer;

    private String ultrasonographyBAnswer;

    private String urineFlowRateAnswer;

    private String urineRoutineAnswer;

    public String getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(String patientAge) {
        this.patientAge = patientAge;
    }

    public String getBloodRoutineAnswer() {
        return bloodRoutineAnswer;
    }

    public void setBloodRoutineAnswer(String bloodRoutineAnswer) {
        this.bloodRoutineAnswer = bloodRoutineAnswer;
    }

    public String getDigitalRectalAnswer() {
        return digitalRectalAnswer;
    }

    public void setDigitalRectalAnswer(String digitalRectalAnswer) {
        this.digitalRectalAnswer = digitalRectalAnswer;
    }

    public String getExpressedProstaticSecretionAnswer() {
        return expressedProstaticSecretionAnswer;
    }

    public void setExpressedProstaticSecretionAnswer(String expressedProstaticSecretionAnswer) {
        this.expressedProstaticSecretionAnswer = expressedProstaticSecretionAnswer;
    }

    public String getSpecificAntigenAnswer() {
        return specificAntigenAnswer;
    }

    public void setSpecificAntigenAnswer(String specificAntigenAnswer) {
        this.specificAntigenAnswer = specificAntigenAnswer;
    }

    public String getUltrasonographyBAnswer() {
        return ultrasonographyBAnswer;
    }

    public void setUltrasonographyBAnswer(String ultrasonographyBAnswer) {
        this.ultrasonographyBAnswer = ultrasonographyBAnswer;
    }

    public String getUrineFlowRateAnswer() {
        return urineFlowRateAnswer;
    }

    public void setUrineFlowRateAnswer(String urineFlowRateAnswer) {
        this.urineFlowRateAnswer = urineFlowRateAnswer;
    }

    public String getUrineRoutineAnswer() {
        return urineRoutineAnswer;
    }

    public void setUrineRoutineAnswer(String urineRoutineAnswer) {
        this.urineRoutineAnswer = urineRoutineAnswer;
    }

    @Override
    public String toString() {
        return "MedicalExaminationParam{" +
                "patientAge='" + patientAge + '\'' +
                ", bloodRoutineAnswer='" + bloodRoutineAnswer + '\'' +
                ", digitalRectalAnswer='" + digitalRectalAnswer + '\'' +
                ", expressedProstaticSecretionAnswer='" + expressedProstaticSecretionAnswer + '\'' +
                ", specificAntigenAnswer='" + specificAntigenAnswer + '\'' +
                ", ultrasonographyBAnswer='" + ultrasonographyBAnswer + '\'' +
                ", urineFlowRateAnswer='" + urineFlowRateAnswer + '\'' +
                ", urineRoutineAnswer='" + urineRoutineAnswer + '\'' +
                '}';
    }
}
