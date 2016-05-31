package com.ems.data;

import java.io.File;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import com.ems.data.dao.DataReadException;
import com.ems.data.dao.DataSaveException;

public class DataSaver {
	public static JSONObject readSingleData(String fileName, Map<String, String> map) throws DataReadException{
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
			throw new DataReadException();
		}
		return null;
	}
	
	public static JSONArray readAllData(String fileName) throws DataReadException{
		try{
			File file = new File(fileName);
			String jsonStr = FileUtility.readFile(file);
			JSONArray jsonArray = new JSONArray(jsonStr);
			return jsonArray;
		}catch(Exception e){
			e.printStackTrace();
			throw new DataReadException();
		}
	}
	
	public static void writeData(String fileName, JSONObject obj, boolean isNewData) throws DataSaveException {
		try{
			File file = new File(fileName);
			String jsonStr = FileUtility.readFile(file);
			JSONArray jsonArray = new JSONArray(jsonStr);
			
			if(isNewData){
				if(jsonArray.length() == 0){
					obj.put("Id", 1);
				}else{
					JSONObject lastObj = jsonArray.getJSONObject(jsonArray.length()-1);
					obj.put("Id", lastObj.getInt("Id") + 1);
				}
			}
			
			jsonArray.put(obj);
			FileUtility.writeFile(file, jsonArray.toString());
		}catch(Exception e){
			e.printStackTrace();
			throw new DataSaveException();
		}
	}
}
