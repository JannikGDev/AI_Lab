package genetic;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Operations {

	
	
	public final static float MUTATION_RATE = 0.002f;
	public final static int GENOME_SIZE = 1;
	public final static int POPULATION_SIZE = 100;
	
	public static byte[] crossover(byte[] A, byte[] B) {
		
		int cut = GENOME_SIZE*4;//(int)(Math.random()*GENOME_SIZE*8 + 0.5);
		
		byte[] C = new byte[GENOME_SIZE];
		
		for(int i = 0; i < GENOME_SIZE*8; i++) {
			if(i/8 < cut/8) {
				C[i/8] = A[i/8];
			}
			else if(i/8 == cut/8) {
				
				byte mask = 1;
				
				for(int x = 0; x < i % 8; x++) {
					mask *= 2;
				}
				
				if((A[i/8] & mask) > 0) {
					C[i/8] |= mask;
				}
				else {
					C[i/8] &= ~mask;
				}
				
				
			}
			else {
				C[i/8] = B[i/8];
			}
		}
		
		return A;
	}
	
	public static byte[] mutation(byte[] A) {
		
		for(int i = 0; i < GENOME_SIZE*8; i++) {
			byte mask = 1;
			
			for(int x = 0; x < i % 8; x++) {
				mask *= 2;
			}
			
			
			if(Math.random() <= MUTATION_RATE) {
				
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
	
	
	public static byte[][] selection(byte[][] A, GeneticProblem environment) {
	
	
		int[] fitness = new int[A.length];
		
		for(int i = 0; i < A.length; i++) {
			fitness[i] = environment.calcFitness(A[i]);
		}
		
		
		PriorityQueue<Integer> list = new PriorityQueue<Integer>(new Comparator<Integer>() {
	
			@Override
			public int compare(Integer o1, Integer o2) {
				
				if(fitness[o1] > fitness[o2]) {
					return -1;
				}
				else if(fitness[o1] == fitness[o2]) {
					return 0;
				}
				else {
					return 1;
				}
			}
			
		});
		
		for(int i = 0; i <  A.length; i++) {
			list.add(i);
		}
		
		byte[][] selected = new byte[POPULATION_SIZE][];
		
		for(int i = 0; i < POPULATION_SIZE; i++) {
			selected [i] = A[list.poll()];
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
