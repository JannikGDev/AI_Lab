package genetic;

public class BridgeProblem implements GeneticProblem {

	@Override
	public int calcFitness(byte[] A) {
		int sum = 0;
		
		for(int i = 0; i < A.length; i++) {
			sum += A[i];
		}
		
		return sum;
	}

}
