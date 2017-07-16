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
import javafx.scene.Node;
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
import javafx.scene.shape.Rectangle;


public class NeuralNetworkGUI {
	
	AnchorPane networkPane;
	Button btn_train;
	Button btn_run;
	Button btn_reset;
	Label lbl_error;
	Canvas cvs_data;
	Canvas cvs_network;
	NeuralNetwork nn;
	
	ListView<String> trainList;
	HashMap<String, float[]> datasets;
	int[] network_config = new int[]{2,10,1};
	
	public NeuralNetworkGUI() {
		
		
		nn = new NeuralNetwork(network_config);
		datasets = new HashMap<String, float[]>();
		
		networkPane = new AnchorPane();
		Rectangle rect = new Rectangle(900,600,0,0);
		lbl_error = new Label("Error: ");
		lbl_error.setPrefSize(200, 50);
		AnchorPane.setLeftAnchor(lbl_error, 500.0);
		AnchorPane.setTopAnchor(lbl_error, 320.0);
		
		trainList = new ListView<String>();
		trainList.setPrefSize(200, 300);
		AnchorPane.setLeftAnchor(trainList, 600.0);
		AnchorPane.setTopAnchor(trainList, 10.0);
		
		btn_train = new Button();
		AnchorPane.setLeftAnchor(btn_train, 700.0 - btn_train.getWidth()/2);
		AnchorPane.setTopAnchor(btn_train, 320.0);
		btn_train.setText("Train");
		btn_train.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				
				List<String> list = trainList.getItems();
				if(list.size() == 0) return;
				
				
				
				
				for(int x = 0; x < 100000/list.size(); x++) {
					
					float[][] input = new float[list.size()][];
					float[][] output = new float[list.size()][];
					
					
					for(int i = 0; i < list.size(); i++) {
						
						float[] data = datasets.get(list.get(i));
						
						input[i] = new float[]{data[0],data[1]};
						output[i] = new float[]{data[2]};
						
						nn.train(input[i], output[i]);
						
					}
					
					float error = nn.getError(input,output);

					lbl_error.setText("Error: " + Math.round(error*1000)/1000.0f);
					
				}
				
				
				
				
				
				GraphicsContext gc = cvs_network.getGraphicsContext2D();
				gc.setFill(Color.WHITE);
				gc.fillRect(0, 0, cvs_network.getWidth(), cvs_network.getHeight());
				drawNetwork();
				drawOutputPlain();
			}
			
		});
		
		btn_run = new Button();
		AnchorPane.setLeftAnchor(btn_run, 750.0 - btn_run.getWidth()/2);
		AnchorPane.setBottomAnchor(btn_run, 30.0);
		btn_run.setText("Run");
		btn_run.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				
				
				
				
			}
			
		});
		
		btn_reset = new Button();
		AnchorPane.setLeftAnchor(btn_reset, 650.0 - btn_reset.getWidth()/2);
		AnchorPane.setBottomAnchor(btn_reset, 30.0);
		btn_reset.setText("Reset");
		btn_reset.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				
				datasets.clear();
				updateList();
				nn = new NeuralNetwork(network_config);
				
				
				GraphicsContext gc = cvs_network.getGraphicsContext2D();
				gc.setFill(Color.WHITE);
				gc.fillRect(0, 0, cvs_network.getWidth(), cvs_network.getHeight());
				drawNetwork();
				drawOutputPlain();
			}
			
		});
		
		
		cvs_data = new Canvas(280,280);
		GraphicsContext gc_data = cvs_data.getGraphicsContext2D();
		AnchorPane.setLeftAnchor(cvs_data, 10.0);
		AnchorPane.setTopAnchor(cvs_data, 320.0);
		gc_data.setFill(Color.GRAY);
		gc_data.fillRect(0, 0, cvs_data.getWidth(), cvs_data.getHeight());
		cvs_data.setOnMouseClicked(event -> {
			int x = (int)event.getX();
			int y = (int)event.getY();
			float fx = (float) (x / cvs_data.getWidth())*2 - 1.0f;
			float fy = (float) (y / cvs_data.getHeight())*2 - 1.0f;
			
			
			if(event.getButton() == MouseButton.PRIMARY) {
				
				datasets.put(x + "," + y + " = true", new float[]{fx,fy,1});
				updateList();
				drawOutputPlain();
			}
			else if(event.getButton() == MouseButton.SECONDARY) {
				
				datasets.put(x + "," + y + " = false", new float[]{fx,fy,0});
				updateList();
				drawOutputPlain();
			}
		});
		
		cvs_network = new Canvas(580,300);
		GraphicsContext gc_network = cvs_network.getGraphicsContext2D();
		AnchorPane.setLeftAnchor(cvs_network, 10.0);
		AnchorPane.setTopAnchor(cvs_network, 10.0);
		gc_network.setFill(Color.WHITE);
		gc_network.fillRect(0, 0, cvs_network.getWidth(), cvs_network.getHeight());
		
		
		networkPane.getChildren().add(cvs_network);
		networkPane.getChildren().add(cvs_data);
		networkPane.getChildren().add(btn_train);
		networkPane.getChildren().add(btn_reset);
		networkPane.getChildren().add(btn_run);
		networkPane.getChildren().add(trainList);
		networkPane.getChildren().add(lbl_error);
		networkPane.getChildren().add(rect);
	}
	
	
	public void updateList() {
		
		List<String> list = trainList.getItems();
		list.clear();
		list.addAll(datasets.keySet());
		
		
	}
	
	
	public void drawNeuron(Neuron n, float x, float y, float size) {
		
		GraphicsContext gc = cvs_network.getGraphicsContext2D();
		
		
		if(n.result > 0.5) gc.setFill(new Color(0.5,0.5,n.result,1));
		else gc.setFill(new Color(1.0 - n.result,0.5,0.5,1));
		gc.fillOval(x-5*size, y-5*size, 10*size, 10*size);
	
		for(int i = 0; i < n.inputs; i++) {
			if(n.weights[i] >= 0)gc.setStroke(Color.BLUE);
			else gc.setStroke(Color.RED);
			
			gc.setLineWidth((Math.sqrt(Math.abs(n.weights[i])) + 0.1)*size*0.5);
			
			if(n.inputs % 2 == 1)gc.strokeLine(x - size*30, y - size*(i-n.inputs/2)*20, x, y);
			else gc.strokeLine(x - size*30, y - size*(i- n.inputs/2 + 0.5f)*20, x, y);
			
		}
		
	}
	
	Node getContent() {
		
		return networkPane;
	}

	public void drawNetwork() {
		
		GraphicsContext gc = cvs_network.getGraphicsContext2D();
		
		for(int i = 0; i < nn.layers.length; i++) {
			
			for(int j = 0; j < nn.layers[i].length; j++) {
				
				drawNeuron(nn.layers[i][j],(i - nn.layers.length/2)*60 + 200,(j - nn.layers[i].length/2)*40 + 200,2);
				
				
			}
			
			
		}
		
		
		
		
	}
	
	
	public void drawOutputPlain() {
		
		GraphicsContext gc = cvs_data.getGraphicsContext2D();
		
		float w = (float)cvs_data.getWidth();
		float h = (float)cvs_data.getHeight();
		
		for(int x = 0; x < w; x++) {
			for(int y = 0; y < h; y++) {
				
				float result = nn.process(new float[]{(x/w)*2 - 1.0f,(y/h)*2 - 1.0f})[0];
				
				if(result > 0.5) gc.setFill(new Color(0.5,0.5,result,1));
				else gc.setFill(new Color(1.0 - result,0.5,0.5,1));
				
				gc.fillRect(x, y, 1, 1);
			}
		}
		
		List<String> list = trainList.getItems();
		
		for(int i = 0; i < list.size(); i++) {
			
			float[] data = datasets.get(list.get(i));
			
			if(data[2] < 0.5f)gc.setFill(Color.RED);
			else gc.setFill(Color.BLUE);
			
			gc.fillOval(((data[0]/2) + 0.5f)*w, ((data[1]/2) + 0.5f)*h, 2, 2);
			
		}
	}
	

}
