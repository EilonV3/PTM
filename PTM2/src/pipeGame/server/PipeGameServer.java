package pipeGame.server;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import server_interface.ClientHandler;
import server_interface.Server;

public class PipeGameServer implements Server {
	
	private int port;
	private ServerSocket server;
	private volatile boolean stop;
	
	public PipeGameServer(int port) {
		this.port = port;
		this.server = null;
		this.stop = true;
		//System.out.println("Ready to start!");
	}
	
	@Override
	public void start(ClientHandler clientHandler) {
		try {
			this.server = new ServerSocket(this.port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		stop = false;
		//System.out.println("Server is online and waiting...// port: " + server.getLocalPort());
		
		try {
			server.setSoTimeout(1000);
		} catch (SocketException e) {
			e.printStackTrace();
		}
		
		new Thread(()->run(clientHandler)).start();

	}
	
	private void run(ClientHandler clientHandler) {
		
		while (!this.stop) {
				try {
					Socket socket = server.accept();
					new Thread(()->{
					try {
						BufferedReader in;
						in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
						PrintWriter out = new PrintWriter(socket.getOutputStream());
						
						clientHandler.handleClient(in, out);
						//socket.getInputStream().close();
						//socket.getOutputStream().close();
						socket.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					}).start();
				} catch (IOException e) {}
		}
		try {
			server.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void stop() {
		this.stop = true;
		//System.out.println("Server is offline.");
	}
	
	// Main methode to check the server
	public static void main(String[] args) {
		PipeGameServer myServer = new PipeGameServer(6400);
		myServer.start(new PipeGameClientHandler(new PipeGameSolver(),new PipeGameCacheManager()));
	}

}
