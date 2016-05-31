package com.ems.data.dao;

import org.json.JSONArray;
import org.json.JSONObject;

public interface DaoInterface {
	public void saveNewRecord() throws DataSaveException;
	public void modifyRecord() throws DataSaveException;
	public void deleteRecord() throws DataSaveException;
	public JSONObject getRecordById(int id) throws DataReadException;
	public JSONArray getAllRecords() throws DataReadException;
}
