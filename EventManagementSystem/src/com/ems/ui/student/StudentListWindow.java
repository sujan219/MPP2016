package com.ems.ui.student;

import java.util.Arrays;
import java.util.List;

import com.ems.baseclasses.DataObject;
import com.ems.baseclasses.Student;
import com.ems.data.dao.DataReadException;
import com.ems.data.dao.StudentDao;
import com.ems.ui.AddActionWindow;
import com.ems.ui.ListWindow;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StudentListWindow extends ListWindow{

	private static final String TITLE = "Student";
	public StudentListWindow() {
		super(TITLE);
	}

	@Override
	protected List<String> getColumns() {
		return Arrays.asList("Id", "Name", "Email", "Entry");
	}
	
	@Override
	protected ObservableList<ObservableList<String>> getTableContent() throws DataReadException {
		try{
			StudentDao sDao = new StudentDao();
			List<DataObject> dataList = sDao.getAllRecords();
			ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
			for(DataObject eachDataObject:dataList){
				Student eachStudent = (Student) eachDataObject;
				ObservableList<String> row = FXCollections.observableArrayList();
				row.add(eachStudent.getId() + "");
				row.add(eachStudent.getName());
				row.add(eachStudent.getEmail());
				row.add(eachStudent.getEntry());
				data.add(row);
			}
			return data;
		}catch(Exception e){
			e.printStackTrace();
			throw new DataReadException();
		}
	}

	@Override
	protected AddActionWindow getAddActionWindow(int id) {
		return new StudentAddWindow(id, this);
	}

	@Override
	protected DataObject getDataObjectById(int id) {
		return new Student(id, null, null, null);
	}
}
