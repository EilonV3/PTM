package search_algo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import searcher_templates.Solution;
import searcher_templates.State;

public class CommonSearcher<T> {
	
	List<State<T>> closedList;
	
	public CommonSearcher() {
		this.closedList = new ArrayList<>();
	}
	
	public Solution<T> backTrace(State<T> stateToBackTrace){
		Solution<T> solution= new Solution<>();
		State<T> tmpState = stateToBackTrace;
		
		while (tmpState.getFather() != null) {
			solution.add(tmpState.getState());
			tmpState = tmpState.getFather();
		}
		
		solution.add(tmpState.getState());
		Collections.reverse(solution);
		
		return solution;
	}
	
}
