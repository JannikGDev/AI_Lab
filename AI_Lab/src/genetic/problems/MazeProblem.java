package genetic.problems;

import genetic.GeneticProblem;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MazeProblem implements GeneticProblem {

	public final float MUTATION_RATE = 0.1f;
	public final int GENOME_SIZE = 6;
	public final int POPULATION_SIZE = 200;
	
	boolean[][] maze;
	int size;
	
	int startX;
	int startY;
	int goalX;
	int goalY;
	
	public MazeProblem(int size) {
		
		maze = new boolean[size][size];
		this.size = size;
		
		startX = 0;
		startY = 0;
		goalX = size-1;
		goalY = size-1;
		
		for(int x = 0; x < size; x++) {
			for(int y = 0; y < size; y++) {
				
				maze[x][y] = Math.random() > 0.8f;
				
			}
		}
		
		
		maze[startX][startY] = false;
		maze[goalX][goalY] = false;
	}
	
	
	@Override
	public int calcFitness(byte[] A) {
		
		int x = startX;
		int y = startY;
		
		int steps = 1;
		
		for(int i = 0; i < A.length*8; i += 2) {
			
			byte data = A[i/8];
			byte dir = (byte) ((((0b11111111>>>6)<<i) & data)>>>i);
			
			switch(dir) {
			case 0:
				if(y - 1 > 0 && !maze[x][y-1]) {
					y -= 1;
					steps++;
				} else  i = A.length*8;
				break;
			case 1:
				if(x + 1 < size && !maze[x+1][y]) {
					x += 1;
					steps++;
				}else  i = A.length*8;
				break;
			case 2:
				if(x - 1> 0 && !maze[x-1][y]) {
					x -= 1;
					steps++;
				}else   i = A.length*8;
				break;
			case 3:
				if(y + 1 < size && !maze[x][y+1]) {
					y += 1;
					steps++;
				}else  i = A.length*8;
				break;
			}
		}
		
		int diffX = goalX - x;
		int diffY = goalY - y;
		
		return (int) (size*2 -(diffX+diffY)	);
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

	@Override
	public void drawSolution(byte[] A, GraphicsContext context) {
		
		//Draw Maze
		double width = context.getCanvas().getWidth();
		double height = context.getCanvas().getHeight();
		
		context.setFill(Color.WHITE);
		context.fillRect(1,1,width-2,height-2);
		
		context.setFill(Color.BLACK);
		
		double w = width/size;
		double h = height/size;
		
		for(int x = 0; x < size; x++) {
			for(int y = 0; y < size; y++) {
				
				if(x == startX && y == startY) {
					context.setFill(Color.GREEN);
					context.fillRect(x*w, y*h, w, h);
				}
				else if(x == goalX && y == goalY) {
					context.setFill(Color.BLUE);
					context.fillRect(x*w, y*h, w, h);
				}
				else if(maze[x][y]) {
					context.setFill(Color.BLACK);
					context.fillRect(x*w, y*h, w, h);
				}
				
			}
		}
		
		int x = startX;
		int y = startY;
		
		for(int i = 0; i < A.length*8; i += 2) {
			
			byte data = A[i/8];
			byte dir = (byte) ((((0b11111111>>>6)<<i) & data)>>>i);
			
			context.setFill(Color.BLACK);
			context.setFont(new Font(15));
			
			
			
			switch(dir) {
			case 0:
				if(y - 1 > 0 && !maze[x][y-1]) {
					context.fillText("^", x*w, y*h);
					y -= 1;
				}
				else i = A.length*8;
			
				break;
			case 1:
				if(x + 1 < size && !maze[x+1][y]) {
					context.fillText(">", x*w + w/4, y*h + h);
					x += 1;
				}else i = A.length*8;
				
				break;
			case 2:
				if(x - 1> 0 && !maze[x-1][y]) {
					context.fillText("<", x*w + w/4, y*h + h);
					x -= 1;
				}else i = A.length*8;
					
				break;
			case 3:
				if(y + 1 < size && !maze[x][y+1]) {
					context.fillText("v", x*w + w/4, y*h + h);
					y += 1;
				}else i = A.length*8;
				
				break;
			}

		}
		
		context.fillText("o", x*w + w/4, y*h + h);
		

	}

}
