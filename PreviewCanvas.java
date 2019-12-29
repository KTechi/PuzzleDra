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
 	private int hop = 10;
 	private int combSum = 0;
 	private int temp = 0;
 	public int status = 1;

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
	private Timeline timeline;
	private int duration = 40;

	public PreviewCanvas(){
		super(480, 300);
		gc = getGraphicsContext2D();
		cellSize = (int) (this.getWidth()/6-7);
		enemyHp = 330;
		setTimeline();
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
	public void attack() {
		for(int i = 0; i < combNum.length; i++) {
			combSum += combNum[i];
		}
		temp = hop;
		timeline.play();
	}

	private void setTimeline() {
		timeline = new Timeline(
			new KeyFrame(
				new Duration(duration),
				new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
						if(combSum != 0) {
							System.out.println(combSum);
							if(status == 1) {
								if(temp > 0) {
									temp--;
									for(int i = 0; i < mHopMotion.length; i++) {
										mHopMotion[i]+=1;
									}
									System.out.println("paint1");
									paint();
								}else {
									status = 0;
								}
							}else if(status == 0) {
								if(temp <= hop) {
									temp++;
									for(int i = 0; i < mHopMotion.length; i++) {
										mHopMotion[i]--;
									}
									System.out.println("paint2");
									paint();
								}else {
									System.out.println("一コンボ終了");
									combSum--;
									status = 1;
								}
							}

						}else {
							System.out.println("finish");
							timeline.stop();
						}

					}
				}
			)
		);
		timeline.setCycleCount(Timeline.INDEFINITE);
	}

}
