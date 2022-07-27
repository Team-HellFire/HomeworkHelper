package com.HellFire;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController //데이터를 리턴 @Controller + @Response Body
public class ApiDataController {

    @Value ("${Client-Id}")
    String clientId;//애플리케이션 클라이언트 아이디값";

    @Value ("${Client-Secret}")
    String clientSecret;//애플리케이션 클라이언트 시크릿값";

    String apiURL = "https://openapi.naver.com/v1/papago/n2mt"; //요청 api url"
    String text;

    @RequestMapping("/translate/en-ko")
    public String translateStartEnToKo (@RequestBody String text) throws Throwable {
        String ret;
        try {
            ret = URLEncoder.encode(text, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("인코딩 실패", e);
        }

        System.out.println("========잘 됬나..?=========");
        System.out.println("text : " + text);
        System.out.println("encoded :"+ ret);

        Map<String, String> requestHeaders = new HashMap<>(); //API서버로 보낼 해시맵
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);

        String langParameter = "source=en&target=ko&text=";

        System.out.println("apiURL : " + apiURL);
        System.out.println("requestHeaders : " + requestHeaders);
        System.out.println("ret : " + text);
        System.out.println("langParameter : " + langParameter);

        String responseBody = post(apiURL, requestHeaders, text, langParameter);

        System.out.println("========잘 됬나..?=========");
        System.out.println("respnseBody : " + responseBody);

        //Strig을 JSON으로 변환
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(responseBody);
        JSONObject jsonObj = (JSONObject) obj;
        JSONObject message = (JSONObject) jsonObj.get("message");
        JSONObject result = (JSONObject) message.get("result");
        String translatedData = (String) result.get("translatedText");

        // "text =" 을 제거
        int eraseIndex = translatedData.indexOf("=");
        String completeText = translatedData.substring(eraseIndex+1);
        System.out.println("translatedData : " + completeText);

        return completeText;
    }

    @RequestMapping("/translate/ko-en")
    public String translateStartKoToEn (@RequestBody String text) throws Throwable {
        String ret;
        try {
            ret = URLEncoder.encode(text, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("인코딩 실패", e);
        }

        System.out.println("========잘 됬나..?=========");
        System.out.println("text : " + text);
        System.out.println("encoded :"+ ret);

        Map<String, String> requestHeaders = new HashMap<>(); //API서버로 보낼 해시맵
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);

        String langParameter = "source=ko&target=en&text=";

        System.out.println("apiURL : " + apiURL);
        System.out.println("requestHeaders : " + requestHeaders);
        System.out.println("ret : " + ret);
        System.out.println("langParameter : " + langParameter);

        String responseBody = post(apiURL, requestHeaders, text, langParameter);

        System.out.println("========잘 됬나..?=========");
        System.out.println("respnseBody : " + responseBody);

        //String을 JSON으로 변환
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(responseBody);
        JSONObject jsonObj = (JSONObject) obj;
        JSONObject message = (JSONObject) jsonObj.get("message");
        JSONObject result = (JSONObject) message.get("result");
        String translatedData = (String) result.get("translatedText");

        // "text =" 을 제거
        int eraseIndex = translatedData.indexOf("=");
        String completeText = translatedData.substring(eraseIndex+1);
        System.out.println("translatedData : " + completeText);

        return completeText;
    }


    private static String post(String apiUrl, Map<String, String> requestHeaders, String text, String langParameter){
        HttpURLConnection con = connect(apiUrl);

        int eraseIndex = text.indexOf("=");
        String requestText = text.substring(eraseIndex+1);
        System.out.println("requestText :"+requestText);

        String postParams = langParameter + requestText;
        try {
            con.setRequestMethod("POST");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }

            con.setDoOutput(true);
            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.write(postParams.getBytes());
                wr.flush();
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 응답
                return readBody(con.getInputStream());
            } else {  // 에러 응답
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }

    private static HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }

    private static String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);

        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();

            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }

            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }
}

