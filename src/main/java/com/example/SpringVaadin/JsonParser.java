package com.example.SpringVaadin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public class JsonParser {
	
	public static String urlAl() {
		StringBuilder result = new StringBuilder();
		String urlToRead = "http://api.openweathermap.org/data/2.5/weather?q=istanbul,tr&appid=2fd3b35c4b7fcfe84964b9b829703075";
		try {
			URL url = new URL(urlToRead);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
			rd.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONObject json;
		String xml=null;
		try {
			json = new JSONObject(result.toString());
			xml = XML.toString(json);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	return xml;
    }
		  public static void main(final String[] argv) throws DocumentException  {
			System.out.println(urlAl());

	}
}
