package edu.wcsu.cs360.battleship.client.service.canvas;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 * Creates a Game board using {@link javafx.scene.canvas.Canvas}
 */
public class BoardDrawService {
	
	private Pane pane;
	
	private BoardDrawService() {
		
	}
	
	public BoardDrawService(Pane pane) {
		this.pane = pane;
	}
	
	public void draw() {
		Circle circle = new Circle(50, Color.BLUE);
		circle.relocate(20, 20);
		Rectangle rectangle = new Rectangle(100, 100, Color.RED);
		rectangle.relocate(70, 70);
		pane.getChildren().addAll(circle, rectangle);
	}
	
}