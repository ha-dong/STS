package com.example.storycraft.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class KakaoService {

    // Access Token을 가져오는 메서드
    public String getAccessToken(String code) {
        String tokenUrl = "https://kauth.kakao.com/oauth/token";
        String params = "grant_type=authorization_code" +
                "&client_id=277b7da274fd3f49f47cb0c95028c100" +  // REST API 키 사용
                "&redirect_uri=https://a16f-123-142-55-115.ngrok-free.app/StoryCraft/callback" +  // 새로운 ngrok URL로 수정
                "&code=" + code;

        String accessToken = null;

        try {
            // HTTP 연결 설정
            HttpURLConnection conn = (HttpURLConnection) new URL(tokenUrl).openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            // 요청 파라미터 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            bw.write(params);
            bw.flush();

            // 응답 읽기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                result.append(line);
            }

            // JSON 파싱 및 액세스 토큰 추출
            JSONObject json = new JSONObject(result.toString());
            accessToken = json.getString("access_token");

            // 로그 출력
            System.out.println("Access Token: " + accessToken);

            br.close();
            bw.close();

        } catch (Exception e) {
            // 예외 발생 시 로그 출력
            System.err.println("Error while getting access token: " + e.getMessage());
            e.printStackTrace();
        }

        return accessToken;
    }

    // 사용자 정보를 가져오는 메서드
    public Map<String, Object> getUserInfo(String accessToken) {
        String reqUrl = "https://kapi.kakao.com/v2/user/me";
        Map<String, Object> userInfo = new HashMap<>();

        try {
            // HTTP 연결 설정
            HttpURLConnection conn = (HttpURLConnection) new URL(reqUrl).openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", "Bearer " + accessToken);

            // 응답 읽기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                result.append(line);
            }

            // JSON 파싱 및 사용자 정보 추출
            JSONObject json = new JSONObject(result.toString());
            userInfo.put("id", json.getLong("id"));
            userInfo.put("nickname", json.getJSONObject("properties").getString("nickname"));

            // 이메일 정보 추출 (선택적)
            if (json.getJSONObject("kakao_account").has("email")) {
                userInfo.put("email", json.getJSONObject("kakao_account").getString("email"));
            } else {
                userInfo.put("email", null);  // 이메일이 없는 경우
            }

            // 로그 출력
            System.out.println("User Info: " + userInfo);

            br.close();

        } catch (Exception e) {
            // 예외 발생 시 로그 출력
            System.err.println("Error while getting user info: " + e.getMessage());
            e.printStackTrace();
        }

        return userInfo;
    }

}
