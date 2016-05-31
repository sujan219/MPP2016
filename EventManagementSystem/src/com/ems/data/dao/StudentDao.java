package com.ems.data.dao;

import org.json.JSONArray;
import org.json.JSONObject;

import com.ems.baseclasses.Student;
import com.ems.data.DataSaver;

public class StudentDao implements DaoInterface{
	private Student std;
	public static final String KEY_ID = "Id";
	public static final String KEY_NAME = "Name";
	public static final String KEY_EMAIL = "Email";
	public static final String KEY_ENTRY = "Entry";
	public static final String FILE_NAME = "student.json";
	
	public StudentDao(Object obj){
		std = (Student) obj;
	}
	public StudentDao(){}
	
	@Override
	public void saveNewRecord() throws DataSaveException {
		try {
			JSONObject obj = new JSONObject();
			obj.put(KEY_ID, std.getId());
			obj.put(KEY_NAME, std.getName());
			obj.put(KEY_EMAIL, std.getEmail());
			obj.put(KEY_ENTRY, std.getEntry());
			DataSaver.writeData(FILE_NAME, obj, true);
		}catch(Exception e){
			e.printStackTrace();
			throw new DataSaveException();
		}
	}

	@Override
	public void modifyRecord() {
		
	}

	@Override
	public void deleteRecord() {
		
	}

	@Override
	public JSONObject getRecordById(int id) throws DataReadException {
		return null;
	}

	@Override
	public JSONArray getAllRecords() throws DataReadException {
		return DataSaver.readAllData(FILE_NAME);
	}

}
