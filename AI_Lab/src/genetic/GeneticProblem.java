package genetic;

import javafx.scene.canvas.GraphicsContext;

public interface GeneticProblem {

	
	public int calcFitness(byte[] A);
	
	public float getMutationRate();
	public int getGenomeSize();
	public int getGenerationSize();
	public void drawSolution(byte[] A, GraphicsContext context);
	
}
