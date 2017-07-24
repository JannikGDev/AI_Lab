package genetic;

public class GeneticController {

	
	public byte[][] population;
	
	public GeneticProblem problem;
	
	public final static int INITIAL_MUTATION = 10;
	
	
	public GeneticController() {
		
		problem = new BridgeProblem();

		population = new byte[problem.getGenerationSize()][problem.getGenomeSize()];
		
		for(int n = 0; n < INITIAL_MUTATION; n++) {
			for(int i = 0; i < problem.getGenerationSize(); i++) {
				population[i] = Operations.mutation(population[i],problem.getMutationRate());
			}
		}
	}
	
	public void iterate() {
		
		int newSize = (int) (problem.getGenerationSize()*1.05);
		
		byte[][] nextGen = new byte[newSize][];
		
		
		for(int i = 0; i < population.length; i++) {
			nextGen[i] = population[i];
		}
		
		for(int i = population.length; i < newSize; i += 2) {
			
			byte[][] parents = Operations.selection(population, 2, problem);
			byte[][] children = Operations.crossover(parents[0], parents[1]);
			nextGen[i] = children[0];
			nextGen[i+1] = children[1];

		}
		
		for(int i = 0; i < newSize; i++) {
			nextGen[i] = Operations.mutation(nextGen[i],problem.getMutationRate());
		}
		
		population = Operations.selection(nextGen,problem.getGenerationSize(), problem);
		
	}
	
	public byte[][] getOrderedGeneration() {
		
		// TODO implement
		
		
		
		
		
		return population;
	}
}
