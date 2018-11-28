package pipeGame.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import server_interface.CacheManager;
import server_interface.ClientHandler;
import server_interface.Instructions;
import server_interface.Solver;

public class PipeGameClientHandler implements ClientHandler{

	private Solver solver;
	private CacheManager cacheManager;
	
	public PipeGameClientHandler(Solver solver, CacheManager cacheManager) {
		this.solver = solver;
		this.cacheManager = cacheManager;
	}
	
	@Override
	public void handleClient(BufferedReader in, PrintWriter out) throws IOException {
		
		StringBuilder sb = new StringBuilder(in.readLine() + '\n');
		while (!(sb.toString().contains("done"))) {
			sb.append(in.readLine() + "\n");
		}
		
		String levelInput = sb.toString();
		levelInput.substring(0, levelInput.length()-5);
		
		PipeGameLevel requestedLevel = PipeGameLevelBuilder.build(levelInput);
		
		String result;
		
		try {
			result = this.cacheManager.load(levelInput).toString();
		} catch (NullPointerException ignored) {
			Instructions solvedLevel = solver.solve(requestedLevel);
			cacheManager.save(levelInput, solvedLevel.toString());
		}
		
		out.print(this.cacheManager.load(levelInput).toString());
		out.print("done");
//		try {
//			out.print(this.cacheManager.load(levelInput).toString());	
//		} catch (NullPointerException e1) {
//			 try {
//				Instructions solvedLevel = solver.solve(requestedLevel);
//				cacheManager.save(levelInput, solvedLevel.toString());
//			} catch (NullPointerException e2) {
//				out.print("done");
//			}
//		}
		
		//System.out.println("The client recieved an answer.");
		out.flush();
		out.close();
		in.close();
		while(true) {
			try {
				Thread.sleep(1000);
				System.out.println(Thread.currentThread().getId()+" is still awake. total theads:"+Thread.activeCount());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
