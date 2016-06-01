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
import com.ems.ui.DialogUtil;

public abstract class AbstractDao {
	
	private DataObject dataObj;
	
	protected AbstractDao(DataObject dataObj){
		this.dataObj = dataObj;
	}
	
	protected AbstractDao(){}
	
	public final DataObject getRecordById(int id) throws DataReadException {
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

	public final List<DataObject> getAllRecords() throws DataReadException {
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
	
	public final void saveNewRecord() throws DataSaveException {
		try {
			JSONObject obj = getJSONObject();
			DataSaver.writeData(getFileName(), obj, true);
		} catch (JSONException e) {
			e.printStackTrace();
			throw new DataSaveException(e.getMessage());
		}
	}

	public final void modifyRecord() throws DataSaveException {
		if(dataObj.getId() == 0){
			throw new DataSaveException("The record cannot be modified because it does not exist. Check the id of record");
		}else{
			try{
				JSONObject obj = getJSONObject();
				DataSaver.writeData(getFileName(), obj, false);
			}catch(JSONException e){
				e.printStackTrace();
				throw new DataSaveException();
			}
		}
	}

	public final void deleteRecord() throws DataSaveException {
		if(dataObj.getId() == 0){
			throw new DataSaveException("The record cannot be deleted because it does not exist. Check the id of record");
		}else{
			try{
				DataSaver.deleteData(getFileName(), dataObj.getId());
				DialogUtil.showSuccessDialog("Record deleted successfully");
			}catch(Exception e){
				e.printStackTrace();
				DialogUtil.showErrorDialog();
				throw new DataSaveException();
			}
		}
	}
	
	
	public abstract DataObject getObjectFromJSON(JSONObject obj) throws JSONException;
	public abstract JSONObject getJSONObject() throws JSONException;
	public abstract String getFileName();
}
