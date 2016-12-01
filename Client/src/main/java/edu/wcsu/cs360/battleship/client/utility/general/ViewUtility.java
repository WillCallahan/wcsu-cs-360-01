package edu.wcsu.cs360.battleship.client.utility.general;

import com.airhacks.afterburner.views.FXMLView;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

public class ViewUtility {
	
	/**
	 * Replaces the {@link Stage#scene} in the current {@link Stage} with the {@link FXMLView#scene}
	 *
	 * @param fxmlView View to set current {@link Stage#scene} to
	 * @param stage    Stage to modify
	 */
	public static <T extends FXMLView> void replace(T fxmlView, Stage stage) {
		Scene scene = new Scene(fxmlView.getView());
		stage.close();
		stage.setScene(scene);
		stage.setTitle(getTitleFromClassName(fxmlView.getClass()));
		stage.show();
	}
	
	/**
	 * Creates a new {@link Stage} with a {@link Modality} on top of the {@link Window}
	 *
	 * @param fxmlView New View to create
	 * @param window   Window to have below {@link FXMLView}
	 * @param modality Modality of the View
	 * @throws IllegalArgumentException If {@link Modality#WINDOW_MODAL} and {@link Window} is {@code null}
	 */
	public static <T extends FXMLView> void onTop(T fxmlView, Window window, Modality modality) {
		if (modality == Modality.WINDOW_MODAL && window == null)
			throw new IllegalArgumentException();
		Stage stage = new Stage();
		Scene scene = new Scene(fxmlView.getView());
		stage.initModality(modality);
		stage.initOwner(window);
		stage.setScene(scene);
		stage.setTitle(getTitleFromClassName(fxmlView.getClass()));
		stage.show();
	}
	
	/**
	 * Creates a new {@link Stage} with the {@link FXMLView} above the {@link Window}
	 *
	 * @param fxmlView New View to create
	 * @param window   Window to have below {@link FXMLView}
	 * @see ViewUtility#onTop(FXMLView, Window, Modality)
	 */
	public static void onTop(FXMLView fxmlView, Window window) {
		onTop(fxmlView, window, Modality.WINDOW_MODAL);
	}
	
	/**
	 * Creates a new {@link Stage} above all other windows of the application
	 *
	 * @param fxmlView New View to create
	 * @see ViewUtility#onTop(FXMLView, Window, Modality)
	 */
	public static void onTop(FXMLView fxmlView) {
		onTop(fxmlView, null, Modality.APPLICATION_MODAL);
	}
	
	/**
	 * Gets the class name of an {@link FXMLView} for use as a title
	 *
	 * @param fxmlView FXML View to get class name of
	 * @param <T>      Type that extends {@link FXMLView}
	 * @return {@link Class#getSimpleName()} of the {@link FXMLView}
	 */
	public static <T extends FXMLView> String getTitleFromClassName(Class<T> fxmlView) {
		return fxmlView.getSimpleName();
	}
	
}
