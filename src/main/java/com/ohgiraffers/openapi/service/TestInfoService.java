package com.ohgiraffers.openapi.service;

import com.ohgiraffers.openapi.entity.TestInfo;
import com.ohgiraffers.openapi.repository.TestInfoRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestInfoService {

    @Autowired
    private TestInfoRepository testInfoRepository;

    public void saveTestInfoFromJson(JSONObject jsonObject) {
        JSONObject responseBody = jsonObject.getJSONObject("response").getJSONObject("body");
        JSONObject item = responseBody.getJSONObject("items").getJSONObject("item");

        TestInfo testInfo = new TestInfo();
        testInfo.setPassEndDt(item.getString("PASS_END_DT"));
        testInfo.setDescription(item.getString("description"));
        testInfo.setExamRegStartDt(item.getString("EXAM_REG_START_DT"));
        testInfo.setExamRegEndDt(item.getString("EXAM_REG_END_DT"));
        testInfo.setExamStartDt(item.getString("EXAM_START_DT"));
        testInfo.setExamEndDt(item.getString("EXAM_END_DT"));
        testInfo.setPassStartDt(item.getString("PASS_START_DT"));

        testInfoRepository.save(testInfo);
    }
}
