package edu.wcsu.cs360.battleship.client.controller;

import edu.wcsu.cs360.battleship.client.utility.general.ViewUtility;
import edu.wcsu.cs360.battleship.client.utility.notification.AlertUtility;
import edu.wcsu.cs360.battleship.client.view.AboutView;
import edu.wcsu.cs360.battleship.client.view.BoardView;
import edu.wcsu.cs360.battleship.client.view.CreateAccountView;
import edu.wcsu.cs360.battleship.common.domain.entity.User;
import edu.wcsu.cs360.battleship.common.domain.session.ApplicationSession;
import edu.wcsu.cs360.battleship.common.repository.UserFutureRepository;
import edu.wcsu.cs360.battleship.common.service.serialize.ObjectMapperClassCastService;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdeferred.DeferredManager;
import org.jdeferred.impl.DefaultDeferredManager;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for the {@link edu.wcsu.cs360.battleship.client.view.LoginView}
 */
public class LoginController implements Initializable {
	
	private Log log = LogFactory.getLog(this.getClass());
	@Inject
	private ApplicationSession applicationSession;
	@Inject
	private UserFutureRepository userFutureRepository;
	
	public LoginController() {
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		log.info("Initializing " + getClass());
	}
	
	/**
	 * Shows the {@link AboutView}
	 *
	 * @param actionEvent Action Event
	 */
	public void onHelpMenuAboutClick(ActionEvent actionEvent) {
		ViewUtility.onTop(new AboutView());
	}
	
	/**
	 * Quits the application
	 *
	 * @param actionEvent Action Event
	 */
	public void onFileMenuCloseClick(ActionEvent actionEvent) {
		((Stage) (menuBar).getScene().getWindow()).close();
	}
	
	/**
	 * Attempts to lo in the specified user. If the user provided valid credentials, the the user is brought to the
	 * {@link BoardView}. Otherwise, the user is prompted that they enter invalid credentials and they are required
	 * to attempt to log in again.
	 *
	 * @param actionEvent Action Event
	 */
	public void onLoginButtonClick(ActionEvent actionEvent) {
		DeferredManager deferredManager = new DefaultDeferredManager();
		loginButton.setDisable(true);
		deferredManager.when(userFutureRepository.findOneByUsernameAndPassword(usernameTextField.getText(), passwordPasswordField.getText()))
				.done(result -> {
					if (AlertUtility.alertIfHasError(result))
						return;
					if (result.getBody() == null)
						AlertUtility.alert("Invalid Credentials", Alert.AlertType.WARNING);
					else {
						Platform.runLater(() -> {
							ObjectMapperClassCastService objectMapperClassCastService = new ObjectMapperClassCastService();
							applicationSession.setUser(objectMapperClassCastService.cast(result.getBody(), User.class));
							ViewUtility.replace(new BoardView(), ((Stage) (loginButton).getScene().getWindow()));
						});
					}
				})
				.fail(AlertUtility::alert)
				.always((state, resolved, rejected) -> {
					Platform.runLater(() -> {
						loginButton.setDisable(false);
					});
				});
		
	}
	
	public void onCreateAccountButtonClick(ActionEvent actionEvent) {
		ViewUtility.replace(new CreateAccountView(), ((Stage) (createAccountButton).getScene().getWindow()));
	}
	
	/**
	 * Checks if the {@link LoginController#usernameTextField} and {@link LoginController#passwordPasswordField} text
	 * have lengths greater than zero. If the lengths are greater than zero, then the Login button is enabled.
	 * Otherwise, the login button is disabled.
	 *
	 * @param keyEvent Key Event
	 */
	public void inputFieldValueChanged(KeyEvent keyEvent) {
		if (passwordPasswordField.getText() == null || passwordPasswordField.getText().length() <= 0)
			loginButton.setDisable(true);
		else if (usernameTextField.getText() == null || usernameTextField.getText().length() <= 0)
			loginButton.setDisable(true);
		else {
			loginButton.setDisable(false);
			if (keyEvent.getCode() == KeyCode.ENTER)
				onLoginButtonClick(new ActionEvent());
		}
	}
	
	@FXML
	private MenuBar menuBar;
	@FXML
	private MenuItem fileMenuClose;
	@FXML
	private TextField usernameTextField;
	@FXML
	private PasswordField passwordPasswordField;
	@FXML
	private Button loginButton;
	@FXML
	private Button createAccountButton;
	
}
