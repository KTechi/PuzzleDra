import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class PreviewCanvas extends Canvas{
	GraphicsContext gc;
	public int enemyHp;
	public int cellSize;
	Image[] monster = {
		new Image("redM.jpg"),
		new Image("blueM.jpg"),
		new Image("greenM.jpg"),
		new Image("holyM.jpg"),
		new Image("darkM.jpg"),
		new Image("redM.jpg"),
	};

	Image boss = new Image("bossM.jpg");
	//Image maze = new Image("maze.jpg");

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
		gc.strokeLine(75, 190, 405, 190);
		gc.drawImage(boss, 80, 10, 320, 170);
		for(int i = 0; i < monster.length; i++) {
			gc.drawImage(monster[i],cellSize*i,this.getHeight()-cellSize, cellSize, cellSize);
		}
	}

}
