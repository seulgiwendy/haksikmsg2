package com.wheejuni.haksikmessage;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.json.simple.*;
import org.json.simple.parser.*;

public class returnMessageSet implements RequestHandler<JSONObject, JSONObject> {

    @Override
    public JSONObject handleRequest(JSONObject input, Context context) {
    	 context.getLogger().log("Input: " + input);

         
         String getMessage = input.get("content").toString();
         String keyMessage = null;
         if (getMessage == null){keyMessage = "null";}
         String returnMessage = null;
         JSONObject setMsg = new JSONObject();
         JSONObject returnJson = new JSONObject();
         
         try {
			returnMessage = ReturnMenus.setPageParse(getMessage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
        setMsg.put("text", returnMessage);
        returnJson.put("message", setMsg);
         
         return returnJson;
    }
    
    

}
