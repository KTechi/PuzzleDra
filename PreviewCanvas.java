import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PreviewCanvas extends Canvas{
	GraphicsContext gc;
	public int enemyHp;
	public int cellSize;
	Color[] monster = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.PURPLE};

	public PreviewCanvas(){
		super(480, 300);
		gc = getGraphicsContext2D();
		cellSize = (int) (this.getWidth()/5);
		enemyHp = 100;
	}

	public void paint(){
		gc.clearRect(0, 0, this.getWidth(), this.getHeight());
		gc.setStroke(Color.RED);
		gc.setLineWidth(3);
		gc.strokeLine(200, 90, 400, 90);
		gc.strokeText("BossEnemy", 50, 50);
		for(int i = 0; i < monster.length; i++) {
			gc.setFill(monster[i]);
			gc.strokeRect(this.getWidth()-cellSize, cellSize + cellSize*i, cellSize, cellSize);
		}
	}

}
