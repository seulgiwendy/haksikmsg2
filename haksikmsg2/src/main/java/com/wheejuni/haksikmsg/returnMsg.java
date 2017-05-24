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
        
      	/*if (getMessage.equals("��ü �޴� ����")){
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
        //returnMessage = "�� ���� ���񽺸� ���� ��ġ���Դϴ�! ���� �ٽ� ������! \n(������ ����ź���� �����Ϸ�, ��� �л��Ĵ��� ���ϴ�.)";
       setMsg.put("text", returnMessage);
       returnJson.put("message", setMsg);
       
      	
      	cafList.add("�ι����Ĵ�");
      	cafList.add("����ȸ���Ĵ�");
      	cafList.add("��ü �޴� ����");
      	selectList.put("type", "buttons");
      	selectList.put("buttons", cafList);
      	returnJson.put("keyboard", selectList);
        
        return returnJson;
    }

}
