import javafx.scene.canvas.*;
import javafx.scene.paint.Color;

public class PuzzleCanvas extends Canvas {
	GraphicsContext gc = getGraphicsContext2D();
	private final int SIZE = 80;
	int[][] field;
	double XX = -1000, YY = -1000;
	Color[] stoneColor = {
			Color.YELLOW,
			Color.GRAY,
			Color.BLUE,
			Color.RED,
			Color.GREEN,
			Color.PINK
		};
	
	PuzzleCanvas() {
		super(480, 500);
		field = new int[5][6];
		
		for (int y = 0; y < field.length; y++)
		for (int x = 0; x < field[0].length; x++) {
			// make field
			field[y][x] = (x + y) % 6;
		}
		paint();
	}
	
	void paint() {
		// set BackGround
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		for (int y = 0; y < field.length; y++)
		for (int x = 0; x < field[0].length; x++) {
			if (y == (int)YY / SIZE && x == (int)XX / SIZE) continue;
			gc.setFill(stoneColor[field[y][x]]);
			gc.fillOval(x * SIZE, y * SIZE, SIZE, SIZE);
		}
		if (YY <= -1 || XX <= -1) return;
		gc.setFill(stoneColor[field[(int)YY/SIZE][(int)XX/SIZE]]);
		gc.fillOval(XX-SIZE/2, YY-SIZE/2, SIZE, SIZE);
	}
}