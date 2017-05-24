package com.wheejuni.haksikmsg;
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

public class WholeMenu {
	static final String[] days = {"일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"}; 
	
	
	static String getBreakfMenu() throws ParseException{
		
		try{
		String returnString = null;
		JSONParser parser = new JSONParser();
		Calendar timeDateCount = Calendar.getInstance();
		SimpleDateFormat datelabel = new SimpleDateFormat("YYYYMMdd");
		String timeString = datelabel.format(timeDateCount.getTime());
		int DBtimeIndex = Integer.parseInt(timeString);
		AWSCredentials credentials = null;
		credentials = new BasicAWSCredentials("AKIAJLZEHTD6FSIO6QNA", "AHKZBTa9CpsXS0RwI2+PcFFW86UviyWQ9cqKDY0p");
		AmazonDynamoDBClient newClient = new AmazonDynamoDBClient(credentials);
		Region seoul = Region.getRegion(Regions.AP_NORTHEAST_2);
		
		newClient.setRegion(seoul);
		DynamoDB getDB = new DynamoDB(newClient);
		Table DBtable = getDB.getTable("hufshaksik");
		
		Item menulist = DBtable.getItem("date", DBtimeIndex, "caf_name", "인문관식당아침");
		if (menulist == null) {returnString = "메뉴 정보가 없습니다!";}
		JSONObject rawFormat = (JSONObject)parser.parse(menulist.toJSONPretty());
		JSONObject menupick = (JSONObject) rawFormat.get("menu_list");
		String menu = menupick.get("menu1").toString();
		returnString = menu;
		
		
		return returnString;}
		catch(Exception e){
			String returnString = "메뉴 로드 오류";
			return returnString;
		}
	}
	
	static ArrayList<String> getLunchMenu() throws ParseException{
		
		try{
		ArrayList <String> returnString = new ArrayList<String>();
		JSONParser parser = new JSONParser();
		Calendar timeDateCount = Calendar.getInstance();
		SimpleDateFormat datelabel = new SimpleDateFormat("YYYYMMdd");
		String timeString = datelabel.format(timeDateCount.getTime());
		int DBtimeIndex = Integer.parseInt(timeString);
		AWSCredentials credentials = null;
		credentials = new BasicAWSCredentials("AKIAJLZEHTD6FSIO6QNA", "AHKZBTa9CpsXS0RwI2+PcFFW86UviyWQ9cqKDY0p");
		AmazonDynamoDBClient newClient = new AmazonDynamoDBClient(credentials);
		Region seoul = Region.getRegion(Regions.AP_NORTHEAST_2);
		
		newClient.setRegion(seoul);
		DynamoDB getDB = new DynamoDB(newClient);
		Table DBtable = getDB.getTable("hufshaksik");
		
		Item menulist = DBtable.getItem("date", DBtimeIndex, "caf_name", "인문관식당점심");
		if (menulist == null) {returnString.add("메뉴 정보가 없습니다!");}
		JSONObject rawFormat = (JSONObject)parser.parse(menulist.toJSONPretty());
		JSONObject menupick = (JSONObject) rawFormat.get("menu_list");
		
		Item gyosumenu = DBtable.getItem("date", DBtimeIndex, "caf_name", "교수회관식당점심");
		JSONObject gyosuRaw = (JSONObject)parser.parse(gyosumenu.toJSONPretty());
		JSONObject gyosubob = (JSONObject)gyosuRaw.get("menu_list");
		
		
		String menu1 = menupick.get("menu1").toString(); returnString.add(menu1);
		String menu2 = menupick.get("menu2").toString(); returnString.add(menu2);
		String noodle = menupick.get("menu3").toString(); returnString.add(noodle);
		String gyosu = gyosubob.get("menu1").toString(); returnString.add(gyosu);
		
		
	
		return returnString;}
		catch(Exception e){
			String errormsg = "메뉴 로드 오류";
			ArrayList<String> errorString = new ArrayList<>();
			for (int i = 0; i < 4; i++){
			errorString.add(errormsg);
			}
			return errorString;
		}
	}
	
	static ArrayList<String> getDinnerMenu() throws ParseException{
		try{
		ArrayList <String> returnString = new ArrayList<String>();
		JSONParser parser = new JSONParser();
		Calendar timeDateCount = Calendar.getInstance();
		SimpleDateFormat datelabel = new SimpleDateFormat("YYYYMMdd");
		String timeString = datelabel.format(timeDateCount.getTime());
		int DBtimeIndex = Integer.parseInt(timeString);
		AWSCredentials credentials = null;
		credentials = new BasicAWSCredentials("AKIAJLZEHTD6FSIO6QNA", "AHKZBTa9CpsXS0RwI2+PcFFW86UviyWQ9cqKDY0p");
		AmazonDynamoDBClient newClient = new AmazonDynamoDBClient(credentials);
		Region seoul = Region.getRegion(Regions.AP_NORTHEAST_2);
		
		newClient.setRegion(seoul);
		DynamoDB getDB = new DynamoDB(newClient);
		Table DBtable = getDB.getTable("hufshaksik");
		
		Item menulist = DBtable.getItem("date", DBtimeIndex, "caf_name", "인문관식당저녁");
		if (menulist == null) {returnString.add("메뉴 정보가 없습니다!");}
		JSONObject rawFormat = (JSONObject)parser.parse(menulist.toJSONPretty());
		JSONObject menupick = (JSONObject) rawFormat.get("menu_list");
		
		Item gyosumenu = DBtable.getItem("date", DBtimeIndex, "caf_name", "교수회관식당저녁");
		JSONObject gyosuRaw = (JSONObject)parser.parse(gyosumenu.toJSONPretty());
		JSONObject gyosubob = (JSONObject)gyosuRaw.get("menu_list");
		
		String menu1 = menupick.get("menu1").toString(); returnString.add(menu1);
		String menu2 = gyosubob.get("menu1").toString(); returnString.add(menu2);
		
		return returnString;}
		catch(Exception e){
			String errormsg = "메뉴 로드 오류";
			ArrayList<String> errorString = new ArrayList<>();
			for (int i = 0; i < 2; i++){
			errorString.add(errormsg);
			}
			return errorString;
		}
		
	}
	
	static String getSatLunch() throws ParseException{

		try{
		String returnString = null;
		JSONParser parser = new JSONParser();
		Calendar timeDateCount = Calendar.getInstance();
		SimpleDateFormat datelabel = new SimpleDateFormat("YYYYMMdd");
		String timeString = datelabel.format(timeDateCount.getTime());
		int DBtimeIndex = Integer.parseInt(timeString);
		AWSCredentials credentials = null;
		credentials = new BasicAWSCredentials("AKIAJLZEHTD6FSIO6QNA", "AHKZBTa9CpsXS0RwI2+PcFFW86UviyWQ9cqKDY0p");
		AmazonDynamoDBClient newClient = new AmazonDynamoDBClient(credentials);
		Region seoul = Region.getRegion(Regions.AP_NORTHEAST_2);
		
		newClient.setRegion(seoul);
		DynamoDB getDB = new DynamoDB(newClient);
		Table DBtable = getDB.getTable("hufshaksik");
		
		Item menulist = DBtable.getItem("date", DBtimeIndex, "caf_name", "인문관식당점심");
		if (menulist == null) {returnString = "메뉴 정보가 없습니다!";}
		JSONObject rawFormat = (JSONObject)parser.parse(menulist.toJSONPretty());
		JSONObject menupick = (JSONObject) rawFormat.get("menu_list");
		String menu = menupick.get("menu1").toString();
		returnString = menu;
		
		
		return returnString;}
		catch(Exception e){
			String returnString = "메뉴 로드 오류";
			return returnString;
		}
		
	}
	
	static String returnWholeMenu() throws ParseException{
		
		String returnString = null;
		String returnSatString = null;
		
		
		SimpleDateFormat datelabel = new SimpleDateFormat("YYYY-MM-dd");
		Calendar timeDateCount = Calendar.getInstance();
		String timeStamp = datelabel.format(timeDateCount.getTime());
		String day = null;
		
		ArrayList<String> getlunch = getLunchMenu();
		ArrayList<String> getDinner = getDinnerMenu();
		
		String lunchmenu1 = getlunch.get(0);
		String lunchmenu2 = getlunch.get(1);
		String lunchmenu3 = getlunch.get(2);
		String lunchmenu4 = getlunch.get(3);
		String dinnermenu1 = getDinner.get(0);
		String dinnermenu2 = getDinner.get(1);
		int currentWeekDay = timeDateCount.get(Calendar.DAY_OF_WEEK);
		switch(currentWeekDay){
		case 1: 
			day = days[0];
			break;
		case 2:
			day = days[1];
			break;
		case 3:
			day = days[2];
			break;
		case 4:
			day = days[3];
			break;
		case 5:
			day = days[4];
			break;
		case 6:
			day = days[5];
			break;
		case 7:
			day = days[7];
			break;
		}
		
		String timeInfoString = (timeStamp + " " + day + " 오늘의 메뉴입니다.");
		
		if (currentWeekDay == 7){
			returnString = (timeInfoString + "\n" + "점심\n" + "===========\n" + "인문관" + getSatLunch());
		}
		else {
		returnString = (timeInfoString + "\n" + "아침(인문관)\n" + "===========\n" + getBreakfMenu() + "(1500원)\n\n\n"
		+ "점심(인문관)\n" + "===========\n" + lunchmenu1 + "(2500원)\n" + lunchmenu2 + "(2000원)\n" + lunchmenu3 + "(1500원)\n\n\n" + "점심(교수회관)\n" + "===========\n" + lunchmenu4 + "(4500원)\n\n\n"
		+ "저녁(인문관)\n" + "===========\n" + dinnermenu1 + "(2500원)\n\n\n" + "\n저녁(교수회관)\n" + "===========\n" + dinnermenu2 + "(4500원)\n"); }
		return returnString;
	}

}
