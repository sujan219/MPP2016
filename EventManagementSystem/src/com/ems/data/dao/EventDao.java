package com.ems.data.dao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ems.baseclasses.DataObject;
import com.ems.baseclasses.Event;
import com.ems.baseclasses.OffCampusEvent;
import com.ems.baseclasses.OnCampusEvent;
import com.ems.baseclasses.Personnel;
import com.ems.baseclasses.Resource;
import com.ems.baseclasses.Student;
import com.ems.baseclasses.Transport;
import com.ems.ui.DialogUtil;
import com.ems.ui.event.EventWindow;

public class EventDao extends AbstractDao {

	protected Event event;
	private static final String FILE_NAME = "event.json";
	
	public static final String KEY_ID = "Id";
	public static final String KEY_NAME = "Name";
	public static final String KEY_DESCRIPTION = "Description";
	public static final String KEY_START = "Start";
	public static final String KEY_END = "End";
	public static final String KEY_TYPE = "Type";
	public static final String KEY_LOCATION = "Location";
	public static final String KEY_FUND = "Fund";
	
	public static final String KEY_MANAGERS = "Managers";
	public static final String KEY_RESOURCES = "Resources";
	public static final String KEY_STUDENTS = "Students";
	public static final String KEY_EXPENSES = "Expenses";
	public static final String KEY_TRANSPORTS = "Transports";
	
	public EventDao(Object event){
		super((Event)event);
		this.event = (Event)event;
	}
	
	public EventDao(){}
	
	@Override
	public DataObject getObjectFromJSON(JSONObject obj) throws JSONException {
		int id = obj.getInt(KEY_ID);
		String name = obj.getString(KEY_NAME);
		String description = obj.getString(KEY_DESCRIPTION);
		Calendar start = Calendar.getInstance();
		Calendar end = Calendar.getInstance();
		try{
			start.setTime(Event.dateFormat.parse(obj.getString(KEY_START)));
			end.setTime(Event.dateFormat.parse(obj.getString(KEY_END)));
		}catch(ParseException e){
			e.printStackTrace();
			DialogUtil.showErrorDialog(e.getMessage());
		}
		String type = obj.getString(KEY_TYPE);
		String location = obj.getString(KEY_LOCATION);
		double fund = obj.getDouble(KEY_FUND);
		
		List<Personnel> managerList = getListFromJSONArray(new PersonnelDao(), obj.getJSONArray(KEY_MANAGERS));
		List<Resource> resourceList = getListFromJSONArray(new ResourceDao(), obj.getJSONArray(KEY_RESOURCES));
		List<Student> studentList = getListFromJSONArray(new StudentDao(), obj.getJSONArray(KEY_STUDENTS));
		List<Transport> transportList = getListFromJSONArray(new TransportDao(), obj.getJSONArray(KEY_TRANSPORTS));
		
		//List<Expense> managerList = getListFromJSONArray(new PersonnelDao(), obj.getJSONArray(KEY_MANAGERS));
		
		Event event = null;
		if(type.equals(EventWindow.ON_CAMPUS)){
			event =  new OnCampusEvent(id, name, description, start, end, location, fund);
		}else{
			OffCampusEvent tempEvent = new OffCampusEvent(id, name, location, description, start, end, location, fund);
			tempEvent.setTransportList(transportList);
			event = tempEvent;
		}
		
		event.setEventManagerList(managerList);
		event.setResourceList(resourceList);
		event.setStudentList(studentList);
		
		return event;
	}

	@Override
	public JSONObject getJSONObject() throws JSONException {
		JSONObject obj = new JSONObject();
		obj.put(KEY_ID, event.getId());
		obj.put(KEY_NAME, event.getName());
		obj.put(KEY_DESCRIPTION, event.getDescription());
		obj.put(KEY_START, Event.dateFormat.format(event.getStartDateTime().getTime()));
		obj.put(KEY_END, Event.dateFormat.format(event.getEndDateTime().getTime()));
		obj.put(KEY_TYPE, event.getType());
		obj.put(KEY_LOCATION, event.getLocation());
		obj.put(KEY_FUND, event.getFund());
		obj.put(KEY_MANAGERS, getJSONArrayFromList(event.getManagerList()));
		obj.put(KEY_EXPENSES, getJSONArrayFromList(event.getExpenseList()));
		obj.put(KEY_RESOURCES, getJSONArrayFromList(event.getResourceList()));
		obj.put(KEY_STUDENTS, getJSONArrayFromList(event.getStudentList()));
		if(event instanceof OffCampusEvent){
			obj.put(KEY_TRANSPORTS, getJSONArrayFromList(((OffCampusEvent)event).getTransportList()));
		}
		
		return obj;
	}

	@Override
	public String getFileName() {
		return FILE_NAME;
	}

	private <T extends DataObject> JSONArray getJSONArrayFromList(List<T> list) throws JSONException {
		JSONArray array = new JSONArray();
		for(T item:list){
			AbstractDao dao = DaoFactory.getDaoInterface(item);
			array.put(dao.getJSONObject());
		}
		return array;
	}
	
	private <T extends DataObject> List<T> getListFromJSONArray(AbstractDao dao, JSONArray array) throws JSONException {
		List<T> list = new ArrayList<>();
		for(int i=0; i<array.length(); ++i){
			JSONObject jsonObject = array.getJSONObject(i);
			list.add((T) dao.getObjectFromJSON(jsonObject));
		}
		return list;
	}
}
