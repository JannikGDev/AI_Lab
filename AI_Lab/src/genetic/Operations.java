package genetic;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Operations {
	
	public static byte[][] crossover(byte[] A, byte[] B) {
		
		int cut = A.length*4;//(int)(Math.random()*GENOME_SIZE*8 + 0.5);
		
		byte[][] children = new byte[2][A.length];
		
		for(int i = 0; i <  A.length; i++) {
			if(i < cut/8) {
				children[0][i] = A[i];
				children[1][i] = B[i];
				i += 8;
			}
			else if(i == cut/8) {
				byte mask = 0b1111111;
				

				mask = (byte) (mask << (cut % 8));
				
				
				byte nMask = (byte) ~mask;
				
				children[0][i] = mask;
				
				
				children[0][i] = (byte) ((mask & A[i]) | (nMask & B[i]));
				children[1][i] = (byte) ((mask & B[i]) | (nMask & A[i]));
				
			}
			else {
				children[0][i] = B[i];
				children[1][i] = A[i];
			}
		}
		
		return children;
	}
	
	public static byte[] mutation(byte[] A, float mutationRate) {
		
		for(int i = 0; i < A.length*8; i++) {
			byte mask = 1;
			
			for(int x = 0; x < i % 8; x++) {
				mask *= 2;
			}
			
			
			if(Math.random() <= mutationRate) {
				
				if((A[i/8] & mask) > 0) {
					A[i/8] &= ~mask;
				}
				else {
					A[i/8] |= mask;
				}
				
			}
		}
		
		return A;
	}
	
	public static int[] getFitness(byte[][] A, GeneticProblem environment) {
		
		int[] fitness = new int[A.length];
		
		for(int i = 0; i < A.length; i++) {
			fitness[i] = environment.calcFitness(A[i]);
		}
		return fitness;
	}
	
	
	public static byte[][] selection(byte[][] A,int amount, GeneticProblem environment) {
	
		int[] selection = new int[amount];
		
		int[] fitness = new int[A.length];
		
		for(int i = 0; i < A.length; i++) {
			fitness[i] = environment.calcFitness(A[i]);
		}
		
		long fitnessSum = 0;
		for(int i = 0; i < fitness.length; i++) {
			
			fitnessSum += fitness[i];
			
		}
		
		for(int i = 0; i < amount; i++) {
			
			long select = (long) (Math.random()*fitnessSum);
			
			
			for(int n = 0; n < A.length; n++) {
				
				
				select -= fitness[n];
				
				if(select <= 0 || n == A.length - 1) {
					fitnessSum -= fitness[n];
					fitness[n] = 0;
					selection[i] = n;
					break;
				}
			}
		}
		
		
		byte[][] selected = new byte[amount][];
		
		for(int i = 0; i < amount; i++) {
			
			selected[i] = A[selection[i]];
			
		}
		
		return selected;
	}
	
	public static String genomeToString(byte[] A) {
		String s = "";
		
		for(int i = 0; i < A.length; i++) {
			s += String.format("%8s", Integer.toBinaryString((A[i] + 256) % 256)).replace(' ', '0');
		}
		
		return s;
	}
	
}
