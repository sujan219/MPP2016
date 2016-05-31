package com.ems.data;

import java.io.File;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

public class DataSaver {
	public static JSONObject readData(String fileName, Map<String, String> map){
		try{
			File file = new File(fileName);
			String jsonStr = FileUtility.readFile(file);
			JSONArray jsonArray = new JSONArray(jsonStr);
			for(int i=0; i<jsonArray.length(); ++i){
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				Set<String> keys = map.keySet();
				boolean match = true;
				for(String key:keys){
					if(!jsonObject.has(key)){
						match = false;
						break;
					}else{
						String value = jsonObject.getString(key);
						if(!value.equals(map.get(key))){
							match = false;
						}
					}
				}
				
				if(match){
					return jsonObject;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean writeData(String fileName, JSONObject obj){
		try{
			File file = new File(fileName);
			String jsonStr = FileUtility.readFile(file);
			JSONArray jsonArray = new JSONArray(jsonStr);
			jsonArray.put(obj);
			FileUtility.writeFile(file, jsonArray.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
		return true;
	}
}
