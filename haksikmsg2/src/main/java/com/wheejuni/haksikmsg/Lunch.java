package com.wheejuni.haksikmsg;
import java.util.ArrayList;
import java.util.Collections;

import org.json.simple.*;
import org.jsoup.nodes.*;
import org.jsoup.parser.*;
import org.jsoup.select.*;
import org.jsoup.*;


public class Lunch {
	
	static ArrayList<String> setLunchMenu() throws Exception{
		
		String targetURL = "https://webs.hufs.ac.kr/jsp/HUFS/cafeteria/m/todayMenu.jsp?campus=H1&gubun=B";
		Document lunch = Jsoup.connect(targetURL).get();
		Elements lunchMenus = lunch.select("div.caf_rep");
		String lunchMenuRawList = lunchMenus.text();
		String [] lunchmenuList = lunchMenuRawList.split(" ");
		ArrayList <String> lunchmenuArrayList = new ArrayList<String>();
		Collections.addAll(lunchmenuArrayList, lunchmenuList);
		/*for (int i = 0;i < lunchmenuArrayList.size(); i++){
			if(lunchmenuArrayList.get(i).contains("(")){lunchmenuArrayList.remove(i);}
		
	}*/
		return lunchmenuArrayList;
}
	
	/*public static void main (String args[]) throws Exception{
		
		ArrayList<String> test = new ArrayList<String>();
		test = setLunchMenu();
		
		for (int i = 0; i < test.size(); i++){
			System.out.println(test.get(i));
		}*/
	//}
}
