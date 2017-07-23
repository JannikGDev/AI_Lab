package genetic;

public class GeneticController implements GeneticProblem {

	
	public byte[][] population;
	
	public final static int INITIAL_MUTATION = 10;
	
	
	public GeneticController() {

		population = new byte[Operations.POPULATION_SIZE][Operations.GENOME_SIZE];
		
		for(int n = 0; n < INITIAL_MUTATION; n++) {
			for(int i = 0; i < Operations.POPULATION_SIZE; i++) {
				population[i] = Operations.mutation(population[i]);
			}
		}
		
		population = Operations.selection(population, this);
		
	}
	
	public void iterate() {
		
		int newSize = (int) (Operations.POPULATION_SIZE*1.05);
		
		byte[][] nextGen = new byte[newSize][Operations.GENOME_SIZE];
		
		
		for(int i = 0; i < population.length; i++) {
			nextGen[i] = population[i];
		}
		
		for(int i = population.length; i < newSize; i++) {
			
			int a = (int)(Math.random()*population.length - 1);
			int b = (int)(Math.random()*population.length - 1);
			
			
			//nextGen[i] = Operations.crossover(population[a], population[b]);
			
			
		}
		
		for(int i = 0; i < newSize; i++) {
			nextGen[i] = Operations.mutation(nextGen[i]);
		}
		
		population = Operations.selection(nextGen, this);
		
		
	}
	
	
	@Override
	public int calcFitness(byte[] A) {
		int sum = 0;
		
		for(int i = 0; i < A.length; i++) {
			sum += A[i];
		}
		
		return sum;
	}

	
	
	
	
	
	
	
	
	
	
	
	
}
