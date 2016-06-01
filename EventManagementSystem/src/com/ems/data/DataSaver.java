package com.ems.data;

import java.io.File;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import com.ems.data.dao.DataReadException;
import com.ems.data.dao.DataSaveException;

public class DataSaver {
	
	private static final String KEY_ID = "Id";
	
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
						String value = jsonObject.get(key) + "";
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
	
	public static void writeData(String fileName, JSONObject objToWrite, boolean isNewData) throws DataSaveException {
		try{
			File file = new File(fileName);
			String jsonStr = FileUtility.readFile(file);
			JSONArray jsonArray = new JSONArray(jsonStr);
			
			if(isNewData){ //insert new data
				if(jsonArray.length() == 0){
					objToWrite.put(KEY_ID, 1);
				}else{
					JSONObject lastObj = jsonArray.getJSONObject(jsonArray.length()-1);
					objToWrite.put(KEY_ID, lastObj.getInt(KEY_ID) + 1);
				}
				jsonArray.put(objToWrite);
			}else{ //update existing data
				int id = objToWrite.getInt(KEY_ID);
				for(int i=0; i<jsonArray.length(); ++i){
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					if(jsonObject.getInt(KEY_ID) == id){
						jsonArray.put(i, objToWrite);
						break;
					}
				}
			}
			FileUtility.writeFile(file, jsonArray.toString());
		}catch(Exception e){
			e.printStackTrace();
			throw new DataSaveException();
		}
	}
	
	public static void deleteData(String fileName, int id) throws DataSaveException {
		try{
			File file = new File(fileName);
			String jsonStr = FileUtility.readFile(file);
			JSONArray jsonArray = new JSONArray(jsonStr);
			for(int i=0; i<jsonArray.length(); ++i){
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				if(jsonObject.getInt(KEY_ID) == id){
					jsonArray.remove(i);
					break;
				}
			}
			FileUtility.writeFile(file, jsonArray.toString());
		}catch(Exception e){
			e.printStackTrace();
			throw new DataSaveException();
		}
	}
}
