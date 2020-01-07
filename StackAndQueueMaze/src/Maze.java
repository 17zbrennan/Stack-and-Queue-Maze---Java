//CIT360 - 01, Zachary Brennan; Creates a maze, reads the maze and then traverses the maze in two different ways. 
import java.awt.Point;
import java.util.Arrays;
import java.util.Random;
public class Maze {
	public static void main(String[] args) {
		int colomn; 
		int row;
		int iteration = 1;
		Random randomOne;
		Random randomTwo; 
		char[][] maze; 
		char[][] mazeCopy;
		boolean[][] visited;
		boolean[][] visitedCopy;
		int[][] distance;
		System.out.println("The order of movement is: North, East, South, West.");
	while(iteration < 4) {
			colomn = (int) (Math.random() * 15)+5;
			row = colomn;
			randomOne = new Random();
			randomTwo = new Random();
			maze = new char[row+2][colomn+2];
			visited = new boolean[row+2][colomn+2];
			distance = new int [row+2][colomn+2];
			createMaze(maze,colomn,randomOne,randomTwo,colomn,row);
			fill2DArray(visited, maze);
			createTarget(maze,0,0,colomn,row,randomOne,randomTwo);
			createStart(maze,0,0,colomn,row,randomOne,randomTwo);
			System.out.println("Depth First Maze #" + iteration);
			traverseMazeStack(maze,visited);
			read2DArray(maze);
			iteration++;
		}
		iteration = 1;
		while(iteration < 4) {
			colomn = (int) (Math.random() * 15)+5;
			row = colomn;
			randomOne = new Random();
			randomTwo = new Random();
			maze = new char[row+2][colomn+2];
			visited = new boolean[row+2][colomn+2];
			distance = new int [row+2][colomn+2];
			createMaze(maze,colomn,randomOne,randomTwo,colomn,row);
			fill2DArray(visited, maze);
			createTarget(maze,0,0,colomn,row,randomOne,randomTwo);
			createStart(maze,0,0,colomn,row,randomOne,randomTwo);
			System.out.println("Breadth First Maze #" + iteration);
			traverseMazeQueue(maze,visited,distance);
			read2DArray(maze);
			iteration++;
		
		}
	}	
	/**
	 * Goes through the randomly generated maze and makes a breadth first path. 
	 * @param maze
	 * @param visited
	 * @param distance
	 */
	public static void traverseMazeQueue(char[][] maze,boolean[][] visited,int[][] distance){
		int	row = 0;
		int col = 0;
		int distancePoint = 0;
		boolean noSolution = false;
		Point point = new Point(row, col);
		Point temp = new Point(row, col);
		boolean target = false;
		Queue queue = new Queue();
		for(int i = 0; i < maze.length; i++) {
			for(int j = 0; j < maze[i].length;j++){
				if(maze[i][j] == 'S') {
					row = i;
					col = j;
				}
			}
		}
		point.setLocation(row, col);
		queue.enqueue(point);
		distance[row][col] = 0;
		visited[row][col] = true;
		 while(target == false) {
			 if(!queue.isEmpty()) {
				temp = (Point) queue.peak();
				queue.dequeue();
				distancePoint =	distance[temp.x ][temp.y] + 1;
				if(maze[temp.x][temp.y] == 'F') {
					target = false;
					break;
				}
				if(visited[temp.x -1][temp.y] == false){
					visited[temp.x-1][temp.y] = true;
					distance[temp.x-1][temp.y] = distancePoint;
					row = temp.x -1;
					col = temp.y;
					queue.enqueue(new Point(row,col));
				}
				if(visited[temp.x][temp.y+1] == false){
					visited[temp.x][temp.y+1] = true;
					distance[temp.x][temp.y+1] = distancePoint;
					row = temp.x;
					col = temp.y+1;
					queue.enqueue(new Point(row,col));
				}
				if(visited[temp.x+1][temp.y] == false){
					visited[temp.x+1][temp.y] = true;
					distance[temp.x+1][temp.y] = distancePoint;
					row = temp.x + 1;
					col = temp.y;
					queue.enqueue(new Point(row,col));
				}
				if(visited[temp.x][temp.y -1] == false){
					visited[temp.x][temp.y-1] = true;
					distance[temp.x][temp.y-1] = distancePoint;
					row = temp.x;
					col = temp.y-1;
					queue.enqueue(new Point(row,col));
				}
			}else {
				System.out.println("Maze has no solution.");
				target = true;
				noSolution = true;
			}
		 }
		 if(noSolution == false){
			 row = temp.x;
			 col = temp.y;
			 target = false;
			 distancePoint = distance[temp.x][temp.y];
			 while(target == false){
				 try {
					if(distance[row-1][col] == distancePoint -1) {
						distancePoint -= 1;
						if(maze[row][col] != 'F')
							maze[row][col] = '.';
						row = row -1;
					}else if(distance[row][col+1] == distancePoint -1) {
						distancePoint -= 1;
						if(maze[row][col] != 'F')
							maze[row][col] = '.';
						col = col + 1;
					}else if(distance[row+1][col] == distancePoint -1) {
						distancePoint -= 1;
						if(maze[row][col] != 'F')
							maze[row][col] = '.';
						row = row +1;
					}else if(distance[row][col-1] == distancePoint -1) {
						distancePoint -= 1;
						if(maze[row][col] != 'F')
							maze[row][col] = '.';
						col = col -1;
					}else {
						target = true;
					}
			 	
			 }
		 catch(Exception e) {
			 break;
 			}
		 }
	 }
 }
	/**
	 * Goes through the randomly generated maze and makes a depth first path. 
	 * @param maze
	 * @param visited
	 */
	public static void traverseMazeStack(char[][] maze,boolean[][] visited){
		int	row = 0;
		int col = 0;
		int originalRow;
		int originalCol;
		Point point = new Point(row, col);
		Point temp = new Point(row, col);
		boolean target = false;
		Stack stack = new Stack();
		for(int i = 0; i < maze.length; i++) {
			for(int j = 0; j < maze[i].length;j++){
				if(maze[i][j] == 'S') {
					row = i;
					col = j;
				}
			}
		}
		point.setLocation(row, col);
		visited[row][col] = true;
		originalRow = row;
		originalCol = col;
		while(target != true) {
			if(visited[row-1][col] == false) {
				row -= 1;
				stack.push(new Point(row,col));
				visited[row][col] = true;
			}else if(visited[row][col+1] == false) {
				col += 1;
				stack.push(new Point(row,col));
				visited[row][col] = true;
			}else if(visited[row+1][col] == false) {
				row += 1;				
				stack.push(new Point(row,col));
				visited[row][col] = true;
			}else if(visited[row][col-1] == false) {
				col -= 1;
				stack.push(new Point(row,col));
				visited[row][col] = true;
			}else {
					stack.pop();
					if(!stack.isEmpty()) {
						temp = (Point) stack.top();
						col = temp.y;
						row = temp.x;
					}else {
						row = originalRow;
						col = originalCol;
						if(visited[row-1][col] == true && visited[row+1][col] == true && visited[row][col+1] == true && visited[row][col-1] == true ) {
							target = true;
							System.out.println("Maze has no solution.");
						}
					}
			}
			if(maze[row][col] == 'F'){
				target = true;
			}
			
		}
		while(!stack.isEmpty()) {
			
			temp = (Point) stack.top();
			row = temp.x;
			col = temp.y;
			stack.pop();
			if(maze[row][col] != 'F' && maze[row][col] != 'S') {
				maze[row][col] = '.';
			}
		}	
	}
	/**
	 * Creates a randomly generated maze.
	 * @param array
	 * @param walls
	 * @param randomOne
	 * @param randomTwo
	 * @param c
	 * @param r
	 */
	public static void createMaze(char[][]array,int walls, Random randomOne, Random randomTwo,int c, int r) {
		int wallLocation;
		int randCol;
		int randRow;
		int rand;
		int randTwo;
		for(int i = 0; i < array.length; i++) {
			for(int j = 0; j < array[i].length;j++){
				if(j == 0 || i == 0 || j == array[i].length-1 || i == array.length-1)
				{
					array[i][j] = 'x';
				}
			}
		}
		while(walls > 0) {
			randCol = randomOne.nextInt((c - 1) + 1) + 1;
			randRow = randomTwo.nextInt((r - 1) + 1) + 1;
			rand = (int)(Math.random()*4);
			array[randRow][randCol] = 'x';
			while(rand >= 0) {
				randTwo = (int)(Math.random()*4);
				if(randTwo == 3) {
					array[randRow-1][randCol] = 'x';
				}
				if(randTwo == 2) {
					array[randRow][randCol+1] = 'x';
				}
				if(randTwo == 1) {
					array[randRow+1][randCol] = 'x';
				}
				if(randTwo == 0) {
					array[randRow][randCol-1] = 'x';
				}
				rand -= 1;
			}
			walls--;
		}
	}
	/** 
	 * Places the target of the maze represented as an F. 
	 * @param array
	 * @param randRow
	 * @param randCol
	 * @param c
	 * @param r
	 * @param randomOne
	 * @param randomTwo
	 */
	public static void createTarget(char[][] array,int randRow, int randCol,int c, int r, Random randomOne, Random randomTwo) {
		boolean target = false;
		while(target == false) {
			randCol = randomOne.nextInt((c - 1) + 1) + 1;
			randRow = randomTwo.nextInt((r - 1) + 1) + 1;
			if(array[randRow][randCol] != 'x' ){
				array[randRow][randCol] = 'F';
				target = true;
			}
		}
	}
	/** 
	 * Creates the start of the maze represented as an S. 
	 * @param array
	 * @param randRow
	 * @param randCol
	 * @param c
	 * @param r
	 * @param randomOne
	 * @param randomTwo
	 */
	public static void createStart(char[][] array,int randRow, int randCol,int c, int r, Random randomOne, Random randomTwo) {
		boolean target = false;
		while(target == false) {
			randCol = randomOne.nextInt((c - 1) + 1) + 1;
			randRow = randomTwo.nextInt((r - 1) + 1) + 1;
			if(array[randRow][randCol] != 'x' && array[randRow][randCol] != 'F'
					&& array[randRow-1][randCol] != 'F'&& array[randRow+1][randCol] != 'F'
					&& array[randRow][randCol-1] != 'F'&& array[randRow][randCol+1] != 'F'){
				array[randRow][randCol] = 'S';
				target = true;
			}
		}
	}
	/**
	 * Reads the maze into the console. 
	 * @param array
	 */
	public static void read2DArray(char[][] array) {
		for(int i = 0; i < array.length; i++) {
			for(int j = 0; j < array[i].length;j++){
				System.out.print(array[i][j]);
				System.out.print(" ");
			}
			System.out.println();
		}
	}
	/**
	 * Fills the visited array with true or false values.
	 * @param array
	 * @param arrayTwo
	 */
	public static void fill2DArray(boolean[][] array,char[][] arrayTwo) {
		for(int i = 0; i < array.length; i++) {
			for(int j = 0; j < array[i].length;j++){
				array[i][j] = false;
				if(arrayTwo[i][j] == 'x')
					array[i][j] = true;
			}
		}
	}
}