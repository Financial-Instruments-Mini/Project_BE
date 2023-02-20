package com.financial.member.entity.enums;

public enum Job {
    OFFICE_WORKERS("회사원"), PUBLIC_OFFICIAL("공무원"), PROFESSION("전문직"),
    AGRICULTURAL_WORKER("농부"), BUISNESSMAN("사업가/자영업자"), FREELANCER("프리랜서"),
    HOUSEWIFE("주부"), STUDENT("학생"), SOLDIER("군인"), INOCCUPATION("무직");

    private String jobName;

    Job(String jobName) {
        this.jobName = jobName;
    }
    public String getJobName() {
        return jobName;
    }
}
