package pipeGame.server;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import searcher_interface.Searchable;
import searcher_templates.State;

public class PipeGameSearchable implements Searchable<PipeGameLevel> {
	
	private State<PipeGameLevel> startState;
	
	public PipeGameSearchable(PipeGameLevel level) {
		this.startState = new State<>(level);
	}
	
	@Override
	public boolean isGoalState(State<PipeGameLevel> state) {
		char positionToCheck = state.getState().getObjectInPosition();
		return positionToCheck == 'g';
	}

	@Override
	public State<PipeGameLevel> getStartState() {
		return this.startState;
	}
	
	public boolean isOutOfBound(int i, int j) {
		if (i < 0 || i >= startState.getState().getNumOfRows() || j < 0 || j >= startState.getState().getNumOfCols())
			return true;
		else return false;
	}
	
	public boolean isEmptySpace (Point position, PipeGameLevel level) {
		return level.getObjectInPosition(position.x, position.y) == ' ';
	}
	
	public boolean isCurvedPipe (Point position, PipeGameLevel level) {
		char positionToCheck = level.getObjectInPosition(position.x, position.y);
		if (positionToCheck == 'L' || positionToCheck == 'F' || positionToCheck  == '7' || positionToCheck == 'J')
			return true;
		else return false;
	}
	
	public Boolean isGoal(Point position, PipeGameLevel level) {
		char positionToCheck = level.getObjectInPosition(position.x, position.y);
		return positionToCheck == 'g';
	}

	
	/*============================================================*/
	
	
	public enum Move {UP, DOWN, LEFT, RIGHT}
	
	@Override
	public ArrayList<State<PipeGameLevel>> getPossibleStates(State<PipeGameLevel> state) {
		
		ArrayList<State<PipeGameLevel>> possibleStates = new ArrayList<>();
		List<Move> nextMoves = this.nextMoves(state.getState());
		
		for (Move m: nextMoves)
			AddStateIfPossible(possibleStates, state, m);
		
		for (State<PipeGameLevel> s: possibleStates) {
			s.setCost(state.getCost() + 1);
			s.setFather(state);
		}
			
		return possibleStates;
	}
	
	private List<Move> nextMoves (PipeGameLevel level){
		
		ArrayList<Move> nextMoves = new ArrayList<>();
		char positionToCheck = level.getObjectInPosition(level.getRow(), level.getCol());
		
		switch(positionToCheck) {
			
			case 's':
				nextMoves.add(Move.UP);
				nextMoves.add(Move.DOWN);
				nextMoves.add(Move.LEFT);
				nextMoves.add(Move.RIGHT);
				break;
			case 'L':
				nextMoves.add(Move.UP);
				nextMoves.add(Move.RIGHT);
			case 'F':
				nextMoves.add(Move.DOWN);
				nextMoves.add(Move.RIGHT);
                break;
            case '7':
            	nextMoves.add(Move.DOWN);
            	nextMoves.add(Move.LEFT);
                break;
            case 'J':
            	nextMoves.add(Move.UP);
            	nextMoves.add(Move.LEFT);
                break;
            case '|':
            	nextMoves.add(Move.DOWN);
            	nextMoves.add(Move.UP);
                break;
            case '-':
            	nextMoves.add(Move.LEFT);
            	nextMoves.add(Move.RIGHT);
                break;
		}
		
		return nextMoves;
	}
	
	private void AddStateIfPossible(List<State<PipeGameLevel>> list, State<PipeGameLevel> state, Move cameFrom) {
		
		int row = state.getState().getRow();
		int col = state.getState().getCol();
		Point position = goToPosition(row, col, cameFrom);
		
		if(isOutOfBound(position.x, position.y)) return;
		
		if(state.getFather() != null)
			if(position.x == state.getFather().getState().getRow() && position.y == state.getFather().getState().getCol())
				return;
		
		if (isEmptySpace(position, state.getState())) return;
		
		PipeGameLevel tmpLevel = new PipeGameLevel(state.getState());
		
		if (isGoal(position, state.getState())) {
			tmpLevel.setRow(position.x);
			tmpLevel.setCol(position.y);
			list.add(new State<>(tmpLevel));
			return;
		}
		
		tmpLevel.setRow(position.x);
		tmpLevel.setCol(position.y);
		
		switch(cameFrom) {
			
			case UP:
				if (isCurvedPipe(position, state.getState())) {
					tmpLevel.setObject(position.x, position.y, 'F');
					list.add(new State<>(new PipeGameLevel(tmpLevel)));
					tmpLevel.setObject(position.x, position.y, '7');
					list.add(new State<>(new PipeGameLevel(tmpLevel)));
				}
				else {
					tmpLevel.setObject(position.x, position.y, '|');
					list.add(new State<>(new PipeGameLevel(tmpLevel)));
				}
				break;
				
			case DOWN:
				if (isCurvedPipe(position, state.getState())) {
					tmpLevel.setObject(position.x, position.y, 'J');
					list.add(new State<>(new PipeGameLevel(tmpLevel)));
					tmpLevel.setObject(position.x, position.y, 'L');
					list.add(new State<>(new PipeGameLevel(tmpLevel)));
				}
				else {
					tmpLevel.setObject(position.x, position.y, '|');
					list.add(new State<>(new PipeGameLevel(tmpLevel)));
				}
				break;
				
			case LEFT:
				if (isCurvedPipe(position, state.getState())) {
					tmpLevel.setObject(position.x, position.y, 'F');
					list.add(new State<>(new PipeGameLevel(tmpLevel)));
					tmpLevel.setObject(position.x, position.y, 'L');
					list.add(new State<>(new PipeGameLevel(tmpLevel)));
				}
				else {
					tmpLevel.setObject(position.x, position.y, '-');
					list.add(new State<>(new PipeGameLevel(tmpLevel)));
				}
				break;
				
			case RIGHT:
				if (isCurvedPipe(position, state.getState())) {
					tmpLevel.setObject(position.x, position.y, '7');
					list.add(new State<>(new PipeGameLevel(tmpLevel)));
					tmpLevel.setObject(position.x, position.y, 'J');
					list.add(new State<>(new PipeGameLevel(tmpLevel)));
				}
				else {
					tmpLevel.setObject(position.x, position.y, '-');
					list.add(new State<>(new PipeGameLevel(tmpLevel)));
				}
				break;
					
		}
	}
	
	private Point goToPosition(int row, int col, Move direction) {
		
		switch(direction) {
			case UP: return new Point(row-1, col);
			case DOWN: return new Point(row+1, col);
			case LEFT: return new Point(row, col-1);
			case RIGHT: return new Point(row, col+1);
		}
		return null;
	}

}
