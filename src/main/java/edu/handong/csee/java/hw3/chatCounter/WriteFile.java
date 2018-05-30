package edu.handong.csee.java.hw3.chatCounter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

public class WriteFile {

	String outputPath;

	
	static public void write(String outputPath) throws IOException{
		
		FileOutputStream output = new FileOutputStream(outputPath);
		
		Map<String, Integer> sortedDesc = SortByValue.sortByComparator(Displays.id_count, SortByValue.DESC);
//        SortByValue.printMap(sortedDesc); //displays result in descending order of count values
		
        for (Entry<String, Integer> entry : sortedDesc.entrySet())
        {
        	String str = "Kakao_id : " + (String)entry.getKey() + ", count : "+ String.valueOf(entry.getValue()) + "\n";
        	output.write(str.getBytes());
//            System.out.println("Kakao_id : " + entry.getKey() + ", count : "+ entry.getValue());
        }
        
        
		output.close();
	}
}
