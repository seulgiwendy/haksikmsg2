package com.wheejuni.haksikmessage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.util.ArrayList;


public class ReturnMenus {
	
	static String setPageParse(String cafChoice) throws Exception{
		
	
	String returnString = null;
	String targetURL = "https://webs.hufs.ac.kr/jsp/HUFS/cafeteria/m/todayMenu.jsp?campus=H1&gubun=B";
	Document doc = Jsoup.connect(targetURL).get();
	Elements menus = doc.select("div.caf_rep");
	Elements timePrice = doc.select("div.caf_times");
	String result = doc.toString();
	String newResult = menus.text();
	String time = timePrice.text();
	String [] priceList = time.split("원");
	ArrayList <String> menuArrayList = new ArrayList<String>();
	
	String [] menuList = newResult.split(" ");
	Collections.addAll(menuArrayList, menuList);

	
	for (int i = 0; i < menuArrayList.size(); i++){
		if (menuArrayList.get(i).contains("(")){
			menuArrayList.remove(i);
		}
	}
	
	if (cafChoice.equals("인문관식당")){
		returnString = ("오늘의 인문관식당 점심 메뉴입니다. \n" + menuArrayList.get(0)+": 제공시간 ->" + priceList[0] + "원\n" + menuArrayList.get(1) + ": 제공시간 ->" + priceList[1] + "원\n" 
				+ "면 메뉴: " + menuArrayList.get(2) + ": 제공시간 -> " + priceList[2]);}
	
	if (cafChoice.equals("교수회관식당")){
		returnString = ("오늘의 교수회관식당 점심 메뉴입니다. \n" + menuArrayList.get(3) + ": 제공시간 -> " + priceList[3] + "원");
	}
	
	if (cafChoice.equals("스카이라운지")){
		returnString = "에이, 그 비싼 거 드셔서 뭐 하시려구요?";
	}
	return returnString;
	}
	
	
}
