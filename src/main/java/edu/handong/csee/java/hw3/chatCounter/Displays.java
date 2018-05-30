package edu.handong.csee.java.hw3.chatCounter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


/**
 * This is a public class that checks redundancy of data with id_Redundancy_Check method, and display results in unsorted order.
 * @author 21300750, Jin, Young-In
 *
 */
public class Displays {
	
	//since all redundant data are filtered in gather_data hashmap,
	static HashMap <String, Integer> id_count = new HashMap <String, Integer>(); //this hashmap variable pulls out only id information from gather data.
	//if there is a same id, count value is increased by 1
		
	/**
	 * This is a public method that checks redundancy of data.
	 * Data are given from HashMap gather variable, declared in DataHandling class
	 * @param gather : is a HashMap variable declared in DataHandling class
	 */
	public static void id_Redundancy_Check(HashMap <String, Integer> gather){
		
		Set mapSet = (Set) DataHandling.gather_data.entrySet(); //set gather_data hashmap as entry set
		Iterator it = mapSet.iterator(); //iterator that actually allows loop function in the hashmap
		
		while(it.hasNext()){ //when there is more data
			
			Map.Entry me = (Map.Entry) it.next(); //get an entry from a map
			
			String line = (String) me.getKey(); //get key value of entry
			
			//line is composed of date + id + message
			
			String [] arr_line = line.split(" ", 4); //split the value to 4 partitions by using white-spaces as delimiter
			
			for(int i = 0; i < arr_line.length; i++){
				
				if(i == 2){ //get names from line
										
					if(!(id_count.containsKey(arr_line[i]))) //if name is not in the list
					{
						id_count.put(arr_line[i], 1); //add name to list and set count value as 1
					}
					
					else{ //if there exist the name in the list,
						id_count.put(arr_line[i], id_count.get(arr_line[i]) + 1); //increase count value by 1
					}
				}
			}
		}
		
		
	}

	/**
	 * This is a public method that shows users the result of id and how many messages the id has sent (excluding redundant message)
	 * @param id_count : is a HashMap variable that counts the message that certain id has sent
	 */
	static public void get_Id_Counts(HashMap <String, Integer> id_count){
		
		int num = 1; //counting number of ids total
		
		Set mapSet = (Set) id_count.entrySet();
		Iterator it = mapSet.iterator();
		
		while(it.hasNext()){
			
			Map.Entry me = (Map.Entry) it.next();
			
			System.out.println((num++) +". Kakao_id : " + me.getKey() +  ", count : " + me.getValue()); //prints id info and count value info out of hashmap
		}
	}
}
