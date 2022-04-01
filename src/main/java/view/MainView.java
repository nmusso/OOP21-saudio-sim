package view;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;


public class MainView extends Application {

	private float horizontal;
	private float vertical;
	private float maxX = 500;
	private float maxY = 500;


	/**
	 *
	 */
	@Override
	public void start(Stage mainStage) throws Exception {
		mainStage.setTitle("Collector");
		Parent root = FXMLLoader.load(getClass().getResource("provafxml.fxml"));
		Scene scene = new Scene(root);
		Button btnsource = (Button) scene.lookup("#addSource");
		btnsource.setOnAction((ActionEvent event) -> {
                  System.out.println("add Source");
                });
	        //set transparent
	        scene.setFill(Color.TRANSPARENT);
	        mainStage.setScene(scene);
	        mainStage.show();
	}
	
	public void reserchfolderClick() {
		System.out.println("prova");
	}

}
