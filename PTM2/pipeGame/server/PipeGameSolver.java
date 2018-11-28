package pipeGame.server;

import search_algo.BestFirstSearch;
import searcher_interface.Searcher;
import searcher_templates.Solution;
import server_interface.Solver;

public class PipeGameSolver implements Solver<PipeGameLevel> {

	@Override
	public PipeGameInstructions solve(PipeGameLevel problem) {
		
		Searcher<PipeGameLevel> bestFirstSearch = new BestFirstSearch<>(new PipeGameManhattanDistance());
		Solution<PipeGameLevel> solution;
		
		solution = bestFirstSearch.search(new PipeGameSearchable(problem));
		PipeGameInstructions ProblemInstructions = new PipeGameInstructions(solution, problem);
		
		//System.out.println(ProblemInstructions.toString());
		return ProblemInstructions;
	}
	
	/* public static void main(String[] args) {
	        PipeGameSolver mySolver = new PipeGameSolver();
	        PipeGameLevel level = PipeGameLevelBuilder.build(
	                		 "s|L \n" +
	                         " |L7\n" +
	                         " F -\n" +
	                         "7F-J\n" +
	                         " 7g-");
	        
	        System.out.println("You ask for solution to: ");
	        System.out.println(level);

	        try {
	            long startTime = System.nanoTime();
	            PipeGameInstructions vectors = mySolver.solve(level);
	            long endTime = System.nanoTime();
	            // put here something to check
	            long duration = (endTime - startTime);
	            Long ms = duration / 1000000;
	            Double sec = (double) duration / 1000000000.0;

//	            System.out.println("TOTAL: " + ms + "ms" + " (" + sec + "sec)");
//	            System.out.println("\nThe vectors backtrace is:");

	            if (vectors != null) {
	            System.out.println(vectors.toString());
	            }
	            
	        } catch (NullPointerException ignored) {
	        }


	    }*/

}
