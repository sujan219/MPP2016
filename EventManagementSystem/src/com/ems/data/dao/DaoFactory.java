package com.ems.data.dao;

import com.ems.baseclasses.OnCampusEvent;
import com.ems.baseclasses.Personnel;
import com.ems.baseclasses.Resource;
import com.ems.baseclasses.Student;
import com.ems.baseclasses.Transport;

public class DaoFactory {
	public static AbstractDao getDaoInterface(Object obj){
		if(obj instanceof Student){
			return new StudentDao(obj);
		}else if(obj instanceof Personnel){
			return new PersonnelDao(obj);
		}else if(obj instanceof Resource){
			return new ResourceDao(obj);
		}else if(obj instanceof Transport){
			return new TransportDao(obj);
		}else if(obj instanceof OnCampusEvent){
			return new OnCampusEventDao(obj);
		}else if(obj instanceof OnCampusEvent){
			return new OffCampusEventDao(obj);
		}
		return null;
	}
}
