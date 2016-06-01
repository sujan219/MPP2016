package com.ems.ui.event;

import java.util.List;

import com.ems.baseclasses.DataObject;

public interface DependencyAddedListener {
	public void dependencyAdded(List<DataObject> dataObjList);
}
