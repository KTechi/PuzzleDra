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
			// do not print -1
			if (field[y][x] == -1) continue;
			/////xとyはwindowでの位置なので、
			////実際のfieldの配列数と一致させる
			int stoneX = (int)XX / SIZE;
			int stoneY = (int)YY / SIZE;
			// if the Stone is selected
			// 選択している石なら continue;(表示しない)
			if (y == stoneY && x == stoneX) continue;

			// if mouse is out of canvas keep select the nearest stone
			//マウスカーソルがキャンバス外の時に、一番近い石を選択し続ける。(表示しない)
			if (x==stoneX && YY<=1 && y==0) continue;//UP
			if (x==stoneX && SIZE*field.length-1<YY && y==field.length-1) continue;//DOWN
			if (y==stoneY && XX<=1 && x==0) continue;//LEFT
			if (y==stoneY && SIZE*field[0].length-1<XX && x==field[0].length-1) continue;//RIGHT

			if (y==0 && YY<=1 && x==0 && XX<=1) continue;//Left Up
			if (y==0 && YY<=1 && x==field[0].length-1 && SIZE*field[0].length-1<=XX) continue;//Right Up
			if (y==field.length-1 && SIZE*field.length-1<=YY && x==0 && XX<=1) continue;//Left Down
			if (y==field.length-1 && SIZE*field.length-1<=YY && x==field[0].length-1 && SIZE*field[0].length-1<=XX) continue;//Right Down

			gc.setFill(stoneColor[field[y][x]]);
			gc.fillOval(x * SIZE, y * SIZE, SIZE, SIZE);
		}

		// if is not pressing
		if (!isPressing) {
			if (field[0][0] == -1) return;
			gc.setFill(stoneColor[field[0][0]]);
			gc.fillOval(0, 0, SIZE, SIZE);
			return;
		}

		// if XX out of canvas
		if (XX <= 1) XX = 1;
		else if (SIZE*field[0].length-1 < XX) XX = SIZE*field[0].length-1;
		// if YY out of canvas
		if (YY <= 1) YY = 1;
		else if (SIZE*field.length-1 < YY) YY = SIZE*field.length-1;

		// set selected Stone
		//選択した石の描画
		gc.setFill(stoneColor[field[(int)YY/SIZE][(int)XX/SIZE]]);
		gc.fillOval(XX-SIZE/2, YY-SIZE/2, SIZE, SIZE);
	}
}
