package edu.handong.csee.java.hw3.chatCounter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * This is a class that open up folder and reads all the files inside the folder.
 * @author 21300750, Jin, Young-In
 *
 */
public class ReadFiles {
	
	static File rFolder;
	static String rFileName;
	static File rFileEntry;
	static String combineAddr; //String variable used for handling .csv files and .txt files separately
	
	
	/**
	 * This is a method that saves the data of folder, fileName, fileEntry variables
	 * @param folder : address of opening folder
	 * @param fileName : name of files in the folder
	 * @param fileEntry : variable used for reading files
	 */
	public static void setValues(File folder, String fileName, File fileEntry){
	      rFolder = folder;
	      rFileName = fileName;
	      rFileEntry = fileEntry;
	 }
	
	/**
	 *  This is a public method that opens all files that programmer wants to read in a folder.
	 *  This method discriminates files by there extension file name (.txt or .csv)
	 * @param folder : get folder address by parameter
	 * @throws IOException : exceptional handling for when an error occurs while using IO system
	 */
	public static void listFilesForFolder(final File folder) throws IOException{
		  
		ExecutorService executor = Executors.newFixedThreadPool(Integer.parseInt(Main.numOfThread));
		
		for(final File fileEntry : folder.listFiles()){ //for loop executes until the last file in the folder has been opened
			
			if(fileEntry.isDirectory()){ //if fileEntry is a directory
				listFilesForFolder(fileEntry); //call method again to open file
			}
			
			else{ //if fileEntry value is a file
				
				if(fileEntry.getName().contains(".csv") ||
						fileEntry.getName().contains(".txt")){ 
					//if the filename extension is .txt or .csv
					
					setValues(folder,fileEntry.getName(),fileEntry); //saving data
				      
					Runnable run = new DataHandleThread(); //Giving threads tasks
			               
					executor.execute(run); //running threads in thread pool

				}
			}
		}
		
		while(!executor.isTerminated()){   
	      }
		
		executor.shutdown(); //stop thread
	}
	
	/**
	 * This is a public method that actually read a file by file.
	 * @param folder : get directory of folder
	 * @param fileName : get fileName
	 * @param fileEntry : get fileEntry
	 * @throws IOException : exceptional handling for IO exceptions
	 */
	static public void ReadFilesInFolder() throws IOException{	
		
		combineAddr = rFolder.toString() + "\\" + rFileEntry.getName(); //string concatenation
		
		BufferedReader br = new BufferedReader(new FileReader(combineAddr)); //BufferedReader class instantiation to actually open file and read
		
		
		//below if clause is used for exceptional handling that will be implemented more when actually I deal with the data inside files
		if(rFileEntry.getName().contains(".csv"))
		{
			
			String line = br.readLine(); //get rid of the first line of the file since .csv files have Date, User, Message Strings on the first line of files
			
			while(true)
			{
				line = br.readLine(); //reading from the second line
				
				if(line == null) //when there is no more line to read
					break; //get out
				
				else
				{ //if there is a line to read
					
					 if(line.indexOf(",") == -1)
					 { //exceptional handling used to prevent commas in the message line from disappearing
				            continue;
				     }
					 
					 if(line.startsWith("2018-"))
					 {
						DataHandling.csvHandle(line);
					 }

				}
			}
		}
		//below if-else clause is used for exceptional handling that will be implemented more when actually I deal with the data inside files
		else if(rFileEntry.getName().contains(".txt"))
		{
			while(true)
			{
				String line = br.readLine(); //read from the first line
				//textfiles do not have Date, User, Message String on the first line
				
				if(line == null) //when there is no more line to read
					break; //get out
				
				else
				{
					DataHandling.txtHandle(line); 
				}
			}
		}	
	}
}

