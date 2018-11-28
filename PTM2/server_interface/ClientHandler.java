package server_interface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public interface ClientHandler {
	
	void handleClient(BufferedReader in, PrintWriter out) throws IOException;

}
