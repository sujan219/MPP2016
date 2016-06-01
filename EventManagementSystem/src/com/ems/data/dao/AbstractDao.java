package com.ems.data.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ems.baseclasses.DataObject;
import com.ems.data.DataSaver;

public abstract class AbstractDao {
	
	public DataObject getRecordById(int id) throws DataReadException {
		Map<String, String> map = new HashMap<String, String>();
		map.put("Id", id+"");
		JSONObject jsonObject = DataSaver.readSingleData(getFileName(), map);
		try {
			return getObjectFromJSON(jsonObject);
		} catch (JSONException e) {
			e.printStackTrace();
			throw new DataReadException();
		}
	}

	public List<DataObject> getAllRecords() throws DataReadException {
		List<DataObject> listOfData = new ArrayList<DataObject>();
		try{
			JSONArray jsonArray = DataSaver.readAllData(getFileName());
			for(int i=0; i<jsonArray.length(); ++i){
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				listOfData.add(getObjectFromJSON(jsonObject));
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new DataReadException();
		}
		return listOfData;
	}
	
	public abstract void saveNewRecord() throws DataSaveException;
	public abstract void modifyRecord() throws DataSaveException;
	public abstract void deleteRecord() throws DataSaveException;
	public abstract DataObject getObjectFromJSON(JSONObject obj) throws JSONException;
	public abstract String getFileName();
}
