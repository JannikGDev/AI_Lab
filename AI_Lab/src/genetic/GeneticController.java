package genetic;

import java.util.Comparator;
import java.util.PriorityQueue;

import genetic.problems.MazeProblem;
import genetic.problems.NumberOfOnes;

public class GeneticController {

	
	public byte[][] population;
	
	public GeneticProblem problem;
	
	public final static int INITIAL_MUTATION = 10;
	public final static float CHILD_FACTOR = 2f;
	
	
	public GeneticController() {
		
		problem = new MazeProblem(12);

		population = new byte[problem.getGenerationSize()][problem.getGenomeSize()];
		
		for(int n = 0; n < INITIAL_MUTATION; n++) {
			for(int i = 0; i < problem.getGenerationSize(); i++) {
				population[i] = Operations.mutation(population[i],problem.getMutationRate());
			}
		}
	}
	
	public void iterate() {
		
		int newSize = (int) (problem.getGenerationSize()*CHILD_FACTOR);
		
		byte[][] nextGen = new byte[newSize][];
		
		
		for(int i = 0; i < population.length; i++) {
			nextGen[i] = population[i];
		}
		
		for(int i = population.length; i < newSize; i += 2) {
			
			byte[][] parents = Operations.selection(population, 2, problem);
			while(parents[0].equals(parents[1])) {
				parents = Operations.selection(population, 2, problem);
			}
			byte[][] children = Operations.crossover(parents[0], parents[1]);
			nextGen[i] = children[0];
			if(i < newSize - 1)nextGen[i+1] = children[1];

		}
		
		for(int i = 0; i < newSize; i++) {
			nextGen[i] = Operations.mutation(nextGen[i],problem.getMutationRate());
		}
		
		population = Operations.selection(nextGen,problem.getGenerationSize(), problem);
		
	}
	
	public byte[][] getOrderedGeneration() {
		
		int[] fitness = Operations.getFitness(population, problem);
		
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				if(fitness[o1] < fitness[o2]) {
					return 1;
				}
				else if(fitness[o1] > fitness[o2]) {
					
					return -1;
				}
				else {
					return 0;
				}
			}	
		});
		
		for(int i = 0; i < fitness.length;i++) {
			pq.add(i);
		}
		
		
		byte[][] orderedPopulation = new byte[population.length][];
		
		for(int i = 0; i < orderedPopulation.length;i++) {
			orderedPopulation[i] = population[pq.poll()];
		}
		
		return orderedPopulation;
	}
}
