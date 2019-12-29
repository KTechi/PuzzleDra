import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class PuzzleCanvas extends Canvas {
	private Executer executer;
	private PreviewCanvas preview;
	private GraphicsContext gc = getGraphicsContext2D();
	private Image[] stones = {
			new Image("redStone.png"),
			new Image("blueStone.png"),
			new Image("greenStone.png"),
			new Image("yellowStone.png"),
			new Image("darkStone.png"),
			new Image("pinkStone.png")
		};
	private boolean isPressing = false;
	private final int SIZE = 80;
	private double XX = -100, YY = -100;
	int[][] field;

	private Timeline timeline;
	private int duration = 6;
	private int frame;
	private int status;

	PuzzleCanvas() {
		super(480, 402);

		field = new int[5][6];
		for (int y = 0; y < field.length; y++)
		for (int x = 0; x < field[0].length; x++) {
			field[y][x] = (x + y) % 6;// make field
		}
		setTimeline();
		paint();
	}

	void setExecuter(Executer e) {
		executer = e;
	}

	void setPreview(PreviewCanvas preview) {
		this.preview = preview;
	}

	void pressed(double x, double y) {
		isPressing = true;
		XX = x;
		YY = y;
		paint();
	}

	void dragged(double x, double y) {
		XX = x;
		YY = y;
		paint();
	}

	void released() {
		isPressing = false;
		frame = 0;
		status = 0;
		executer.resetCombNum();
		timeline.play();
	}

	private void paint() {
		// set BackGround
		gc.setFill(new Color(0.2, 0.1, 0.1, 1));
		gc.fillRect(0, 0, this.getWidth(), this.getHeight());

		// if XX or YY is out of canvas
		if (XX < 0) XX = 0;
		else if (SIZE * field[0].length - 1 < XX) XX = SIZE * field[0].length - 1;
		if (YY < 0) YY = 0;
		else if (SIZE * field.length - 1 < YY) YY = SIZE * field.length - 1;

		// print Stones
		for (int y = 0; y < field.length; y++)
		for (int x = 0; x < field[0].length; x++) {
			// do not print -1
			if (field[y][x] == -1) continue;
			// do not print the stone if it is selected
			if (isPressing && y == (int)YY / SIZE && x == (int)XX / SIZE) continue;

			gc.drawImage(stones[field[y][x]], x*SIZE, y*SIZE, SIZE, SIZE);
		}
		// paint the selected stone 選択した石の描画
		if (isPressing) gc.drawImage(stones[field[(int)YY/SIZE][(int)XX/SIZE]], XX-SIZE/2, YY-SIZE/2, SIZE, SIZE);
	}

	private void paint(int margin) {
		// set BackGround
		gc.setFill(new Color(0.2, 0.1, 0.1, 1));
		gc.fillRect(0, 0, this.getWidth(), this.getHeight());

		// print Stones
		boolean drop = false;
		for (int x = 0; x < field[0].length; x++) {
			drop = false;
			for (int y = field.length - 1; 0 <= y; y--) {
				// do not print -1
				if (field[y][x] == -1) {
					drop = true;
					continue;
				}
				if (drop) gc.drawImage(stones[field[y][x]], x*SIZE, y*SIZE+margin, SIZE, SIZE);
				else      gc.drawImage(stones[field[y][x]], x*SIZE, y*SIZE, SIZE, SIZE);
			}
		}
	}

	private void setTimeline() {
		timeline = new Timeline(
			new KeyFrame(
				new Duration(duration),
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						// 消す石がある場合、field[][]の値 -1 で埋める。
						// 消す石が無い場合、終了
						if (status == 0) {
							if (frame != 0) {
								if (frame < 50) frame++;
								else {
									frame = 0;
									if (executer.calculateScore()) frame = 1;
									else status = 1;
								}
							} else {
								if (!executer.calculateScore()) {// アニメーション終了
									paint();
									timeline.stop();
									preview.attack();
								} else frame = 1;
							}
							paint();
						}
						// 落ちるアニメーション
						else if (status == 1) {
							paint(++frame);
							if (SIZE < frame) {
								frame = 0;
								status = (executer.dropStone()) ? 1 : 2;
							}
						}
						// field[][] の値が -1 ならランダムで値を埋める
						else if (status == 2) {
							frame++;
							if (100 < frame) {
								frame = 0;
								status = 0;
							} else if (frame == 50) {
								executer.fillField();
								paint();
							}
						}
					}
				}
			)
		);
		timeline.setCycleCount(Timeline.INDEFINITE);
	}
}