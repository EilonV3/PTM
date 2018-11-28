package searcher_interface;

import searcher_templates.Solution;

public interface Searcher<T> {
	
	Solution search(Searchable<T> searchable);

}
