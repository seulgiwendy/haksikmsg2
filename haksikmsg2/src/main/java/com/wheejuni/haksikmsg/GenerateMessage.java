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
		String inmun = "�ι����Ĵ�";
		String gyosu = "����ȸ���Ĵ�";
		String skylounge = "��ī�̶����";
		//String SorryMsg = "�˼��մϴ�. ����Ͽ��� ���񽺸� �������� �ʽ��ϴ�. �� ��ġ�ҰԿ�!";
		String morningMsg = "��ſ� ��ħ�Դϴ�. \n";
		String lunchMsg = "��ٸ��� ���ɽð��Դϴ�. \n";
		String dinnerMsg = "����ð��Դϴ�. \n";
		String sundayMsg = "�Ͽ��Ͽ��� ��� �л��Ĵ��� �޹��մϴ�!";
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
				Item menulist = DBtable.getItem("date", DBtimeIndex, "caf_name", "�ι����Ĵ��ħ");
				if (menulist == null) {returnString = "�޴� ������ �����ϴ�!";}
				JSONObject rawFormat = (JSONObject)parser.parse(menulist.toJSONPretty());
				JSONObject menupick = (JSONObject) rawFormat.get("menu_list");
				String menu = menupick.get("menu1").toString();
				returnString = (morningMsg + "������ �ι����Ĵ� ��ħ �޴��Դϴ�. \n�޴�: ") + menu + "\n(08:00 ~ 10:00)" + "\n����: 1500��";
				}
				catch(Exception e){
					String errorString = "�޴� ������ �����ϴ�!";
					return errorString;
				}
			}
			if(currentHour >= 10 && currentHour <= 15){
				try{
				Item menulist = DBtable.getItem("date", DBtimeIndex, "caf_name", "�ι����Ĵ�����");
				if (menulist == null) {returnString = "�޴� ������ �����ϴ�!";}
				JSONObject rawFormat = (JSONObject)parser.parse(menulist.toJSONPretty());
				JSONObject menupick = (JSONObject)rawFormat.get("menu_list");
				String menu1 = menupick.get("menu1").toString();
				String menu2 = menupick.get("menu2").toString();
				String noodle = menupick.get("menu3").toString();
				returnString = (lunchMsg + "������ �ι����Ĵ� ���� �޴��Դϴ�. \n�޴� 1: ") + menu1 + "\n(11:00 ~ 14:30)" + "\n����: 2500��" 
			+ "\n�޴� 2: " + menu2 + "\n(11:00 ~ 14:30)" + "\n����: 2000��"
			+ "\n�� �޴�: " + noodle + "\n(11:00 ~ 14:00)" + "\n����: 1500��";
				
			}
				catch(Exception e){
					String errorString = "�޴� ������ �����ϴ�!";
					return errorString;
				}
			}
			if(currentHour > 15){
				try{
				Item menulist = DBtable.getItem("date", DBtimeIndex, "caf_name", "�ι����Ĵ�����");
				if (menulist == null) {returnString = "�޴� ������ �����ϴ�!";}
				JSONObject rawFormat = (JSONObject)parser.parse(menulist.toJSONPretty());
				JSONObject menupick = (JSONObject)rawFormat.get("menu_list");
				String menu = menupick.get("menu1").toString();
				returnString = (dinnerMsg + "������ �ι����Ĵ� ���� �޴��Դϴ�. \n�޴�: ") + menu + "\n(16:40 ~ 18:40)" + "\n����: 2000��";
				}
				catch(Exception e){
					String errorString = "�޴� ������ �����ϴ�!";
					return errorString;
				}
			}
			
			
					}
		else if(cafChoice.equals(gyosu)){
			
			if(currentHour >= 0 && currentHour < 10){
				returnString = "�װ� �Ƽ���? ����ȸ���Ĵ��� ��ħ���� �� �ݴϴ�.";
				
				
			}
			if(currentHour >= 10 && currentHour <= 15){
				try{
				Item menulist = DBtable.getItem("date", DBtimeIndex, "caf_name", "����ȸ���Ĵ�����");
				if (menulist == null) {returnString = "�޴� ������ �����ϴ�!";}
				JSONObject rawFormat = (JSONObject)parser.parse(menulist.toJSONPretty());
				JSONObject menupick = (JSONObject)rawFormat.get("menu_list");
				String menu = menupick.get("menu1").toString();
				returnString = (lunchMsg + "������ ����ȸ���Ĵ� ���� �޴��Դϴ�. \n�޴�: ") + menu + "\n(11:00 ~ 13:30)" + "\n����: 4500��" ;
				
				
			}
				catch(Exception e){
					String errorString = "�޴� ������ �����ϴ�!";
					return errorString;
				}
			}
			
			
			if(currentHour > 15){
				try{
				Item menulist = DBtable.getItem("date", DBtimeIndex, "caf_name", "����ȸ���Ĵ�����");
				
				JSONObject rawFormat = (JSONObject)parser.parse(menulist.toJSONPretty());
				JSONObject menupick = (JSONObject)rawFormat.get("menu_list");
				String menu = menupick.get("menu1").toString();
				returnString = (dinnerMsg + "������ ����ȸ���Ĵ� ���� �޴��Դϴ�. \n �޴�: ") + menu + "\n(16:40 ~ 18:40)" + "\n����: 4500��";}
				catch(Exception e){
					String errorString = "�޴� ������ �����ϴ�!";
					return errorString;
				}
			}
			
		}
		else{
			//String returnString1 = "��! ������ �߸��Ƴ� ���ϴ�. \n1:1 ����ϱ� ��ư�� ���� ������ �˷��ּ���! \n (��ī�̶������ ���� �������� �ʽ��ϴ�. ��ī�̶������ �� �����帱�Կ�!)";
			String returnString1 = WholeMenu.returnWholeMenu();
			return returnString1;
			
		}
		
		
		return returnString;
		
	}

}
