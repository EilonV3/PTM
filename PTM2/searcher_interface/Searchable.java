package searcher_interface;

import java.util.ArrayList;

import pipeGame.server.PipeGameLevel;
import searcher_templates.State;

public interface Searchable<T> {
	
	boolean isGoalState(State<T> state);
	State<T> getStartState();
	ArrayList<State<T>> getPossibleStates(State<T> state);
	
}
