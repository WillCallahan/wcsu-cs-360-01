package edu.wcsu.cs360.battleship.client.service.canvas;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 * Creates a Game board using {@link javafx.scene.canvas.Canvas}
 */
public class BoardDrawService {
	
	private static final int GRID_BOX_MIN_HEIGHT_PIXELS = 50;
	private static final int GRID_BOX_MIN_WIDTH_PIXELS = 50;
	private static final int GRID_LINE_WIDTH_PIXELS = 1;
	private static final Color GRID_COLOR = Color.BLACK;
	private static final Color GRID_FILL_COLOR = Color.TRANSPARENT;
	
	/**
	 * Whether or not to center the board being drawn in the {@link BoardDrawService#pane}
	 */
	private boolean centerBoard;
	private Pane pane;
	
	private BoardDrawService() {
		centerBoard = true;
	}
	
	public BoardDrawService(Pane pane) {
		this();
		this.pane = pane;
	}
	
	public void draw() {
		pane.getChildren().clear();
		drawGrid();
	}
	
	private void drawGrid() {
		int numberOfHorizontalBoxes = (int) pane.getWidth() / GRID_BOX_MIN_WIDTH_PIXELS;
		int numberOfVerticalBoxes = (int) pane.getHeight() / GRID_BOX_MIN_HEIGHT_PIXELS;
		int horizontalOffset = 0;
		int verticalOffset = 0;
		if (centerBoard) {
			horizontalOffset = (int) (pane.getWidth() % GRID_BOX_MIN_WIDTH_PIXELS) / 2;
			verticalOffset = (int) (pane.getHeight() % GRID_BOX_MIN_HEIGHT_PIXELS) / 2;
		}
		for (int i = 0; i < numberOfHorizontalBoxes; i++) {
			for (int o = 0; o < numberOfVerticalBoxes; o++) {
				Rectangle rectangle = new Rectangle(GRID_BOX_MIN_WIDTH_PIXELS, GRID_BOX_MIN_HEIGHT_PIXELS);
				rectangle.setFill(GRID_FILL_COLOR);
				rectangle.setStroke(GRID_COLOR);
				rectangle.setStrokeWidth(GRID_LINE_WIDTH_PIXELS);
				rectangle.relocate((i * GRID_BOX_MIN_WIDTH_PIXELS) + horizontalOffset, (o * GRID_BOX_MIN_HEIGHT_PIXELS) + verticalOffset);
				pane.getChildren().add(rectangle);
			}
		}
	}
	
}