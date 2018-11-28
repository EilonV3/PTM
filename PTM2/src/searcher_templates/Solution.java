package searcher_templates;

import java.util.ArrayList;

public class Solution<T> extends ArrayList<T> {
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (T state: this)
			sb.append(state.toString());
			
		return sb.toString();
	}

}
