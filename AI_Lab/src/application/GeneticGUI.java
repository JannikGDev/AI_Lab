package application;

import genetic.BridgeProblem;
import genetic.GeneticController;
import genetic.GeneticProblem;
import genetic.Operations;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

public class GeneticGUI {

	GeneticController controller;
	GeneticProblem problem;
	
	AnchorPane geneticPane;
	
	Label lbl_generation;
	Label lbl_genome;
	Label lbl_fitness;
	Button btn_reset;
	Button btn_cycle;
	ListView<String> genomeList = new ListView<String>();
	
	
	public GeneticGUI() {
		
		controller = new GeneticController();
		problem = new BridgeProblem();
		
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
				controller.iterate();
				fillList();
			}
			
		});
		
		
		
		
		geneticPane.getChildren().add(lbl_generation);
		geneticPane.getChildren().add(lbl_genome);
		geneticPane.getChildren().add(lbl_fitness);
		geneticPane.getChildren().add(genomeList);
		geneticPane.getChildren().add(btn_reset);
		geneticPane.getChildren().add(btn_cycle);
		
		fillList();
	}
	
	
	public void fillList() {
		
		genomeList.getItems().clear();
		
		for(int i = 0; i < Operations.POPULATION_SIZE; i++) {
			
			String s = Operations.genomeToString(controller.population[i]);
			
			s += "  " + problem.calcFitness(controller.population[i]);
			
			genomeList.getItems().add(s);
		}
		
		
	}
	
	
	public Node getContent() {
		
		
		return geneticPane;
	}
	
}
