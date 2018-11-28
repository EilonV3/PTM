package test;

import java.util.ArrayList;
import java.util.List;

import pipeGame.server.PipeGameCacheManager;
import pipeGame.server.PipeGameClientHandler;
import pipeGame.server.PipeGameSearchable;
import pipeGame.server.PipeGameServer;
import pipeGame.server.PipeGameSolver;
import search_algo.BestFirstSearch;
import searcher_interface.Searchable;
import searcher_interface.Searcher;
import server_interface.CacheManager;
import server_interface.ClientHandler;
import server_interface.Server;
import server_interface.Solver;

// edit these imports according to your project
/*import algorithm.BestFS;
import algorithm.PipeGameSearchable;
import algorithm.Searchable;
import algorithm.Searcher;
import algorithm.Solution;
import algorithm.State;
import server.CacheManager;
import server.ClientHandler;
import server.MyCacheManager;
import server.MyClientHandler;
import server.MyServer;
import server.MySolver;
import server.Server;
import server.Solver;*/

public class TestSetter {
	
	public static void setClasses(DesignTest dt){
		
		// set the server's Interface, e.g., "Server.class"
		// don't forget to import the correct package e.g., "import server.Server"
		dt.setServerInteface(Server.class);
		// now fill in the other types according to their names
		// server's implementation
		dt.setServerClass(PipeGameServer.class);
		// client handler interface
		dt.setClientHandlerInterface(ClientHandler.class);
		// client handler class
		dt.setClientHandlerClass(PipeGameClientHandler.class);
		// cache manager interface
		dt.setCacheManagerInterface(CacheManager.class);
		// cache manager class
		dt.setCacheManagerClass(PipeGameCacheManager.class);
		// solver interface
		dt.setSolverInterface(Solver.class);
		// solver class
		dt.setSolverClass(PipeGameSolver.class);
		// searchable interface
		dt.setSearchableInterface(Searchable.class);
		// searcher interface
		dt.setSearcherInterface(Searcher.class);
		// your searchable pipe game class
		dt.setPipeGameClass(PipeGameSearchable.class);
		// your Best First Search implementation
		dt.setBestFSClass(BestFirstSearch.class);
	}
	
	// run your server here
	static Server s;
	public static void runServer(int port){
		s=new PipeGameServer(port);
		s.start(new PipeGameClientHandler(new PipeGameSolver(), new PipeGameCacheManager()));
	}
	// stop your server here
	public static void stopServer(){
		s.stop();
	}
	
	/* ------------- Best First Search Test --------------
	 * You are given a Maze
	 * Create a new Searchable from the Maze
	 * Solve the Maze
	 * and return a list of moves from {UP,DOWN,RIGHT,LEFT}
	 *  
	 */
	
	public static List<String> solveMaze(Maze m){
		
		return null;
	}

}
