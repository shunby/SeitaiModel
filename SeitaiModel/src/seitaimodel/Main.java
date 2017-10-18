package seitaimodel;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage stage) {
		load(stage);
	}

	private void load(Stage stage){
		try {
			//FXMLをロード・シーン作成
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/res/fxml/Main.fxml"));
			System.out.println(getClass().getResource("/res/fxml/Main.fxml"));
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
