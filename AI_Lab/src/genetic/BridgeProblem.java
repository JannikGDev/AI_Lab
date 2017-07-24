package genetic;

public class BridgeProblem implements GeneticProblem {

	public final float MUTATION_RATE = 0.002f;
	public final int GENOME_SIZE = 1;
	public final int POPULATION_SIZE = 100;
	
	
	@Override
	public int calcFitness(byte[] A) {
		int sum = 0;
		
		for(int i = 0; i < A.length; i++) {
			sum += (A[i] + 128);
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

}
