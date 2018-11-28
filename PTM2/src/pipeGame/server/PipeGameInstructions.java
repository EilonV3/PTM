package pipeGame.server;

import java.util.ArrayList;

import searcher_templates.Solution;
import server_interface.Instructions;

public class PipeGameInstructions extends ArrayList<String> implements Instructions {
	
	public PipeGameInstructions() {
	}
	
	public PipeGameInstructions(Solution<PipeGameLevel> solution) {
		for (PipeGameLevel state: solution)
			this.add(state.toString());
		
	}
	
	public PipeGameInstructions(Solution<PipeGameLevel> solution, PipeGameLevel requestedLevel) {
		for(PipeGameLevel level: solution) {
			int x = level.getRow();
			int y = level.getCol();
			
			char pipe1 = level.getObjectInPosition();
			char pipe2 = requestedLevel.getObjectInPosition(x,y);
			
			if(pipe1 != 's' && pipe1 != 'g' && pipe1 != ' ' && pipe1 != pipe2)
				this.add(x + "," + y + "," + timesToRotate(pipe2, pipe1));
		}
	}
	
	private int timesToRotate(char cRequested, char cSolution) {
		if (cSolution == cRequested) return 0;
		
		switch(cRequested) {
		
		case '-':
			return 1;
			
		case '|':
			return 1;
			
		case 'L':
			switch(cSolution) {
			case 'F': return 1;
			case '7': return 2;
			case 'J': return 3;
			}
			break;
			
		case 'F':
			switch(cSolution) {
			case '7': return 1;
			case 'J': return 2;
			case 'L': return 3;
			}
			break;
			
		case '7':
			switch(cSolution) {
			case 'J': return 1;
			case 'L': return 2;
			case 'F': return 3;
			}
			break;
			
		case 'J':
			switch(cSolution) {
			case 'L': return 1;
			case 'F': return 2;
			case '7': return 3;
			}
			break;
			
		}
		return 0;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (String stringInArray: this)
			sb.append(stringInArray).append("\n");
		sb.append("done");
		return sb.toString();
	}

}
