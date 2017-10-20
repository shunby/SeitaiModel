package seitaimodel;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import seitaimodel.node.Node;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

	@FXML private SplitPane root;
	@FXML private AnchorPane mainScreen;
	@FXML private AnchorPane infoScreen;

	private Stage stage;

	@Override
	public void start(Stage stage) {
		load(stage);
		this.stage = stage;
		Node node = new Node("plant", 100, 100);
		mainScreen.getChildren().add(node.getShape());
		mainScreen.getChildren().add(node.getText());
	}

	private void load(Stage stage){
		try {
			//FXMLをロード・シーン作成
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/res/fxml/Main.fxml"));
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
}
