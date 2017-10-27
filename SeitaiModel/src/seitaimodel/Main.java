package seitaimodel;

import java.awt.MouseInfo;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;




import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

import seitaimodel.node.Air;
import seitaimodel.node.Ground;
import seitaimodel.node.Node;
import seitaimodel.node.Pipe;
import seitaimodel.node.Updatable;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

public class Main extends Application {

	@FXML private SplitPane root;
	@FXML private AnchorPane mainScreen;
	@FXML private AnchorPane infoScreen;

	private Stage stage;
	private Map<String, Updatable> objects;
	public static boolean isRunning = true;

	@Override
	public void start(Stage stage) {
		load(stage);
		this.stage = stage;
		objects = new HashMap<>();

		//ドラッグアンドドロップ:ドロップされたとき
		mainScreen.setOnDragDropped((e)->{
			Dragboard db = e.getDragboard();
			boolean success = false;
			if(db.hasString()){
				String id = db.getString();
				Node n = (Node)objects.get(id);
				if(n != null){
					Bounds b = n.getShape().getBoundsInParent();
					n.setX(e.getX() - b.getWidth() / 2);
					n.setY(e.getY() - b.getHeight() / 2);
					success = true;
				}
			}
			e.setDropCompleted(success);
			e.consume();
		});
		//ドラッグアンドドロップ:ドラッグ中
		mainScreen.setOnDragOver((e)->{
			if(e.getDragboard().hasString()){
				e.acceptTransferModes(TransferMode.MOVE);
			}
			e.consume();
		});

		initSimulation();

		MainThread th = new MainThread();
		stage.setOnCloseRequest((e)->{
			isRunning = false;
		});
		new Thread(th).start();


	}

	private void initSimulation(){
		Ground ground = new Ground(mainScreen, 100, 300);
		Air air = new Air(mainScreen, 100, 0);

		objects.put(air.getName(), air);
		objects.put(ground.getName(), ground);
	}

	private void load(Stage stage){
		try {
			//FXMLをロード・シーン作成
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("res/fxml/Main.fxml"));
			loader.setController(this);
			Scene scene = new Scene(loader.load());
			stage.setScene(scene);

			//表示
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}


	}



	public static void main(String[] args) {
		launch(args);
	}


	class MainThread extends Task<Boolean>{

		@Override
		protected Boolean call() throws Exception {
			while(isRunning){
				try{
					objects.forEach((s, object)->{
						object.update();
					});
					Thread.sleep(32);
				}catch(Exception e){
					e.printStackTrace();

				}

			}
			return null;
		}
	}
}
