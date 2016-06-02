package com.ems.data.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.ems.baseclasses.DataObject;
import com.ems.baseclasses.Expense;

public class ExpenseDao extends AbstractDao {
	private Expense expense;
	
	public static final String KEY_NAME = "Name";
	public static final String KEY_COST = "Cost";
	
	public ExpenseDao(Object obj) {
		super((Expense)obj);
		expense = (Expense) obj;
	}
	
	public ExpenseDao(){}
	
	@Override
	public JSONObject getJSONObject() throws JSONException {
		JSONObject obj = new JSONObject();
		obj.put(KEY_NAME, expense.getName());
		obj.put(KEY_COST, expense.getCost());
		return obj;
	}

	@Override
	public String getFileName() {
		return null;
	}

	@Override
	public DataObject getObjectFromJSON(JSONObject obj) throws JSONException {
		String name = obj.getString(KEY_NAME);
		double cost = obj.getDouble(KEY_COST);
		return new Expense(name, cost);
	}
	
	@Override
	public List<DataObject> getAllRecords(Map<String, String> map) throws DataReadException {
		return new ArrayList<DataObject>();
	}
}
