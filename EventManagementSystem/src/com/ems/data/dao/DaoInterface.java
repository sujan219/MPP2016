package com.ems.data.dao;

public interface DaoInterface {
	public void saveNewRecord() throws DataSaveException;
	public void modifyRecord() throws DataSaveException;
	public void deleteRecord() throws DataSaveException;
}
