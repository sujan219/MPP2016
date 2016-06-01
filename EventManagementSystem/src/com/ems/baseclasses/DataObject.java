package com.ems.baseclasses;

import com.ems.data.dao.AbstractDao;
import com.ems.data.dao.DaoFactory;
import com.ems.data.dao.DataSaveException;

public abstract class DataObject {

	public final void saveNewData() throws DataSaveException {
		AbstractDao dao = DaoFactory.getDaoInterface(this);
		dao.saveNewRecord();
	}

	public final void modifyData() throws DataSaveException {
		AbstractDao dao = DaoFactory.getDaoInterface(this);
		dao.modifyRecord();
	}

	public final void deleteData() throws DataSaveException {
		AbstractDao dao = DaoFactory.getDaoInterface(this);
		dao.deleteRecord();
	}
	
	public abstract int getId();
}
