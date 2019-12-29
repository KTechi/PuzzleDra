import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class PreviewCanvas extends Canvas {
	GraphicsContext gc;
	public int enemyHp;
	public int cellSize;
	public int[] mHopMotion = {0, 0, 0, 0, 0, 0};///各モンスターに対応した跳ねる動作
	public int[] combNum = {0, 0, 0, 0, 0, 0 };////各色のコンボ数 --画面にいるモンスターの色順(最後は回復)
 	public int hop = 10;

	Color[] mColor = {
		Color.RED,
		Color.BLUE,
		Color.GREEN,
		Color.YELLOW,
		Color.PURPLE,
		Color.RED
	};

	Image[] monster = {
		new Image("redM.png"),
		new Image("blueM.png"),
		new Image("greenM.png"),
		new Image("holyM.png"),
		new Image("darkM.png"),
		new Image("redM.png"),
	};

	Image boss = new Image("bossM.png");
	Image maze = new Image("maze.jpg");
	Image hpBar = new Image("HpBar.PNG");

	//Animation
	private Timeline attack;
	private int duration = 500;

	public PreviewCanvas(){
		super(480, 300);
		gc = getGraphicsContext2D();
		cellSize = (int) (this.getWidth()/6-7);
		enemyHp = 330;
		paint();
	}


	////////////////////////////////////////////////////////////////
	///////////////////default field////////////////////////////////
	public void paint(){
		gc.clearRect(0, 0, this.getWidth(), this.getHeight());
		/////BackGround
		gc.drawImage(maze, 0, 0, this.getWidth(), this.getHeight());
		////Boss
		gc.drawImage(boss, 80, 20, 320, 170);
		////BossHp
		gc.setStroke(Color.GRAY);
		gc.setLineWidth(6);
		gc.strokeLine(75, 179, 75+enemyHp, 179);
		gc.setStroke(Color.BLUE);
		gc.setLineWidth(3);
		gc.strokeLine(74, 179, 404, 179);
		////Our monsters
		for(int i = 0; i < monster.length; i++) {
			gc.drawImage(monster[i],cellSize*i+4*(i+1),this.getHeight()-cellSize-30-mHopMotion[i], cellSize, cellSize);
		}
		////My Hp
		gc.drawImage(hpBar, -5, this.getHeight()-25, this.getWidth()-5, 25);
	}


	/////////////////////////////////////////////////////////////////////
	//////////////Attack////////////////////////////////////////////////
	private void attack() {
		attack = new Timeline(
			new KeyFrame(
				new Duration(duration),
				new EventHandler<ActionEvent>() {

					public void handle(ActionEvent event) {

					}
				}
			)
		);
	}

}
