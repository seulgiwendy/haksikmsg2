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
	String [] priceList = time.split("��");
	ArrayList <String> menuArrayList = new ArrayList<String>();
	
	String [] menuList = newResult.split(" ");
	Collections.addAll(menuArrayList, menuList);

	
	for (int i = 0; i < menuArrayList.size(); i++){
		if (menuArrayList.get(i).contains("(")){
			menuArrayList.remove(i);
		}
	}
	
	if (cafChoice.equals("�ι����Ĵ�")){
		returnString = ("������ �ι����Ĵ� ���� �޴��Դϴ�. \n" + menuArrayList.get(0)+": �����ð� ->" + priceList[0] + "��\n" + menuArrayList.get(1) + ": �����ð� ->" + priceList[1] + "��\n" 
				+ "�� �޴�: " + menuArrayList.get(2) + ": �����ð� -> " + priceList[2]);}
	
	if (cafChoice.equals("����ȸ���Ĵ�")){
		returnString = ("������ ����ȸ���Ĵ� ���� �޴��Դϴ�. \n" + menuArrayList.get(3) + ": �����ð� -> " + priceList[3] + "��");
	}
	
	if (cafChoice.equals("��ī�̶����")){
		returnString = "����, �� ��� �� ��ż� �� �Ͻ÷�����?";
	}
	return returnString;
	}
	
	
}
