package pipeGame.server;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.io.*;

import server_interface.ClientHandler;
import server_interface.Server;

public class PipeGameServer implements Server {

	private int port;
	private ServerSocket server;
	private volatile boolean stop;
	private PriorityExecutor<PipeGameTask> priorityExecutorService;
	// Create PipeGameTask!!!

	public PipeGameServer(int port) {
		this.port = port;
		this.server = null;
		this.stop = true;
		 System.out.println("Ready to start!");

		priorityExecutorService = new PriorityExecutor<>(Executors.newFixedThreadPool(4),
				new PriorityBlockingQueue<>());
		System.out.println("reached here");
	}


    @Override
    public void start(ClientHandler clientHandler) {
        priorityExecutorService.activeExecutor();
        try {
            this.server = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stop = false;
        System.out.println("Server is alive and running on port " + server.getLocalPort());

        try {
            server.setSoTimeout(1000);
        } catch (SocketException e) {
            e.printStackTrace();
        }

        new Thread(()-> {
            try {
                run(clientHandler);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    @Override
    public void stop()  {
        this.stop = true;
        this.priorityExecutorService.shutdownExecutor();
        System.out.println("Server is down.");
    }


    private void run(ClientHandler clientHandler) throws IOException {
        while (!stop){
            try {
                //System.out.print(".");
                Socket socket = this.server.accept();
                new Thread(() -> {
                    try {
                        BufferedInputStream socketInputStream = new BufferedInputStream(socket.getInputStream());
                        socketInputStream.mark(0);
                        System.out.println(socketInputStream.markSupported());
                        BufferedReader in = new BufferedReader(new InputStreamReader(socketInputStream));
                       
                        // Getting Priority according to size of the matrix
                        int GameSize = 0;
                        String line = in.readLine();
                        int column = line.length();
                        int row = 0;
                        while(!line.equals("done")) {
                            row++;
                            line = in.readLine();
                        }
                        GameSize = column*row;
                        socketInputStream.reset();

                        System.out.println("\nNew client on port " + socket.getPort() + " Size: " + GameSize);
                        priorityExecutorService.add(new PipeGameTask(() -> {
                            try {
                                clientHandler.handleClient(in, new PrintWriter(socket.getOutputStream()));
                                in.close();
                                socket.close();
                            } catch(IOException ignored) {}
                        }, GameSize));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }).start();
            }catch (SocketTimeoutException ignored) { }
        }

        server.close();
    }


	// Main method to check the server
	public static void main(String[] args) {
		PipeGameServer myServer = new PipeGameServer(6400);
		myServer.start(new PipeGameClientHandler(new PipeGameSolver(), new PipeGameCacheManager()));
	}

}