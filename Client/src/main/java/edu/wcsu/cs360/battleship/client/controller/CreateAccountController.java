package edu.wcsu.cs360.battleship.client.controller;

import edu.wcsu.cs360.battleship.client.service.ServerConnectionHandlerService;
import edu.wcsu.cs360.battleship.client.utility.validation.TextInputValidationUtility;
import edu.wcsu.cs360.battleship.common.domain.entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateAccountController implements Initializable {
	
	private Log log = LogFactory.getLog(this.getClass());
	@Inject
	private ServerConnectionHandlerService serverConnectionHandlerService;
	
	public CreateAccountController() {
	}
	
	//region Event Handlers
	
	public void onCreateAccountButtonClick(ActionEvent actionEvent) {
		if (!validateForm())
			return;
		User user = getUserFromForm();
		
	}
	
	public void onCancelButtonClick(ActionEvent actionEvent) {
		Stage stage = (Stage)cancelButton.getScene().getWindow();
		stage.close();
	}
	
	//endregion
	
	//region Common Methods
	
	private boolean validateForm() {
		boolean valid = true;
		if (TextInputValidationUtility.hasTextOrApplyCss(usernameTextField, passwordPasswordField, firstNameTextField).size() != 0)
			valid = false;
		if (!TextInputValidationUtility.hasEmailAddressOrApplyCss(emailTextField))
			valid = false;
		return valid;
	}
	
	private User getUserFromForm() {
		User user = new User();
		user.setUsername(usernameTextField.getText());
		user.setPassword(passwordPasswordField.getText());
		user.setFirstName(firstNameTextField.getText());
		user.setMiddleName(middleNameTextField.getText());
		user.setLastName(lastNameTextField.getText());
		user.setEmail(emailTextField.getText());
		return user;
	}
	
	//endregion
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		log.info("Initializing " + getClass());
	}
	
	@FXML
	private Button createAccountButton;
	@FXML
	private Button cancelButton;
	@FXML
	private TextField usernameTextField;
	@FXML
	private TextField firstNameTextField;
	@FXML
	private TextField lastNameTextField;
	@FXML
	private TextField middleNameTextField;
	@FXML
	private TextField emailTextField;
	@FXML
	private PasswordField passwordPasswordField;
	
}
