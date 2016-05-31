package com.test.main;

import com.ems.baseclasses.Personnel;
import com.ems.baseclasses.UserLogin;
import com.ems.data.dao.DaoFactory;
import com.ems.data.dao.DaoInterface;
import com.ems.data.dao.UserLoginDao;

public class Main {
	public static void main(String[] args) throws Exception {		
		UserLogin user = new UserLogin("Sujan", "test1233");
		UserLoginDao loginDao = new UserLoginDao(user);
		System.out.println(loginDao.authUser());
		
		DaoInterface interfaceD = DaoFactory.getDaoInterface(new Personnel(0, "Sujan", "test", true, true));
		if(interfaceD == null){
			System.out.println("null");
		}else{
			interfaceD.saveNewRecord();
		}
	}
}