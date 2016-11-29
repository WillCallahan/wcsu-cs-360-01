package edu.wcsu.cs360.battleship.client.view;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BoardView extends FXMLFilePathView {
	
	private Log log = LogFactory.getLog(this.getClass());
	private static final String FXML_FILE_PATH = "/views/board.fxml";
	
	public BoardView() {
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getFXMLFilePath() {
		return FXML_FILE_PATH;
	}
	
}
