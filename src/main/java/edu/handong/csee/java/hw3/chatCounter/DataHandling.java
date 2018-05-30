package edu.handong.csee.java.hw3.chatCounter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class is parsing data.
 * 
 * @author 21300750, Jin, Young-In
 *
 */
public class DataHandling {
	
	static ArrayList <String> csvList = new ArrayList <String>(); //saves all String data from csv files
	static ArrayList <String> txtList = new ArrayList <String>(); //saves all String data from txt files
	static HashMap <String, Integer> gather_data = new HashMap <String, Integer>(); //merging cvs data and txt data; redundant message are all removed
	
	//below are variables used for data handling/parsing
	static String id = "";
	static String msg = "";
	static String date = "";
	static String year = "";
	static boolean check = false;
	static String data = "";
	static String temp;
	
	/**
	 * This method changes format of csv data so that it can be compared with txt data
	 * @param line : line values will be sent from ReadFiles class
	 */
	static public void csvHandle(String line){
		
		csvList.add(line);
		
		//below works are done to separate input line to date, id and msg variables
		int idx = line.indexOf(","); 
		date = line.substring(0, idx);
		temp = line.substring(idx+1, line.length());
		
		date = date.substring(0, date.length() - 3);
		
		idx = temp.indexOf(",");
		id = temp.substring(1, idx - 1);
		msg = temp.substring(idx + 2, temp.length() - 1);
		
		if(msg.contains("\n")){
			idx = temp.indexOf("\n");
			msg = temp.substring(0, idx);
		}
		
		
		if(msg.equals("Photo")) //exceptional handling
			msg = "사진";
		
		
		data = date + " " + id + " " + msg; //after some changes in format of data, data is set in date + id + message form.

		//checking redundancy
		if(!gather_data.containsKey(data)){ //if data is not in the list 
			gather_data.put(data, 1);  //put data info in the list and set count value as 1
		}
		
		else{ //if data is in the list
			gather_data.put(data, gather_data.get(data)); //increase count value by 1
			
			//however, there shouldn't be a redundant data, since Hashmap filters redundant key values (key = messages).
			//this action is done just for exceptional error handling. 
		}
		
	}
	
	/**
	 * This is a method that changes format of text file data so taht it can be compared with csv data
	 * @param line : line values will be sent from ReadFile class
	 */
	static public void txtHandle(String line){
		
		 // below actions in if clause are for changing text file date format to csv file date format for comparation that is done later
        if(line.contains("---------------"))
        {
           int idx = line.indexOf("2018");
           line = line.substring(idx, line.length());
           
           idx = line.indexOf("---------------");
           line = line.substring(0, idx);
           
           line = line.replaceAll("\\s", "");
           line = line.replace("년", "-");
           line = line.replace("월", "-");
           
           idx = line.indexOf("일");
           line = line.substring(0, idx);
           year = line;
           
           idx = line.indexOf("-");
           line = line.substring(idx+1, line.length());
           
           idx = line.indexOf("-");
           String month = line.substring(0, idx);
           
           if(Integer.parseInt(month) < 10)
           	month = "0"+month;
           
           String day = line.substring(idx+1, line.length());
           
           if(Integer.parseInt(day) < 10)
           	day = "0"+day;
           
           year = "2018-" + month + "-" + day;
           
        }
        
	    else //changing data format regarding id and message information
	    {
	           if(line.startsWith("[")){
	           	txtList.add(line);
	           	
	           	int idx = line.indexOf("]");
	           	id = line.substring(1, idx);
	           	
	           	line = line.substring(idx+2, line.length());
	           	
	           	idx = line.indexOf("]");
	           	date = line.substring(1, idx);
	           
	           	msg = line.substring(idx+2, line.length());
	           	
	           	
	           	if(msg.contains("\n")){
	           		idx = line.indexOf("\n");
	           		msg = line.substring(0, idx);
	           	}
	           	
	           	if(date.startsWith("오후")){
	           		//if date is 오후, excluding 오후 12시, plus 12 to all 오후 hours
	           		idx = date.indexOf(" ");
	           		date = date.substring(idx+1, date.length());
	           		idx = date.indexOf(":");
	           		
	           		String hour = date.substring(0, idx);
	           		date = date.substring(idx+1, date.length());
	           		
	           		int num = Integer.parseInt(hour);
	           		
	           		if(num != 12){ //hour cannot be 24. hour can be only up to 23.
	           			num = num + 12;
	           		}
	           		
	           		hour = String.valueOf(num);
	           		
	           		date = hour + ":" + date;
	           	}
	           	
	           	else if(date.startsWith("오전")){
	           		//if date is 오전, change 오전 12시 to 0시
	           		idx = date.indexOf(" ");
	           		date = date.substring(idx+1, date.length());
	           		idx = date.indexOf(":");
	           		
	           		String hour = date.substring(0, idx);
	           		date = date.substring(idx+1, date.length());
	           		
	           		int num = Integer.parseInt(hour);
	           		
	           		if(num == 12){
	           			num = 0;
	           		}
	           		
	           		if(num < 10){ //exceptional handling; txt files show hours(0~9) in one digit format, but csv files has 0 infront of hours even if they are one digits. 
	           			
	           			hour = String.valueOf(num);
	           			hour = "0" + hour;
	           		}
	           		
	           		else{
	                  		hour = String.valueOf(num);
	           		}
	           		
	           		date = hour + ":" + date;	
	           	}
	           	
	           	date = year + " " + date;
	           	
	           	data = date + " " + id + " " + msg; //setting right format so that text data can be compared with csv data
	           	
	           	if(!gather_data.containsKey(data)){ //if data is not in the list
	           		gather_data.put(data, 1); //put data in the list and set count value by 1
				}
					
				else{ //if data is not in the list
					gather_data.put(data, gather_data.get(data)); //increase count value by 1
					//however, there shouldn't be a redundant data, since Hashmap filters redundant key values (key = messages).
					//this action is done just for exceptional error handling. 
				}
	        }
        }
        
        
	}
}
