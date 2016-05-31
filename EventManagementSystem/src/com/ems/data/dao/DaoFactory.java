package com.ems.data.dao;

import com.ems.baseclasses.Student;

public class DaoFactory {
	public static DaoInterface getDaoInterface(Object obj){
		if(obj instanceof Student){
			return new StudentDao(obj);
		}
		return null;
	}
}
