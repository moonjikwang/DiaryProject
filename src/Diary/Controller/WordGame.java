package Diary.Controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class WordGame {
	public String wordGame(String userValue) {
		String result = null;
		try {
			String key = "0C3C823C86C3442028F352854EC6DC44";
			String word =userValue;	
			StringBuffer response = null;
			URL url = new URL("https://opendict.korean.go.kr/api/search?key=" + key
					+ "&type_search=search&q=" + word+"&req_type=json&advanced=y&target=1&method=include&type1=proverb&letter_s=2&letter_e=20&sort=popular&num=15");
			HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line = "";
			 response = new StringBuffer();
	            while ((line = br.readLine()) != null) {
	                response.append(line);
	            }
	            JSONParser parser = new JSONParser();
	            JSONArray jsonArr = new JSONArray();
	            JSONObject jsonObj = null;
	            
	            jsonObj = (JSONObject) parser.parse(response.toString());
	            jsonObj = (JSONObject) jsonObj.get("channel");
	            jsonArr = (JSONArray) jsonObj.get("item");
	            	jsonObj = (JSONObject) jsonArr.get((int)(Math.random()*15));
	            	result = jsonObj.get("word").toString();
	            	jsonArr = (JSONArray) jsonObj.get("sense");
	            	jsonObj = (JSONObject) jsonArr.get(0);
	            	result += "("+jsonObj.get("definition")+")";
	            	br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}

