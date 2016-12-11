package edu.wcsu.cs360.battleship.client.controller;

import edu.wcsu.cs360.battleship.client.utility.notification.AlertUtility;
import edu.wcsu.cs360.battleship.client.utility.validation.TextInputValidationUtility;
import edu.wcsu.cs360.battleship.common.domain.entity.User;
import edu.wcsu.cs360.battleship.common.domain.session.ApplicationSession;
import edu.wcsu.cs360.battleship.common.repository.IUserFutureRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdeferred.DeferredManager;
import org.jdeferred.impl.DefaultDeferredManager;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for the {@link edu.wcsu.cs360.battleship.client.view.UserInformationView}
 */
public class UserInformationController implements Initializable {
	
	private Log log = LogFactory.getLog(this.getClass());
	@Inject
	private ApplicationSession applicationSession;
	@Inject
	private IUserFutureRepository iUserFutureRepository;
	
	public UserInformationController() {
	}
	
	//region Event Handlers
	
	@SuppressWarnings("unchecked")
	public void onSaveButtonClick(ActionEvent actionEvent) {
		if (!validateForm())
			return;
		User user = getUserFromForm();
		applicationSession.getUser().setUsername(user.getUsername());
		applicationSession.getUser().setEmail(user.getEmail());
		applicationSession.getUser().setPassword(user.getPassword());
		applicationSession.getUser().setFirstName(user.getFirstName());
		applicationSession.getUser().setMiddleName(user.getMiddleName());
		applicationSession.getUser().setLastName(user.getLastName());
		DeferredManager deferredManager = new DefaultDeferredManager();
		deferredManager.when(iUserFutureRepository.save(applicationSession.getUser()))
				.done(result ->
						AlertUtility.alert("Successfully saved information!", Alert.AlertType.INFORMATION)
				)
				.fail(AlertUtility::alert);
	}
	
	
	public void onCloseButtonClick(ActionEvent actionEvent) {
		Stage stage = (Stage) closeButton.getScene().getWindow();
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
	
	@SuppressWarnings("Duplicates")
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
	
	private void serUserInForm(User user) {
		usernameTextField.setText(user.getUsername());
		passwordPasswordField.setText(user.getPassword());
		firstNameTextField.setText(user.getFirstName());
		middleNameTextField.setText(user.getMiddleName());
		lastNameTextField.setText(user.getLastName());
		emailTextField.setText(user.getEmail());
	}
	
	//endregion
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		log.info("Initializing " + getClass());
		serUserInForm(applicationSession.getUser());
	}
	
	@FXML
	private Button saveButton;
	@FXML
	private Button closeButton;
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
