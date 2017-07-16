package neural;

public class NeuralNetwork {
	
	
	public Neuron[][] layers;
	public float[] result;
	public float learningRate;
	
	
	public NeuralNetwork(int[] neuronCounts) {
		
		layers = new Neuron[neuronCounts.length - 1][];
		
		learningRate = 3.0f;
		
		for(int i = 1; i < neuronCounts.length; i++) {
			
			layers[i-1] = new Neuron[neuronCounts[i]];
			
			for(int j = 0; j < neuronCounts[i]; j++) {
				
				layers[i-1][j] = new Neuron(neuronCounts[i-1]);
				
			}
			
		}
		
		result = new float[neuronCounts[neuronCounts.length - 1]];
	}
	
	
	public float[] process(float[] input) {
		
		for(int i = 0; i < layers.length; i++) {
			result = new float[layers[i].length];
			
			for(int j = 0; j < layers[i].length; j++) {
				
				result[j] = layers[i][j].process(input);
				
			}
			
			input = result;
		}
		
		
		
		
		return result;
	}
	
	public float getError(float[][] input, float[][] expected) {
		
		float error = 0;
		
		for(int i = 0; i < input.length; i++) {
			
			float[] result = process(input[i]);
			
			for(int j = 0; j < result.length; j++) {
				
				error += Math.abs(expected[i][j] - result[j]);
				
			}
			
			
		}
		
		error = error / input.length;
		
		return error;
	}
	
	
	public void train(float[] input, float[] expected) {
		
		
		float[] result = process(input);
		float[][] delta = new float[layers.length][];
		
		int lastLayer = layers.length - 1;
		
		delta[lastLayer] = new float[layers[lastLayer].length];
		
		for(int j = 0; j < delta[lastLayer].length; j++) {
			
			delta[lastLayer][j] = result[j] * (1.0f - result[j]) * (expected[j] - result[j]);
			
		}
		
		
		for(int i = layers.length - 2; i >= 0; i--) {
			
			delta[i] = new float[layers[i].length];
			
			for(int j = 0; j < delta[i].length; j++) {
				
				float error = 0.0f;
				for(int k = 0; k < layers[i+1].length; k++) {
					
					error += layers[i+1][k].weights[j] * delta[i+1][k];
					
				}
				
				delta[i][j] = layers[i][j].result * (1.0f - layers[i][j].result) * error;
			}
			
		}
		
		for(int k = 0; k < layers[0].length; k++) {
			
			for(int j = 0; j < input.length; j++) {
				
				layers[0][k].weights[j] += learningRate * delta[0][k] * input[j];
			}
			
			layers[0][k].bias += learningRate * delta[0][k];
		}
		
		
		for(int i = 1; i < layers.length; i++) {
			
			
			for(int k = 0; k < layers[i].length; k++) {
				
				for(int j = 0; j < layers[i][0].inputs; j++) {
					
					layers[i][k].weights[j] += learningRate * delta[i][k] * layers[i-1][j].result;
				}
				
				layers[i][k].bias += learningRate * delta[i][k];
			}
		}
		
		
	}
	
	
	
	
}
