package pipeGame.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import server_interface.CacheManager;
import server_interface.Instructions;

public class PipeGameCacheManager implements CacheManager {
	
	String path;
	
	public PipeGameCacheManager() {
		this.path = "./cache/";
		new File(path).mkdirs();
	}

	@Override
	public Instructions load(String problem) {
		PipeGameInstructions loadedInstructions = new PipeGameInstructions();
		try {
			Scanner loader = new Scanner(new FileInputStream(new File(path + Integer.toString(problem.hashCode()))));
			
			while (loader.hasNextLine())
				loadedInstructions.add(loader.nextLine());
			
			loader.close();
			return loadedInstructions;
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return null;
	}

	@Override
	public void save(String problem, String solution)  {
		FileOutputStream saver;
		try {
			saver = new FileOutputStream(new File(path + Integer.toString(problem.hashCode())));

		saver.write(solution.getBytes());
		saver.flush();
		saver.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}

	}
	
	/*public static void main(String[] args) {
		
		PipeGameCacheManager cache = new PipeGameCacheManager();
		try {
			cache.save("s-7L\n||F7\nF--|\n-gLF", "shit got real");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(cache.load("s-7L\n||F7\nF--|\n-gLF").toString());
	}*/

}
