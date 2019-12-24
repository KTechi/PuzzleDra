import javafx.scene.canvas.*;
import javafx.scene.paint.Color;

public class PuzzleCanvas extends Canvas {
	GraphicsContext gc = getGraphicsContext2D();
	private final int SIZE = 80;
	int[][] field;
	double XX = -100, YY = -100;
	boolean isPressing = false;
	
	Color[] stoneColor = {
			Color.YELLOW,
			Color.GRAY,
			Color.BLUE,
			Color.RED,
			Color.GREEN,
			Color.PINK
		};
	
	PuzzleCanvas() {
		super(480, 402);
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
		
		// print Stones
		for (int y = 0; y < field.length; y++)
		for (int x = 0; x < field[0].length; x++) {
			// if the Stone is selected
			int stoneX = (int)XX / SIZE;
			int stoneY = (int)YY / SIZE;
			
			if (y == stoneY && x == stoneX) continue;
			if (y == stoneY && (XX<=1 || SIZE*field[0].length-1<XX)) continue;
//			if (YY<=1 && y==0 && x==stoneX) continue;
//			if (YY<=1 && y==0 && x==stoneX) continue;
//			if (YY<=1 && y==stoneY && x==0) continue;
//			if (YY<=1 && y==stoneY && x==0) continue;
//			if () continue;
//					
//					|| SIZE*field.length-1<YY) && x == (int)XX / SIZE) continue;
//			
			// set Stone color & print
			gc.setFill(stoneColor[field[y][x]]);
			gc.fillOval(x * SIZE, y * SIZE, SIZE, SIZE);
		}
		
		// if is not pressing
		if (!isPressing) return;
		
		if (XX <= 1) XX = 1;
		else if (SIZE*field[0].length-1 < XX) XX = SIZE*field[0].length-1;

		if (YY <= 1) YY = 1;
		else if (SIZE*field.length-1 < YY) YY = SIZE*field.length-1;
		
		// set selected Stone
		gc.setFill(stoneColor[field[(int)YY/SIZE][(int)XX/SIZE]]);
		gc.fillOval(XX-SIZE/2, YY-SIZE/2, SIZE, SIZE);
	}
}