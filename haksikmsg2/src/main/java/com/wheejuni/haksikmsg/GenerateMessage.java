package com.wheejuni.haksikmsg;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TimeZone;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.util.ArrayList;
import java.util.Calendar;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import org.json.simple.*;
import org.json.*;
import org.json.simple.parser.*;
import java.text.SimpleDateFormat;

public class GenerateMessage {
	
	static String setMsg(String cafChoice) throws Exception{
		
		TimeZone tz = TimeZone.getTimeZone("GMT+09:00");
		JSONParser parser = new JSONParser();
		
		
		ArrayList <String> lunchBucket = new ArrayList<String>();
		lunchBucket = Lunch.setLunchMenu();
		ArrayList<String> dinnerBucket = Dinner.setDinnerMenu();
		String inmun = "인문관식당";
		String gyosu = "교수회관식당";
		String skylounge = "스카이라운지";
		//String SorryMsg = "죄송합니다. 토요일에는 서비스를 제공하지 않습니다. 곧 패치할게요!";
		String morningMsg = "즐거운 아침입니다. \n";
		String lunchMsg = "기다리던 점심시간입니다. \n";
		String dinnerMsg = "저녁시간입니다. \n";
		String sundayMsg = "일요일에는 모든 학생식당이 휴무합니다!";
		String returnString = null;
		
		AWSCredentials credentials = null;
		credentials = new BasicAWSCredentials("AKIAJLZEHTD6FSIO6QNA", "AHKZBTa9CpsXS0RwI2+PcFFW86UviyWQ9cqKDY0p");
		AmazonDynamoDBClient newClient = new AmazonDynamoDBClient(credentials);
		Region seoul = Region.getRegion(Regions.AP_NORTHEAST_2);
		
		newClient.setRegion(seoul);
		DynamoDB getDB = new DynamoDB(newClient);
		Table DBtable = getDB.getTable("hufshaksik");
		
		SimpleDateFormat datelabel = new SimpleDateFormat("YYYYMMdd");
		
		Calendar timeDateCount = Calendar.getInstance(tz);
		String timeString = datelabel.format(timeDateCount.getTime());
		int DBtimeIndex = Integer.parseInt(timeString);
		int currentWeekDay = timeDateCount.get(Calendar.DAY_OF_WEEK) ;
		int currentHour = timeDateCount.get(Calendar.HOUR_OF_DAY);
		 
		
		System.out.println(currentHour);
		if(currentWeekDay == 1){
			return sundayMsg;
		}

		if (currentWeekDay == 7){
			return SaturdayMeal.setSaturdayMenu(cafChoice);
		}
				
		
		if(cafChoice.equals(inmun)){
			
			
			if(currentHour >= 0 && currentHour < 10){
				//String menu = Breakf.setBreakfMenu();
				try{
				Item menulist = DBtable.getItem("date", DBtimeIndex, "caf_name", "인문관식당아침");
				if (menulist == null) {returnString = "메뉴 정보가 없습니다!";}
				JSONObject rawFormat = (JSONObject)parser.parse(menulist.toJSONPretty());
				JSONObject menupick = (JSONObject) rawFormat.get("menu_list");
				String menu = menupick.get("menu1").toString();
				returnString = (morningMsg + "오늘의 인문관식당 아침 메뉴입니다. \n메뉴: ") + menu + "\n(08:00 ~ 10:00)" + "\n가격: 1500원";
				}
				catch(Exception e){
					String errorString = "메뉴 정보가 없습니다!";
					return errorString;
				}
			}
			if(currentHour >= 10 && currentHour <= 15){
				try{
				Item menulist = DBtable.getItem("date", DBtimeIndex, "caf_name", "인문관식당점심");
				if (menulist == null) {returnString = "메뉴 정보가 없습니다!";}
				JSONObject rawFormat = (JSONObject)parser.parse(menulist.toJSONPretty());
				JSONObject menupick = (JSONObject)rawFormat.get("menu_list");
				String menu1 = menupick.get("menu1").toString();
				String menu2 = menupick.get("menu2").toString();
				String noodle = menupick.get("menu3").toString();
				returnString = (lunchMsg + "오늘의 인문관식당 점심 메뉴입니다. \n메뉴 1: ") + menu1 + "\n(11:00 ~ 14:30)" + "\n가격: 2500원" 
			+ "\n메뉴 2: " + menu2 + "\n(11:00 ~ 14:30)" + "\n가격: 2000원"
			+ "\n면 메뉴: " + noodle + "\n(11:00 ~ 14:00)" + "\n가격: 1500원";
				
			}
				catch(Exception e){
					String errorString = "메뉴 정보가 없습니다!";
					return errorString;
				}
			}
			if(currentHour > 15){
				try{
				Item menulist = DBtable.getItem("date", DBtimeIndex, "caf_name", "인문관식당저녁");
				if (menulist == null) {returnString = "메뉴 정보가 없습니다!";}
				JSONObject rawFormat = (JSONObject)parser.parse(menulist.toJSONPretty());
				JSONObject menupick = (JSONObject)rawFormat.get("menu_list");
				String menu = menupick.get("menu1").toString();
				returnString = (dinnerMsg + "오늘의 인문관식당 저녁 메뉴입니다. \n메뉴: ") + menu + "\n(16:40 ~ 18:40)" + "\n가격: 2000원";
				}
				catch(Exception e){
					String errorString = "메뉴 정보가 없습니다!";
					return errorString;
				}
			}
			
			
					}
		else if(cafChoice.equals(gyosu)){
			
			if(currentHour >= 0 && currentHour < 10){
				returnString = "그거 아세요? 교수회관식당은 아침밥을 안 줍니다.";
				
				
			}
			if(currentHour >= 10 && currentHour <= 15){
				try{
				Item menulist = DBtable.getItem("date", DBtimeIndex, "caf_name", "교수회관식당점심");
				if (menulist == null) {returnString = "메뉴 정보가 없습니다!";}
				JSONObject rawFormat = (JSONObject)parser.parse(menulist.toJSONPretty());
				JSONObject menupick = (JSONObject)rawFormat.get("menu_list");
				String menu = menupick.get("menu1").toString();
				returnString = (lunchMsg + "오늘의 교수회관식당 점심 메뉴입니다. \n메뉴: ") + menu + "\n(11:00 ~ 13:30)" + "\n가격: 4500원" ;
				
				
			}
				catch(Exception e){
					String errorString = "메뉴 정보가 없습니다!";
					return errorString;
				}
			}
			
			
			if(currentHour > 15){
				try{
				Item menulist = DBtable.getItem("date", DBtimeIndex, "caf_name", "교수회관식당저녁");
				
				JSONObject rawFormat = (JSONObject)parser.parse(menulist.toJSONPretty());
				JSONObject menupick = (JSONObject)rawFormat.get("menu_list");
				String menu = menupick.get("menu1").toString();
				returnString = (dinnerMsg + "오늘의 교수회관식당 저녁 메뉴입니다. \n 메뉴: ") + menu + "\n(16:40 ~ 18:40)" + "\n가격: 4500원";}
				catch(Exception e){
					String errorString = "메뉴 정보가 없습니다!";
					return errorString;
				}
			}
			
		}
		else{
			//String returnString1 = "앗! 뭔가가 잘못됐나 봅니다. \n1:1 상담하기 버튼을 눌러 오류를 알려주세요! \n (스카이라운지는 아직 지원하지 않습니다. 스카이라운지도 곧 보여드릴게요!)";
			String returnString1 = WholeMenu.returnWholeMenu();
			return returnString1;
			
		}
		
		
		return returnString;
		
	}

}
