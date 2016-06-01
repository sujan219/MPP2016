package com.ems.data.dao;

import org.json.JSONException;
import org.json.JSONObject;

import com.ems.baseclasses.DataObject;
import com.ems.baseclasses.Student;

public class StudentDao extends AbstractDao{
	private Student std;
	public static final String KEY_ID = "Id";
	public static final String KEY_NAME = "Name";
	public static final String KEY_EMAIL = "Email";
	public static final String KEY_ENTRY = "Entry";
	public static final String FILE_NAME = "student.json";
	
	public StudentDao(Object obj){
		super((Student)obj);
		std = (Student) obj;
	}
	public StudentDao(){}
	
	@Override
	public DataObject getObjectFromJSON(JSONObject obj) throws JSONException {
		int id = obj.getInt(KEY_ID);
		String name = obj.getString(KEY_NAME);
		String email = obj.getString(KEY_EMAIL);
		String entry = obj.getString(KEY_ENTRY);
		return new Student(id, name, email, entry);
	}
	
	@Override
	public String getFileName() {
		return FILE_NAME;
	}
	
	@Override
	public JSONObject getJSONObject() throws JSONException {
		JSONObject obj = new JSONObject();
		obj.put(KEY_ID, std.getId());
		obj.put(KEY_NAME, std.getName());
		obj.put(KEY_EMAIL, std.getEmail());
		obj.put(KEY_ENTRY, std.getEntry());
		return obj;
	}
}
