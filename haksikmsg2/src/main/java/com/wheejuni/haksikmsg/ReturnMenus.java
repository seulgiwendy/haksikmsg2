package com.wheejuni.haksikmsg;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.util.ArrayList;
import org.json.simple.*;
import org.json.simple.parser.*;


public class ReturnMenus {
	
	static String setPageParse(String cafChoice) throws Exception{
		
		
		
		String returnMsg = GenerateMessage.setMsg(cafChoice); 
		return returnMsg;
		
	}
}

	
	
