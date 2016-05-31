package com.ems.ui;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public abstract class ModalDialog extends Stage{
	public ModalDialog(){
		initStyle(StageStyle.UTILITY);
		initModality(Modality.APPLICATION_MODAL);
	}
}
