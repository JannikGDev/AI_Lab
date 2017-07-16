package application;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class GeneticGUI {

	AnchorPane geneticPane;
	
	Label lbl_generation;
	Label lbl_genome;
	Label lbl_fitness;
	
	
	
	public GeneticGUI() {
		
		
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
		
		
		
		geneticPane.getChildren().add(lbl_generation);
		geneticPane.getChildren().add(lbl_genome);
		geneticPane.getChildren().add(lbl_fitness);
	}
	
	
	
	public Node getContent() {
		
		
		return geneticPane;
	}
	
}
