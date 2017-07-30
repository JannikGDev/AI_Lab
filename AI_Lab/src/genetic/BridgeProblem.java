package genetic;

public class BridgeProblem implements GeneticProblem {

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

}
