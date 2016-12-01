package edu.wcsu.cs360.battleship.client.utility.notification;

import edu.wcsu.cs360.battleship.common.domain.socket.Response;
import edu.wcsu.cs360.battleship.common.utility.ResponseUtility;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AlertUtility {
	
	private static Log log = LogFactory.getLog(AlertUtility.class);
	
	//region General Alert
	
	/**
	 * Shows an {@link Alert} modal
	 *
	 * @param headerText  {@link Alert#setHeaderText(String)}
	 * @param contentText {@link Alert#setContentText(String)}
	 * @param alertType   {@link Alert#setAlertType(Alert.AlertType)}
	 */
	public static void alert(String headerText, String contentText, Alert.AlertType alertType) {
		Platform.runLater(() -> {
			Alert alert = new Alert(alertType);
			alert.setTitle("Error");
			alert.setHeaderText(headerText);
			alert.setContentText(contentText);
			alert.show();
		});
	}
	
	/**
	 * Alerts the user
	 *
	 * @param headerText {@link Alert#setHeaderText(String)}
	 * @param alertType  {@link Alert#setAlertType(Alert.AlertType)}
	 * @see AlertUtility#alert(String, String, Alert.AlertType)
	 */
	public static void alert(String headerText, Alert.AlertType alertType) {
		alert(headerText, null, alertType);
	}
	
	/**
	 * Alerts the user with an {@link javafx.scene.control.Alert.AlertType#ERROR}
	 *
	 * @param headerText  {@link Alert#setHeaderText(String)}
	 * @param contentText {@link Alert#setContentText(String)}
	 * @see AlertUtility#alert(String, String, Alert.AlertType)
	 */
	public static void alert(String headerText, String contentText) {
		alert(headerText, contentText, Alert.AlertType.ERROR);
	}
	
	/**
	 * Alerts the user of a {@link Throwable}
	 *
	 * @param e Exception to get details from
	 * @see AlertUtility#alert(String, Alert.AlertType)
	 */
	public static void alert(Throwable e) {
		log.error(e);
		alert(e.getMessage(), Alert.AlertType.ERROR);
	}
	
	//endregion
	
	//region Response Alerts
	
	public static boolean alertIfHasError(Response response, String headerText) {
		if (ResponseUtility.hasError(response)) {
			alert(headerText, response.getMessage());
			return true;
		}
		return false;
	}
	
	public static boolean alertIfHasError(Response response) {
		return alertIfHasError(response, "An unexpected error occurred");
	}
	
	//endregion
	
}
