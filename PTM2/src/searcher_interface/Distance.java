package searcher_interface;

import searcher_templates.State;

public interface Distance<T> {
	double calcDistance(State<T> stateToCalc);
}
