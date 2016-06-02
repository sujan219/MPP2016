package com.ems.data.dao;

import com.ems.baseclasses.Event;
import com.ems.baseclasses.Expense;
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
		}else if(obj instanceof Event){
			return new EventDao(obj);
		}else if(obj instanceof Expense){
			return new ExpenseDao(obj);
		}
		return null;
	}

	public static AbstractDao getDaoInterface(String daoType){
		if(daoType.equalsIgnoreCase("student")){
			return new StudentDao();
		}else if(daoType.equalsIgnoreCase("personnel")){
			return new PersonnelDao();
		}else if(daoType.equalsIgnoreCase("resource")){
			return new ResourceDao();
		}else if(daoType.equalsIgnoreCase("transport")){
			return new TransportDao();
		}else if(daoType.equalsIgnoreCase("expense")){
			return new ExpenseDao();
		}
		return null;
	}
}