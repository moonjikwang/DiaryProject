package Diary.Controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import org.json.JSONObject;

public class Trans {
	
	   public static String trans(String msg) {
	        String clientId = "aqCPYfEE4Bkvh2QLKvGk";
	        String clientSecret = "f1BzjLv9y5";
	        StringBuffer response = null;
	        String result = null;
	        try {
	            String text = URLEncoder.encode(msg, "UTF-8");
	            String apiURL = "https://openapi.naver.com/v1/papago/n2mt";
	            URL url = new URL(apiURL);
	            HttpURLConnection con = (HttpURLConnection)url.openConnection();
	            con.setRequestMethod("POST");
	            con.setRequestProperty("X-Naver-Client-Id", clientId);
	            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
	            String postParams = "source=ko&target=en&text=" + text;
	            con.setDoOutput(true);
	            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
	            wr.writeBytes(postParams);
	            wr.flush();
	            int responseCode = con.getResponseCode();
	            BufferedReader br;
	            if(responseCode==200) { 
	                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
	            } else {  
	                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
	            }
	            String inputLine;
	            String json = null; //  json 읽기 1
	            response = new StringBuffer();
	            while ((inputLine = br.readLine()) != null) {
	                response.append(inputLine);
	                json = inputLine; // json읽기 2
	            }
	            br.close();
	        	JSONObject root = new JSONObject(json); // json 읽기 3
	            br.close();
	            result = root.getJSONObject("message").getJSONObject("result").getString("translatedText"); // json 읽기 4
	      
	        } catch (Exception e) {
	            System.out.println(e);
	        }
	        return result;
	    }
}
