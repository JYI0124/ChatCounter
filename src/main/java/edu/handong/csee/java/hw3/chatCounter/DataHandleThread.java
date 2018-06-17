package edu.handong.csee.java.hw3.chatCounter;

import java.io.IOException;


/**
 * This is a class that actually runs thread
 * @author 21300750, Jin, Young-In
 *
 */
public class DataHandleThread implements Runnable{
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			ReadFiles.ReadFilesInFolder();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
