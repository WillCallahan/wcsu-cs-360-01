package edu.wcsu.cs360.battleship.client.service.canvas;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Creates a Game board using {@link javafx.scene.canvas.Canvas}
 */
public class BoardDrawService {
	
	private static final int GRID_BOX_MIN_HEIGHT_PIXELS = 50;
	private static final int GRID_BOX_MIN_WIDTH_PIXELS = 50;
	private static final int GRID_LINE_WIDTH_PIXELS = 1;
	private static final Color GRID_COLOR = Color.BLACK;
	private static final Color GRID_FILL_COLOR = Color.TRANSPARENT;
	private List<List<Image>> gridImageList;
	private Image emptyImage;
	private Image shipImage;
	private Image hitImage;
	private Image missImage;
	
	/**
	 * Whether or not to center the board being drawn in the {@link BoardDrawService#pane}
	 */
	private boolean centerBoard;
	private Pane pane;
	
	private BoardDrawService() {
		centerBoard = true;
		gridImageList = null;
	}
	
	public BoardDrawService(Pane pane, Image emptyImage, Image shipImage, Image hitImage, Image missImage) {
		this();
		this.pane = pane;
		this.emptyImage = emptyImage;
		this.shipImage = shipImage;
		this.hitImage = hitImage;
		this.missImage = missImage;
	}
	
	public BoardDrawService(Pane pane, URL emptyImageUrl, URL shipImageUrl, URL hitImageUrl, URL missImageUrl) {
		this();
		this.pane = pane;
		this.emptyImage = new Image(emptyImageUrl.toString());
		this.shipImage = new Image(shipImageUrl.toString());
		this.hitImage = new Image(hitImageUrl.toString());
		this.missImage = new Image(missImageUrl.toString());
	}
	
	public void draw() {
		pane.getChildren().clear();
		populateGridImageList();
		drawBackgroundGridImageList();
		drawForegroundGridImageList();
		drawGrid();
	}
	
	private void populateGridImageList() {
		if (gridImageList == null) {
			gridImageList = new ArrayList<>();
			int numberOfHorizontalBoxes = getNumberOfHorizontalBoxes();
			int numberOfVerticalBoxes = getNumberOfVerticalBoxes();
			for (int i = 0; i < numberOfHorizontalBoxes; i++) {
				gridImageList.add(new ArrayList<>());
				for (int o = 0; o < numberOfVerticalBoxes; o++) {
					gridImageList.get(i).add(null);
				}
			}
		} else {
			int numberOfHorizontalBoxes = getNumberOfHorizontalBoxes();
			int numberOfVerticalBoxes = getNumberOfVerticalBoxes();
			List<List<Image>> gridImageListCopy = new ArrayList<>();
			for (int i = 0; i < numberOfHorizontalBoxes; i++) {
				gridImageListCopy.add(new ArrayList<>());
				for (int o = 0; o < numberOfVerticalBoxes; o++) {
					if (i < gridImageList.size() && o < gridImageListCopy.get(i).size())
						gridImageListCopy.get(i).add(gridImageList.get(i).get(o));
					else
						gridImageListCopy.get(i).add(null);
				}
			}
			gridImageList = gridImageListCopy;
		}
	}
	
	private void drawBackgroundGridImageList() {
		int numberOfHorizontalBoxes = getNumberOfHorizontalBoxes();
		int numberOfVerticalBoxes = getNumberOfVerticalBoxes();
		int horizontalOffset = getHorizontalOffset();
		int verticalOffset = getVerticalOffset();
		for (int i = 0; i < numberOfHorizontalBoxes; i++) {
			for (int o = 0; o < numberOfVerticalBoxes; o++) {
				ImageView backGroundImageView = new ImageView(emptyImage);
				backGroundImageView.setFitWidth(GRID_BOX_MIN_WIDTH_PIXELS);
				backGroundImageView.setFitHeight(GRID_BOX_MIN_HEIGHT_PIXELS);
				backGroundImageView.relocate((i * GRID_BOX_MIN_WIDTH_PIXELS) + horizontalOffset, (o * GRID_BOX_MIN_HEIGHT_PIXELS) + verticalOffset);
				pane.getChildren().add(backGroundImageView);
			}
		}
	}
	
	private void drawForegroundGridImageList() {
		int numberOfHorizontalBoxes = getNumberOfHorizontalBoxes();
		int numberOfVerticalBoxes = getNumberOfVerticalBoxes();
		int horizontalOffset = getHorizontalOffset();
		int verticalOffset = getVerticalOffset();
		for (int i = 0; i < numberOfHorizontalBoxes; i++) {
			for (int o = 0; o < numberOfVerticalBoxes; o++) {
				ImageView imageView = new ImageView(gridImageList.get(i).get(o));
				imageView.setFitWidth(GRID_BOX_MIN_WIDTH_PIXELS);
				imageView.setFitHeight(GRID_BOX_MIN_HEIGHT_PIXELS);
				imageView.relocate((i * GRID_BOX_MIN_WIDTH_PIXELS) + horizontalOffset, (o * GRID_BOX_MIN_HEIGHT_PIXELS) + verticalOffset);
				pane.getChildren().add(imageView);
			}
		}
	}
	
	private void drawGrid() {
		int numberOfHorizontalBoxes = getNumberOfHorizontalBoxes();
		int numberOfVerticalBoxes = getNumberOfVerticalBoxes();
		int horizontalOffset = getHorizontalOffset();
		int verticalOffset = getVerticalOffset();
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
	
	private int getHorizontalOffset() {
		if (centerBoard)
			return (int) (pane.getWidth() % GRID_BOX_MIN_WIDTH_PIXELS) / 2;
		return 0;
	}
	
	private int getVerticalOffset() {
		if (centerBoard)
			return (int) (pane.getHeight() % GRID_BOX_MIN_HEIGHT_PIXELS) / 2;
		return 0;
	}
	
	private int getNumberOfHorizontalBoxes() {
		return (int) pane.getWidth() / GRID_BOX_MIN_WIDTH_PIXELS;
	}
	
	private int getNumberOfVerticalBoxes() {
		return (int) pane.getHeight() / GRID_BOX_MIN_HEIGHT_PIXELS;
	}
	
}