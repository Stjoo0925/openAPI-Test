package com.ohgiraffers.openapi.controller;

import com.ohgiraffers.openapi.service.TestInfoService;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@RestController
@RequestMapping("/api")
public class TestInfoController {

    @Autowired
    private TestInfoService testInfoService;

    @GetMapping("/fetch-and-save")
    public String fetchAndSaveData() {
        try {
            StringBuilder urlBuilder = new StringBuilder("http://openapi.q-net.or.kr/api/service/rest/InquiryTestDatesNationalProfessionalQualificationSVC/getList");
            urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=서비스키");
            urlBuilder.append("&" + URLEncoder.encode("seriesCd", "UTF-8") + "=" + URLEncoder.encode("21", "UTF-8"));
            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");

            BufferedReader rd;
            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }

            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            rd.close();
            conn.disconnect();

            String xml = sb.toString();
            JSONObject jsonObject = XML.toJSONObject(xml);

            // JSON 데이터를 서비스 클래스를 통해 데이터베이스에 저장
            testInfoService.saveTestInfoFromJson(jsonObject);

            return "Data fetched and saved successfully";

        } catch (Exception e) {
            e.printStackTrace();
            return "Error fetching data";
        }
    }
}
