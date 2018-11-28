package pipeGame.server;

import searcher_interface.Distance;
import searcher_templates.State;

public class PipeGameManhattanDistance implements Distance<PipeGameLevel> {

	@Override
	public double calcDistance(State<PipeGameLevel> stateToCalc) {
		int posX = stateToCalc.getState().getPosition().x;
		int posY = stateToCalc.getState().getPosition().y;
		int goalX = stateToCalc.getState().getGoal().x;
		int goalY = stateToCalc.getState().getGoal().y;
		
		double manhattanDistance = Math.abs(posX-goalX) + Math.abs(posY-goalY);
		return manhattanDistance;
	}

}
