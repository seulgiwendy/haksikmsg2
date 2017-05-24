package com.wheejuni.haksikmsg;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import com.amazonaws.services.lambda.runtime.events.S3Event;


import org.json.simple.*;
import org.json.simple.parser.*;

public class returnMsg implements RequestHandler<JSONObject, JSONObject> {

    @Override
    public JSONObject handleRequest(JSONObject input, Context context) {
    	String getMessage = input.get("content").toString();
        String returnMessage = null;
        JSONObject setMsg = new JSONObject();
        JSONObject returnJson = new JSONObject();
        JSONObject selectList = new JSONObject();
      	JSONArray cafList = new JSONArray();
        
      	/*if (getMessage.equals("전체 메뉴 보기")){
      	try {
			returnMessage = WholeMenu.returnWholeMenu();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}}*/
      	
      	
       try {
			returnMessage = ReturnMenus.setPageParse(getMessage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //returnMessage = "더 나은 서비스를 위한 패치중입니다! 내일 다시 만나요! \n(오늘은 석가탄신일 공휴일로, 모든 학생식당이 쉽니다.)";
       setMsg.put("text", returnMessage);
       returnJson.put("message", setMsg);
       
      	
      	cafList.add("인문관식당");
      	cafList.add("교수회관식당");
      	cafList.add("전체 메뉴 보기");
      	selectList.put("type", "buttons");
      	selectList.put("buttons", cafList);
      	returnJson.put("keyboard", selectList);
        
        return returnJson;
    }

}
