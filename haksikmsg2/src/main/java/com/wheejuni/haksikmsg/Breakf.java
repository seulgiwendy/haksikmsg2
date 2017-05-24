package com.wheejuni.haksikmsg;
import org.json.simple.*;
import org.jsoup.nodes.*;
import org.jsoup.parser.*;
import org.jsoup.select.*;
import org.jsoup.*;


public class Breakf {
	static String setBreakfMenu() throws Exception{
		String targetURL = "https://webs.hufs.ac.kr/jsp/HUFS/cafeteria/m/todayMenu.jsp?campus=H1&gubun=A";
		Document breakf = Jsoup.connect(targetURL).get();
		Elements breakfmenu = breakf.select("div.caf_rep");
		
		String breakfList = breakfmenu.text();
		
		
		return breakfList;
	}
	
	
	

}
