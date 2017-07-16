package neural;

public class Mathx {

	
	public static float sigmoid(float x, float beta) {
		
		return (float)(1.0 / (1.0 + Math.exp(-x * beta)));
	}
	
	public static float step(float x) {
		
		if(x >= 0) return 1;
		else return 0;
		
	}
	
	
	
}
