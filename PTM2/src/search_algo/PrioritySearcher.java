package search_algo;

import java.util.ArrayList;
import java.util.PriorityQueue;
import searcher_interface.Distance;
import searcher_templates.State;

public class PrioritySearcher<T> extends CommonSearcher<T> {
	PriorityQueue<State<T>> openList;
	
	public PrioritySearcher(Distance distance) {
		this.openList = new PriorityQueue<>((obj1, obj2)->{
			if (distance.calcDistance(obj1) < distance.calcDistance(obj2)) return -1;
			else if (distance.calcDistance(obj1) > distance.calcDistance(obj2)) return 1;
			else return 0;
		});
	}
	
	 void updateCostInPriorityQueue(State<T> newState) {
	        ArrayList<State<T>> poppedStates = new ArrayList<>();

	        while (openList.size() != 0 && !openList.peek().equals(newState) ) {
	            poppedStates.add(openList.remove());
	        }

	        if (openList.peek().getCost() > newState.getCost()) {
	            openList.remove();
	            openList.add(newState);
	        }
	        
	        openList.addAll(poppedStates);
	 }
}
