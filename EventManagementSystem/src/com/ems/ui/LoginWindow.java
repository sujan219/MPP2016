package com.ems.ui;

import com.ems.baseclasses.UserLogin;
import com.ems.data.dao.UserLoginDao;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginWindow extends Application {
	public static void main(String[] args)
	{
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
		stage.setTitle("Login");
		stage.setScene(new Scene(root));
		stage.show();
		
		TextField userName = (TextField)root.lookup("#txtUsername");
		PasswordField password = (PasswordField)root.lookup("#txtPassword");
		Label message = (Label)root.lookup("#lblMessage");
		Button btnLogin = (Button)root.lookup("#btnLogin");
		btnLogin.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				if(userName.getText().equals("")) {
					message.setText("Please enter a valid Username");
					return;
				}
				if(password.getText().equals("")) {
					message.setText("Password cannot be empty");
					return;
				}
				UserLogin user = new UserLogin(userName.getText(), password.getText());
				UserLoginDao loginDao = new UserLoginDao(user);
				System.out.println(loginDao.authUser());
				if(loginDao.authUser())	{
					new MainWindow(stage);
					stage.hide();
				}
				else {
					message.setText("Username or Password mismatch");
				}
			}
		});
	}
}
