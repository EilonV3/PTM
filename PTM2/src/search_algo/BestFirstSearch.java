package search_algo;
import searcher_interface.*;
import searcher_templates.Solution;
import searcher_templates.State;

import java.util.List;

public class BestFirstSearch<T> extends PrioritySearcher<T> implements Searcher<T> {
    public BestFirstSearch(Distance distance) {
        super(distance);
    }

    @Override
    public Solution search(Searchable<T> searchable) {
        openList.add(searchable.getStartState());
        while (openList.size() > 0) {
            State<T> n = openList.poll();

            if (searchable.isGoalState(n)) {
                //System.out.println("Best-first: FOUND GOAL!");
                return backTrace(n);
            }

            List<State<T>> possibleStates = searchable.getPossibleStates(n);
            for (State<T> s : possibleStates) {
                if (!closedList.contains(s)) {
                    if (!openList.contains(s)) {
                        openList.add(s);
                    } else {
                        updateCostInPriorityQueue(s);
                    }
                }
            }

            closedList.add(n);
        }
        //System.out.println("Best-first: Can't find the path!");
        return null;
    }


}