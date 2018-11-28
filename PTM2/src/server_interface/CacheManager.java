package server_interface;

import java.io.IOException;

public interface CacheManager {
	public Instructions load (String problem);
	public void save(String problem, String solution) throws IOException;

}
