package edu.handong.csee.java.hw3.chatCounter;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class SortByValue {
	
	public static boolean ASC = true;
	public static boolean DESC = false;
	
	
	/**
	 * This is a public class that sorts map by its value.
	 * @param unsortMap : unsorted list of result data (=id_count hashmap)
	 * @param order : true = ascending order, false = descending order
	 * @return : returns the sorted results
	 */
	public static Map<String, Integer> sortByComparator(Map<String, Integer> unsortMap, final boolean order)
    {

        List<Entry<String, Integer>> list = new LinkedList<Entry<String, Integer>>(unsortMap.entrySet()); //set unsorted list as linked list

        // Sorting the list based on values
        Collections.sort(list, new Comparator<Entry<String, Integer>>()
        {
        	//compare the values
        	/**
        	 * This method compares values and sorts.
        	 */
            public int compare(Entry<String, Integer> o1,
                    Entry<String, Integer> o2)
            {
                if (order)
                {
                    return o1.getValue().compareTo(o2.getValue());
                }
                else
                {
                    return o2.getValue().compareTo(o1.getValue());

                }
            }
        });

        // Maintaining insertion order with the help of LinkedList
        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        for (Entry<String, Integer> entry : list)
        {
            sortedMap.put(entry.getKey(), entry.getValue()); //put sorted data to sortedMap variable
        }

        return sortedMap;
    }

	/**
	 * This is a method that prints out the data inside Map.
	 * @param map : data will be passed through the parameter in Map type.
	 */
   /* public static void printMap(Map<String, Integer> map)
    {
        for (Entry<String, Integer> entry : map.entrySet())
        {
            System.out.println("Kakao_id : " + entry.getKey() + ", count : "+ entry.getValue());
        }
    }
*/

}
