package com.wheejuni.haksikmsg;
import java.util.ArrayList;
import java.util.Collections;

import org.json.simple.*;
import org.jsoup.nodes.*;
import org.jsoup.parser.*;
import org.jsoup.select.*;
import org.jsoup.*;

public class Dinner {
	static ArrayList<String> setDinnerMenu() throws Exception{
		String targetURL = "https://webs.hufs.ac.kr/jsp/HUFS/cafeteria/m/todayMenu.jsp?campus=H1&gubun=C";
		Document dinner = Jsoup.connect(targetURL).get();
		Elements dinnerMenus = dinner.select("div.caf_rep");
		String dinnerMenuRawList = dinnerMenus.text();
		String [] dinnermenuList = dinnerMenuRawList.split(" ");
		ArrayList <String> dinnermenuArrayList = new ArrayList<String>();
		Collections.addAll(dinnermenuArrayList, dinnermenuList);
		
		/*for (int i = 0;i < dinnermenuArrayList.size(); i++){
			if(dinnermenuArrayList.get(i).contains("(")){dinnermenuArrayList.remove(i);}
		}*/
		return dinnermenuArrayList;
		
	
	}
	

}
