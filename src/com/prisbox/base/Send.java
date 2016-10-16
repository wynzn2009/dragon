package com.prisbox.base;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Send {
	public static String sendPost(String url,Map<String,String> parameters){
		String result = "";
		BufferedReader in = null;
		PrintWriter out = null;
		StringBuffer sb = new StringBuffer();
		String params = "";
		try {
			if(parameters.size()==1){
				for(String name : parameters.keySet()){
					sb.append(name).append("=").append(URLEncoder.encode(parameters.get(name), "UTF-8"));
				}
			}else{
				for(String name : parameters.keySet()){
					sb.append(name).append("=").append(URLEncoder.encode(parameters.get(name), "UTF-8")).append("&");
				}
				String temp_params = sb.toString();
				params = temp_params.substring(0, temp_params.length()-1);
			}
			URL connURL = new URL(url);
			HttpURLConnection httpConn = (HttpURLConnection) connURL.openConnection();
			httpConn.setRequestProperty("Accept", "*/*");
			httpConn.setRequestProperty("Connection", "Keep-Alive");
			httpConn.setRequestProperty("User_Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");
			httpConn.setDoInput(true);
			httpConn.setDoOutput(true);
			out = new PrintWriter(httpConn.getOutputStream());
			out.write(params);
			out.flush();
			in = new BufferedReader(new InputStreamReader(httpConn.getInputStream(),"UTF-8"));
			String line;
			while((line = in.readLine())!=null){
				result += line;
			}
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(out != null){
				out.close();
			}
			try {
				if(in != null){
					in.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url = "http://fund.eastmoney.com/f10/F10DataApi.aspx";
		HashMap<String, String> map = new HashMap<String,String>();
		map.put("type", "lsjz");
		map.put("code", "161028");
		map.put("page", "1");
		map.put("per", "372");
		map.put("sdate", "");
		map.put("edate", "");
		map.put("rt", "0.8845716235944072");
		String result = sendPost(url, map);
		String jsonResult = result.replace("var apidata=", "").replace(";", "");
		JsonParser parse = new JsonParser();
		JsonObject json = (JsonObject) parse.parse(jsonResult);
		String content = json.get("content").getAsString();
		int index = content.indexOf("tbody");
		content = content.substring(index);
		System.out.println(content);
		List<NAV> dataList = Data.parse(content);
		
		String fileUrl = "E:\\project\\dragon\\"+ new Date().getTime()+".txt";
		
		try {
			Data.toFile(fileUrl,dataList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
