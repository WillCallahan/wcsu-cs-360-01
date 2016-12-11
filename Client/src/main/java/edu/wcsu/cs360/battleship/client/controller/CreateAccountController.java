package edu.wcsu.cs360.battleship.client.controller;

import edu.wcsu.cs360.battleship.client.utility.general.ViewUtility;
import edu.wcsu.cs360.battleship.client.utility.notification.AlertUtility;
import edu.wcsu.cs360.battleship.client.utility.validation.TextInputValidationUtility;
import edu.wcsu.cs360.battleship.client.view.BoardView;
import edu.wcsu.cs360.battleship.client.view.LoginView;
import edu.wcsu.cs360.battleship.common.domain.entity.User;
import edu.wcsu.cs360.battleship.common.domain.session.ApplicationSession;
import edu.wcsu.cs360.battleship.common.repository.IUserFutureRepository;
import edu.wcsu.cs360.battleship.common.service.serialize.IClassCastService;
import edu.wcsu.cs360.battleship.common.service.serialize.ObjectMapperClassCastService;
import javafx.application.Platform;
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
 * Controller for the {@link edu.wcsu.cs360.battleship.client.view.CreateAccountView}
 */
public class CreateAccountController implements Initializable {
	
	private Log log = LogFactory.getLog(this.getClass());
	@Inject
	private ApplicationSession applicationSession;
	@Inject
	private IUserFutureRepository iUserFutureRepository;
	
	public CreateAccountController() {
	}
	
	//region Event Handlers
	
	@SuppressWarnings("unchecked")
	public void onCreateAccountButtonClick(ActionEvent actionEvent) {
		if (!validateForm())
			return;
		createAccountButton.setDisable(true);
		DeferredManager deferredManager = new DefaultDeferredManager();
		deferredManager.when(iUserFutureRepository.findOneByUsername(usernameTextField.getText()))
				.done(result -> {
					if (AlertUtility.alertIfHasError(result))
						return;
					if (result.getBody() != null) {
						Platform.runLater(() -> {
							Alert alert = new Alert(Alert.AlertType.WARNING);
							alert.setTitle("Username Exists!");
							alert.setHeaderText("Username already exists");
							alert.setContentText("Please try another username.");
							alert.show();
							createAccountButton.setDisable(false);
						});
					} else {
						deferredManager.when(iUserFutureRepository.save(getUserFromForm()))
								.done(resultChild -> {
									AlertUtility.alertIfHasError(result);
									log.info("Successfully logged in user " + resultChild.getBody());
									Platform.runLater(() -> {
										IClassCastService iClassCastService = new ObjectMapperClassCastService();
										applicationSession.setUser(iClassCastService.cast(resultChild.getBody(), User.class));
										ViewUtility.replace(new BoardView(), (Stage) createAccountButton.getScene().getWindow());
									});
								})
								.fail(AlertUtility::alert)
								.always((state, resolved, rejected) -> Platform.runLater(() -> createAccountButton.setDisable(false)));
					}
				})
				.fail(AlertUtility::alert);
	}

	
	public void onCancelButtonClick(ActionEvent actionEvent) {
		ViewUtility.replace(new LoginView(), ((Stage) (cancelButton).getScene().getWindow()));
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
