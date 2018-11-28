package searcher_templates;

import java.util.Objects;

public class State<T> {
	private T state;
	private State<T> father;
	private int cost;
	
	public State(T state) {
		this.state = state;
		this.father = null;
		this.cost = 0;
	}
	
	public State(T state, State<T> father, int cost) {
		this.state = state;
		this.father = father;
		this.cost = cost;
	}

	public T getState() {
		return state;
	}

	public void setState(T state) {
		this.state = state;
	}

	public State<T> getFather() {
		return father;
	}

	public void setFather(State<T> father) {
		this.father = father;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}
	
	@Override
	public String toString() {
		return this.state.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || this.getClass() != obj.getClass()) return false;
		State<?> tmpState = (State<?>) obj;
		
		return Objects.equals(this.state, tmpState.state);
	}
	
	@Override
	public int hashCode() {
		return this.state.hashCode();
	}
	
	public boolean hasFather() {
		if (this.father != null) return true;
		else return false;
	}

}
