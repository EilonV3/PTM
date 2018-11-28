package pipeGame.server;

import java.awt.Point;
import java.util.Arrays;
import java.util.Objects;

public class PipeGameLevel {
	
	private char[][] levelMatrix;
	private Point position;
	private Point start;
	private Point goal;
	
	public PipeGameLevel(int rows, int cols) {
		this.levelMatrix = new char[rows][cols];
	}
	
	public PipeGameLevel(PipeGameLevel levelToCopy) {
		this.levelMatrix = new char[levelToCopy.getNumOfRows()][levelToCopy.getNumOfCols()];
		
		for (int i = 0; i < levelToCopy.getNumOfRows(); i++) {
			for (int j = 0; j < levelToCopy.getNumOfCols(); j++) {
				this.levelMatrix[i][j] = levelToCopy.levelMatrix[i][j];
			}
		}
		
		this.setPosition(new Point(levelToCopy.position.x, levelToCopy.position.y));
		this.setGoal(levelToCopy.getGoal());
		this.setStart(levelToCopy.getStart());
	}

	public char[][] getLevelMatrix() {
		return levelMatrix;
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public Point getStart() {
		return start;
	}

	public void setStart(Point start) {
		this.start = start;
	}

	public Point getGoal() {
		return goal;
	}

	public void setGoal(Point goal) {
		this.goal = goal;
	}
	
	public int getNumOfRows() {
		return this.levelMatrix.length;
	}
	
	public int getNumOfCols() {
		return this.levelMatrix[0].length;
	}
	
	public int getRow() {
		return this.position.x;
	}
	
	public void setRow(int row) {
		this.position.x = row;
	}
	
	public int getCol() {
		return this.position.y;
	}
	
	public void setCol(int col) {
		this.position.y = col;
	}
	
	public char getObjectInPosition() {
		return this.levelMatrix[this.position.x][this.position.y];
	}
	
	public char getObjectInPosition(int x, int y) {
		return this.levelMatrix[x][y];
	}
	
	public void setObject(int x, int y, char object) {
		this.levelMatrix[x][y] = object;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < this.getNumOfRows(); i++) {
			for (int j = 0; j < this.getNumOfCols(); j++) {
				sb.append(this.levelMatrix[i][j]);
			}
			sb.append("\n");
		}
		
		String levelToString = sb.toString();
		return levelToString.substring(0, levelToString.length()-1);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (this == null || this.getClass() != obj.getClass()) return false;
		PipeGameLevel newLevel = (PipeGameLevel) obj;
		
		for (int i = 0; i < this.getNumOfRows(); i++) {
			for (int j = 0; j < this.getNumOfCols(); j++) {
				if (this.levelMatrix[i][j] != newLevel.levelMatrix[i][j]) return false;
			}
		}
		
		return Objects.equals(this.position, newLevel.position);
	}

	public int hashCode() {
		
		int hash = Objects.hashCode(this.position);
		hash = 31 * hash + Arrays.hashCode(this.levelMatrix);
		return hash;
		
	}
	
	/*public static void main(String[] args) {
		PipeGameLevel myLevel = PipeGameLevelBuilder.build("s-7L\n||F7\nF--|\n-gLF");
		System.out.println("Start Position: " + myLevel.getStart());
		System.out.println("Goal Position: " + myLevel.getGoal());
		for (int i = 0; i < myLevel.getNumOfRows(); i++)
			System.out.println(myLevel.levelMatrix[i]);
	}*/
}

