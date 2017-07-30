package application;

import genetic.GeneticController;
import genetic.GeneticProblem;
import genetic.Operations;
import genetic.problems.NumberOfOnes;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class GeneticGUI {

	GeneticController controller;
	
	AnchorPane geneticPane;
	
	Label lbl_generation;
	Label lbl_genome;
	Label lbl_fitness;
	Button btn_reset;
	Button btn_cycle;
	Canvas cnv_visu;
	ListView<String> genomeList = new ListView<String>();
	
	
	public GeneticGUI() {
		
		controller = new GeneticController();
		
		geneticPane = new AnchorPane();
		
		lbl_generation = new Label("Generation: ");
		AnchorPane.setLeftAnchor(lbl_generation, 5.0);
		AnchorPane.setTopAnchor(lbl_generation, 5.0);
		
		lbl_genome = new Label("Genome: ");
		AnchorPane.setLeftAnchor(lbl_genome, 5.0);
		AnchorPane.setTopAnchor(lbl_genome, 25.0);
		
		lbl_fitness = new Label("Fitness: ");
		AnchorPane.setLeftAnchor(lbl_fitness, 5.0);
		AnchorPane.setTopAnchor(lbl_fitness, 45.0);
		
		genomeList = new ListView<String>();
		genomeList.setPrefSize(200, 300);
		AnchorPane.setLeftAnchor(genomeList, 600.0);
		AnchorPane.setTopAnchor(genomeList, 10.0);
		genomeList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				
				if(newValue != null) {
					lbl_genome.setText("Genome: " + newValue.split(" ")[0]);
					lbl_fitness.setText("Fitness: " + newValue.split(" ")[2]);
					
					int index = genomeList.getSelectionModel().getSelectedIndex();
					
					controller.problem.drawSolution(controller.getOrderedGeneration()[index], cnv_visu.getGraphicsContext2D());
				}
				
				
				
			}
		});
		
		btn_reset = new Button("Reset");
		AnchorPane.setLeftAnchor(btn_reset, 600.0);
		AnchorPane.setTopAnchor(btn_reset, 400.0);
		btn_reset.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				controller = new GeneticController();
				fillList();
			}
			
		});
		
		btn_cycle = new Button("Iterate");
		AnchorPane.setLeftAnchor(btn_cycle, 500.0);
		AnchorPane.setTopAnchor(btn_cycle, 400.0);
		btn_cycle.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				for(int i = 0; i < 100; i++) {
					controller.iterate();
				}
				
				fillList();
			}
			
		});
		
		cnv_visu = new Canvas(200,200);
		AnchorPane.setLeftAnchor(cnv_visu, 5.0);
		AnchorPane.setTopAnchor(cnv_visu, 425.0);
		cnv_visu.getGraphicsContext2D().setFill(Color.BLACK);
		cnv_visu.getGraphicsContext2D().fillRect(0, 0, 200, 200);
		
		
		geneticPane.getChildren().add(lbl_generation);
		geneticPane.getChildren().add(lbl_genome);
		geneticPane.getChildren().add(lbl_fitness);
		geneticPane.getChildren().add(genomeList);
		geneticPane.getChildren().add(btn_reset);
		geneticPane.getChildren().add(btn_cycle);
		geneticPane.getChildren().add(cnv_visu);
		
		fillList();
	}
	
	
	public void fillList() {
		
		byte[][] genomes = controller.getOrderedGeneration();
		
		genomeList.getItems().clear();
		
		for(int i = 0; i < controller.problem.getGenerationSize(); i++) {
			
			String s = Operations.genomeToHexString(genomes[i]);
			
			s += "  " + controller.problem.calcFitness(genomes[i]);
			
			genomeList.getItems().add(s);
		}
		
		
	}
	
	
	public Node getContent() {
		
		
		return geneticPane;
	}
	
}
