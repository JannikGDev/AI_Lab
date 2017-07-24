package genetic;

public interface GeneticProblem {

	
	public int calcFitness(byte[] A);
	
	public float getMutationRate();
	public int getGenomeSize();
	public int getGenerationSize();
	
	
}
