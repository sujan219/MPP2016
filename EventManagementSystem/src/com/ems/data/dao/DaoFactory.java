package com.ems.data.dao;

import com.ems.baseclasses.Personnel;
import com.ems.baseclasses.Student;

public class DaoFactory {
	public static AbstractDao getDaoInterface(Object obj){
		if(obj instanceof Student){
			return new StudentDao(obj);
		}else if(obj instanceof Personnel){
			return new PersonnelDao(obj);
		}
		return null;
	}
}
