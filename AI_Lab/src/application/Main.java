package application;
	
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import neural.NeuralNetwork;
import neural.Neuron;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;


public class Main extends Application {
	
	NeuralNetworkGUI network;
	GeneticGUI genetic;
	
	@Override
	public void start(Stage primaryStage) {
		
		network = new NeuralNetworkGUI();
		genetic = new GeneticGUI();
		
		
		AnchorPane root = new AnchorPane();
		Scene scene = new Scene(root,800,650);
		
		
		TabPane tabPane = new TabPane();
		
		Tab networkTab = new Tab("Neural Network",network.getContent());
		tabPane.getTabs().add(networkTab);
		
		Tab geneticTab = new Tab("Genetic Algorithm",genetic.getContent());
		tabPane.getTabs().add(geneticTab);
		
		
		root.getChildren().add(tabPane);
		primaryStage.setTitle("A.I. Lab");
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
