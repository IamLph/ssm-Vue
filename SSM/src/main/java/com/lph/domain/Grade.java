package com.lph.domain;

import java.io.Serializable;

/**
 * 成绩试题类
 */
public class Grade implements Serializable {
    private String userId;
    private String core;
    private String testData;
    private int weifaOP;

    public Grade() {
    }

    public Grade(String userId, String core, String testData, int weifaOP) {
        this.userId = userId;
        this.core = core;
        this.testData = testData;
        this.weifaOP = weifaOP;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCore() {
        return core;
    }

    public void setCore(String core) {
        this.core = core;
    }

    public String getTestData() {
        return testData;
    }

    public void setTestData(String testData) {
        this.testData = testData;
    }

    public int getWeifaOP() {
        return weifaOP;
    }

    public void setWeifaOP(int weifaOP) {
        this.weifaOP = weifaOP;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "userId='" + userId + '\'' +
                ", core='" + core + '\'' +
                ", testData='" + testData + '\'' +
                ", weifaOP=" + weifaOP +
                '}';
    }
}
