package edu.wcsu.cs360.battleship.client.controller;

import edu.wcsu.cs360.battleship.client.repository.UserFutureRepository;
import edu.wcsu.cs360.battleship.client.view.BoardView;
import edu.wcsu.cs360.battleship.client.view.CreateAccountView;
import edu.wcsu.cs360.battleship.common.domain.entity.User;
import edu.wcsu.cs360.battleship.common.service.serialize.ObjectMapperClassCastService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdeferred.DeferredManager;
import org.jdeferred.impl.DefaultDeferredManager;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

	private Log log = LogFactory.getLog(this.getClass());
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

	public void onHelpMenuAboutClick(ActionEvent actionEvent) throws InterruptedException {

	}

	public void onFileMenuCloseClick(ActionEvent actionEvent) {
		((Stage) (menuBar).getScene().getWindow()).close();
	}

	public void onLoginButtonClick(ActionEvent actionEvent) {
		User user = new User(usernameTextField.getText(), passwordPasswordField.getText());
		DeferredManager deferredManager = new DefaultDeferredManager();
		loginButton.setDisable(true);
		final Stage stage = new Stage();
		final BoardView boardView = new BoardView();
		final Scene scene = new Scene(boardView.getView());
		stage.setScene(scene);
		deferredManager.when(userFutureRepository.findOneByUsernameAndPassword(user))
				.done(result -> {
					ObjectMapperClassCastService objectMapperClassCastService = new ObjectMapperClassCastService();
					log.info("Result: " + objectMapperClassCastService.cast(result.getBody(), User.class));
				})
				.fail(result -> {
					log.error("Failed to get user!", result);
				})
				.always((state, resolved, rejected) -> {
					loginButton.setDisable(false);
				});

	}

	public void onCreateAccountButtonClick(ActionEvent actionEvent) {
		Stage stage = new Stage();
		CreateAccountView createAccountView = new CreateAccountView();
		Scene scene = new Scene(createAccountView.getView());
		stage.setScene(scene);
		stage.show();
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
		else
			loginButton.setDisable(false);
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
