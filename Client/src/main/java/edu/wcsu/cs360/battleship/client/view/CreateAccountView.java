package edu.wcsu.cs360.battleship.client.view;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CreateAccountView extends FXMLFilePathView {
	
	private Log log = LogFactory.getLog(this.getClass());
	private static final String FXML_FILE_PATH = "/views/create-account.fxml";
	
	public CreateAccountView() {
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getFXMLFilePath() {
		return FXML_FILE_PATH;
	}
	
}
