package edu.handong.csee.java.hw3.chatCounter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;


/**
 * This is a class that writes output of the program on the output File
 * @author 21300750, Jin, Young-In
 *
 */
public class WriteFile {

	String outputPath;

	/**
	 * This method allows the program to write file.
	 * The data is sorted in Map type variable.
	 * @param outputPath : outputPath that was set from CLI command is passed to this method for FileOutputStream
	 * @throws IOException : exceptional handling for IO errors
	 */
	static public void write(String outputPath) throws IOException{
		
		FileOutputStream output = new FileOutputStream(outputPath);
		
		Map<String, Integer> sortedDesc = SortByValue.sortByComparator(Displays.id_count, SortByValue.DESC); //sort value data in descending order of
		
        for (Entry<String, Integer> entry : sortedDesc.entrySet())
        {
        	String str = "Kakao_id : " + (String)entry.getKey() + ", count : "+ String.valueOf(entry.getValue()) + "\n";
        	output.write(str.getBytes()); //writes on output file
        }
        
		output.close(); //close file
	}
}
