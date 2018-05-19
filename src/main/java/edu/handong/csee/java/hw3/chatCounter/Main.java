package edu.handong.csee.java.hw3.chatCounter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
/*
My implementation is done until the functionality where the class can only read files in the folder.
To show that the class is actually reading files, I have put System.out.println() methods.
I have implemented only a single class for intermediate submission.
*/

/**
 * 
 * @author 21300750 Jin, Young-In
 *
 *This is a Main class that has main method in it.
 *Other than main method, there are two methods, one for reading files in a folder, and one for read a specific file.
 */
public class Main {
	
	static List<String> filenames = new LinkedList<String>(); //LinkedList used for checking whether the program is reading right files
	final static File folder = new File("C:\\git\\ChatCounter"); //File class instantiation with directory address of files that needs to be read. Set as final since the directory won't be changed.
	static String combineAddr; //String variable used for handling .csv files and .txt files separately
	
	/**
	 *  This is a public method that all files that programmer wants to read in a folder
	 * @param folder : get folder address by parameter
	 * @throws IOException : exceptional handling for when an error occurs while using IO system
	 */
	public static void listFilesForFolder(final File folder) throws IOException{
		
		
		for(final File fileEntry : folder.listFiles()){ //for loop executes until the last file in the folder has been opened
			
			if(fileEntry.isDirectory()){ //if fileEntry is a directory
				listFilesForFolder(fileEntry); //call method again to open file
			}
			
			else{ //if fileEntry value is a file
				
				if(fileEntry.getName().contains(".csv") ||
						fileEntry.getName().contains(".txt")){ 
					//if the filename extension is .txt or .csv
					
					filenames.add(fileEntry.getName()); //put file name in linkedList
					//above was done just for checking whether right file has been read
					
					readAFile(fileEntry.getName(), fileEntry); //method call with sending the name of file and file path address
				}
			}
		}
	}
	
	/**
	 * This is a public method that reads a single file line by line.
	 * @param fileName : fileName is fileEntry.getName() value
	 * @param fileEntry : file path address
	 * @throws IOException : exceptional handling for when an error occurs while using IO system
	 */
	public static void readAFile(final String fileName, final File fileEntry) throws IOException{
		
		combineAddr = folder.toString() + "\\" + fileEntry.getName(); //string concatenation
		// ex) combineAddr = "C:\\git\\ChatCounter\\JavaProgramming-L2"
		
		System.out.println();
		System.out.println();
		System.out.println(combineAddr); //check whether combineAddr has right directory
		System.out.println();
		System.out.println();
		
		
		BufferedReader br = new BufferedReader(new FileReader(combineAddr)); //BufferedReader class instantiation to actually open file and read
		
		
		//below if clause is used for exceptional handling that will be implemented more when actually I deal with the data inside files
		if(fileEntry.getName().contains(".csv")){
			
			String line = br.readLine(); //get rid of the first line of the file since .csv files have Date, User, Message Strings on the first line of files
			
			while(true){
				line = br.readLine(); //reading from the second line
				
				if(line == null) //when there is no more line to read
					break; //get out
				
				else{ //if there is a line to read
					
					System.out.println(line); //display line on the CLI to show that the program is actually reading some files
				}
			}
		}
		//below if-else clause is used for exceptional handling that will be implemented more when actually I deal with the data inside files
		else if(fileEntry.getName().contains(".txt")){
			while(true){
				String line = br.readLine(); //read from the first line
				//textfiles do not have Date, User, Message String on the first line
				
				if(line == null) //when there is no more line to read
					break; //get out
				
				else{
					
					System.out.println(line); //display line on the CLI to show that the program is actually reading some files
				}
			}
		}		
	}
	

	/**
	 * This is a main method that actually runs the program. 
	 * @param args
	 * @throws IOException : Exceptional handling for when an error occurs while using IO system
	 */
public static void main(String[] args) throws IOException{

		listFilesForFolder(folder); //listFilesForFolder method call with passing the directory address of folder in the parameter
	}
}
