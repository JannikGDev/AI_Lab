package genetic.problems;

import genetic.GeneticProblem;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class NumberOfOnes implements GeneticProblem {

	public final float MUTATION_RATE = 0.02f;
	public final int GENOME_SIZE = 4;
	public final int POPULATION_SIZE = 100;
	
	
	@Override
	public int calcFitness(byte[] A) {
		int sum = 0;
		
		//Fitness = amount of 1s in bitstring
		for(int i = 0; i < A.length; i++) {
			for(int n = 0; n < 8; n++) {
				sum += Math.abs((A[i]>>>n)) % 2;
			}
		}
		
		return sum;
	}


	@Override
	public float getMutationRate() {
		return MUTATION_RATE;
	}


	@Override
	public int getGenomeSize() {
		return GENOME_SIZE;
	}


	@Override
	public int getGenerationSize() {
		return POPULATION_SIZE;
	}


	@Override
	public void drawSolution(byte[] A, GraphicsContext context) {
		
		double width = context.getCanvas().getWidth();
		double height = context.getCanvas().getHeight();
		
		context.setFill(Color.WHITE);
		context.fillRect(1,1,width-2,height-2);
		
		int fitness = calcFitness(A);
		
		context.strokeRect(5, 5, fitness, fitness);
		
	}

}
