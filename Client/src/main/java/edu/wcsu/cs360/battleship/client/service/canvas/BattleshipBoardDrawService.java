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
public class BattleshipBoardDrawService {
	
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
	 * Whether or not to center the board being drawn in the {@link BattleshipBoardDrawService#pane}
	 */
	private boolean centerBoard;
	private Pane pane;
	
	private BattleshipBoardDrawService() {
		centerBoard = true;
		gridImageList = null;
	}
	
	public BattleshipBoardDrawService(Pane pane, Image emptyImage, Image shipImage, Image hitImage, Image missImage) {
		this();
		this.pane = pane;
		this.emptyImage = emptyImage;
		this.shipImage = shipImage;
		this.hitImage = hitImage;
		this.missImage = missImage;
	}
	
	public BattleshipBoardDrawService(Pane pane, URL emptyImageUrl, URL shipImageUrl, URL hitImageUrl, URL missImageUrl) {
		this();
		this.pane = pane;
		this.emptyImage = new Image(emptyImageUrl.toString());
		this.shipImage = new Image(shipImageUrl.toString());
		this.hitImage = new Image(hitImageUrl.toString());
		this.missImage = new Image(missImageUrl.toString());
	}
	
	//region Drawing operations
	
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
					if (i < gridImageList.size() && o < gridImageList.get(i).size())
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
	
	//endregion
	
	//region Vertical/Horizontal calculations
	
	private boolean isCoordinateXAndCoordinateYInGrid(int x, int y) {
		return ((x - getHorizontalOffset()) < (getNumberOfHorizontalBoxes() * GRID_BOX_MIN_WIDTH_PIXELS) && (y - getVerticalOffset()) < (getNumberOfVerticalBoxes() * GRID_BOX_MIN_HEIGHT_PIXELS));
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
	
	public int getNumberOfHorizontalBoxes() {
		return (int) pane.getWidth() / GRID_BOX_MIN_WIDTH_PIXELS;
	}
	
	public int getNumberOfVerticalBoxes() {
		return (int) pane.getHeight() / GRID_BOX_MIN_HEIGHT_PIXELS;
	}
	
	private int getHorizontalGridLocationFromX(int x) {
		return (x - getHorizontalOffset()) / GRID_BOX_MIN_WIDTH_PIXELS;
	}
	
	private int getVerticalGridLocationFromY(int y) {
		return (y - getVerticalOffset()) / GRID_BOX_MIN_HEIGHT_PIXELS;
	}
	
	//endregion
	
	//region setCursorLocation
	
	public void setCursorLocationToEmpty(int x, int y) {
		if (isCoordinateXAndCoordinateYInGrid(x, y))
			gridImageList.get(getHorizontalGridLocationFromX(x)).set(getVerticalGridLocationFromY(y), emptyImage);
	}
	
	public void setCursorLocationToShip(int x, int y) {
		if (isCoordinateXAndCoordinateYInGrid(x, y))
			gridImageList.get(getHorizontalGridLocationFromX(x)).set(getVerticalGridLocationFromY(y), shipImage);
	}
	
	public void setCursorLocationToHit(int x, int y) {
		if (isCoordinateXAndCoordinateYInGrid(x, y))
			gridImageList.get(getHorizontalGridLocationFromX(x)).set(getVerticalGridLocationFromY(y), hitImage);
	}
	 
	public void setCursorLocationToMiss(int x, int y) {
		if (isCoordinateXAndCoordinateYInGrid(x, y))
			gridImageList.get(getHorizontalGridLocationFromX(x)).set(getVerticalGridLocationFromY(y), missImage);
	}
	
	//endregion
	
	//region getTotal
	
	private int getTotalOfImageInGridImageList(final Image image) {
		int total = 0;
		for (int i = 0; i < gridImageList.size(); i++) {
			for (int o = 0; o < gridImageList.get(i).size(); o++) {
				if (gridImageList.get(i).get(o) == image) //Using a reference comparison should be ok; image location never changed
					total++;
			}
		}
		return total;
	}
	
	public int getTotalShipImages() {
		return getTotalOfImageInGridImageList(shipImage);
	}
	
	public int getTotalEmptyImages() {
		return getTotalOfImageInGridImageList(shipImage);
	}
	
	public int getTotalHitImages() {
		return getTotalOfImageInGridImageList(shipImage);
	}
	
	public int getTotalMissImages() {
		return getTotalOfImageInGridImageList(shipImage);
	}
	
	//endregion
	
	//region isCursorLocationAImage
	
	private boolean isCursorLocationAImage(int x, int y, final Image image) {
		return isCoordinateXAndCoordinateYInGrid(x, y) && (gridImageList.get(getHorizontalGridLocationFromX(x)).get(getVerticalGridLocationFromY(y)) == image);
	}
	
	public boolean isCursorLocationAShipImage(int x, int y) {
		return isCursorLocationAImage(x, y, shipImage);
	}
	
	public boolean isCursorLocationAEmptyImage(int x, int y) {
		return isCursorLocationAImage(x, y, emptyImage);
	}
	
	public boolean isCursorLocationAHitImage(int x, int y) {
		return isCursorLocationAImage(x, y, hitImage);
	}
	
	public boolean isCursorLocationAMissImage(int x, int y) {
		return isCursorLocationAImage(x, y, missImage);
	}
	
	//endregion
	
}