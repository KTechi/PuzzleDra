import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PreviewCanvas extends Canvas{
	GraphicsContext gc;
	public int enemyHp;
	public int cellSize;
	Color[] monster = {
		Color.RED,
		Color.BLUE,
		Color.GREEN,
		Color.YELLOW,
		Color.PURPLE,
		Color.RED
	};

	public PreviewCanvas(){
		super(480, 300);
		gc = getGraphicsContext2D();
		cellSize = (int) (this.getWidth()/6);
		enemyHp = 100;
		paint();
	}

	public void paint(){
		gc.clearRect(0, 0, this.getWidth(), this.getHeight());
		gc.setStroke(Color.GREEN);
		gc.setLineWidth(3);
		gc.strokeLine(100, 160, 400, 160);
		gc.setStroke(Color.RED);
		gc.strokeText("BossEnemy", 200, 150);
		for(int i = 0; i < monster.length; i++) {
			gc.setFill(monster[i]);
			gc.fillRect(cellSize*i,this.getHeight()-cellSize, cellSize, cellSize);
		}
	}

}
