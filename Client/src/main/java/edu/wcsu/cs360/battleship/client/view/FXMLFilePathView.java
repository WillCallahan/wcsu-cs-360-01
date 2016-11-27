package edu.wcsu.cs360.battleship.client.view;

import com.airhacks.afterburner.views.FXMLView;

public abstract class FXMLFilePathView extends FXMLView {
	
	public final static String DEFAULT_FXML_FILE_ENDING = ".fxml";
	
	@Override
	protected String getConventionalName(boolean lowercase) {
		String conventionalName = stripEnding(getFXMLFilePath());
		if (lowercase)
			return conventionalName.toLowerCase();
		return conventionalName;
	}
	
	/**
	 * Strips the ".fxml" ending off of a filename if the filename ends in ".fxml"
	 *
	 * @param file Filename
	 * @return Filename stripping of ".fxml" ending
	 */
	private static String stripEnding(String file) {
		if (!file.endsWith(DEFAULT_FXML_FILE_ENDING)) {
			return file;
		}
		int fxmlIndex = file.lastIndexOf(DEFAULT_FXML_FILE_ENDING);
		return file.substring(0, fxmlIndex);
	}
	
	/**
	 * Gets the path the the fxml file to be resolved by the {@link FXMLView}
	 * @return Path to the FXML file related to the {@link FXMLView}
	 */
	protected abstract String getFXMLFilePath();
	
}
