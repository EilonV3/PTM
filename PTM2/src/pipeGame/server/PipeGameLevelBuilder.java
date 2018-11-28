package pipeGame.server;

import java.awt.Point;

public class PipeGameLevelBuilder {
	
	public static PipeGameLevel build(String levelToBuild) {
		
		String[] rows = levelToBuild.split("\n");
		PipeGameLevel theNewLevel = new PipeGameLevel(rows.length, rows[0].length());
		
		for (int i = 0; i < theNewLevel.getNumOfRows(); i++) {
			char[] elements = rows[i].toCharArray();
			for (int j = 0; j < theNewLevel.getNumOfCols(); j++) {
				
				if (elements[j] == 's') {
					theNewLevel.setStart(new Point(i, j));
					theNewLevel.setPosition(theNewLevel.getStart());
				}
				if (elements[j] == 'g') 
					theNewLevel.setGoal(new Point(i, j));
				
				theNewLevel.setObject(i, j, elements[j]);
			}
		}
		
		return theNewLevel;
	}

}
