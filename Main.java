import javafx.application.Application;
import javafx.event.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class Main extends Application implements EventHandler {
	// Components
	private PreviewCanvas previewCanvas;
	private PuzzleCanvas puzzleCanvas;
	private Executer executer;
	private boolean processing = false;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		// PreviewCanvas
		previewCanvas = new PreviewCanvas();
		
		// PuzzleCanvas
		puzzleCanvas = new PuzzleCanvas();
		puzzleCanvas.addEventHandler(MouseEvent.ANY, this);
		
		// Executer
		executer = new Executer(puzzleCanvas.field);
		
		// Pane
		BorderPane bp = new BorderPane();
		bp.setTop(previewCanvas);
		bp.setCenter(puzzleCanvas);
		
		// Scene
		Scene sc = new Scene(bp, 480, 900);

		// Stage
		primaryStage.setTitle("PuzzleDra");
		primaryStage.setMinWidth(480);
		primaryStage.setMaxWidth(480);
		primaryStage.setMinHeight(725);
		primaryStage.setMaxHeight(725);
		primaryStage.setScene(sc);
		primaryStage.show();
	}

	@Override
	public void handle(Event event) {
		if (!(event instanceof MouseEvent)) {
			System.out.println("Error [PuzzleCanvasEventHandler]");
			return;
		}
		MouseEvent e = (MouseEvent)event;
		
		if (e.getEventType() == MouseEvent.MOUSE_DRAGGED) {
			if (processing) return;
			puzzleCanvas.XX = e.getX();
			puzzleCanvas.YY = e.getY();
			executer.switchStone(e.getX(), e.getY());
		} else if (e.getEventType() == MouseEvent.MOUSE_RELEASED) {
			processing = true;
			executer.decide();
			processing = false;
		}

		puzzleCanvas.paint();
	}
}