package neural;

public class Neuron {
	
	
	public float[] weights;
	public float bias;
	public int inputs;
	public float result;
	
	public Neuron(int inputs) {
		this.inputs = inputs;
		weights = new float[inputs];
		result = 0.5f;
		
		for(int i = 0; i < weights.length; i++) {
			
			weights[i] = (float) Math.random()*2 - 1.0f;
			
		}
		
		bias = (float) Math.random()*2 - 1.0f;
		
	}
	
	public float process(float[] input) {
		if(input.length != inputs) {
			System.out.println("WRONG NUMBER OF INPUTS");
			int x = 1/0;
		}
		
		float sum = 0;
		
		for(int i = 0; i < inputs; i++) {
			sum += weights[i]*input[i] + bias;
		}
		
		result = Mathx.sigmoid(sum, 1f);
		return result;
	}
	
	
	public void train(float[] input,float output) {
		
		float result = process(input);
		
		float error = output - result;
		
		bias = bias + error;
		
		for(int i = 0; i < inputs; i++) {
			weights[i] = weights[i] + error * input[i];
			if(weights[i] > 1.0f) weights[i] = 1.0f;
			else if(weights[i] < -1.0f) weights[i] = -1.0f;
		}
		
		
	}
	
	
	
	
}
