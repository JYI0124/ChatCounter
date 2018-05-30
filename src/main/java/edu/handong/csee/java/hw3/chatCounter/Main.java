package edu.handong.csee.java.hw3.chatCounter;

import java.io.File;
import java.io.IOException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

/**
 * 
 * @author 21300750 Jin, Young-In
 *
 *This is a Main class that has main method in it.
 *File directory is set here.
 *Then, program executes through main method.
 *
 */

public class Main {
	
	static File folder;// = new File("C:\\git\\ChatCounter"); //File class instantiation with directory address of files that needs to be read. Set as final since the directory won't be changed.
	boolean verbose;
	boolean help;
	String inputPath;
	String outputPath;
	
	
	/**
	 * This is a main method that actually runs the program. 
	 * @param args
	 * @throws IOException : Exceptional handling for when an error occurs while using IO system
	 */
public static void main(String[] args) throws IOException{

		Main m = new Main();
		m.run(args);
	}


	private void run(String [] args) throws IOException{
		Options options = createOptions();
		
		if(parseOptions(options, args)){
			
			if(help){
				printHelp(options);
				return;
			}
			
			folder = new File(inputPath);
			ReadFiles.listFilesForFolder(folder);
			
			Displays.id_Redundancy_Check(DataHandling.gather_data); //checks if there are redundant messages
			
			WriteFile.write(outputPath); 
			
			
			if(verbose){
				
				System.out.println("Your program is terminated. (This message is shown because you turned on -v option!");
			}
		}
	}
	
	private boolean parseOptions(Options options, String [] args){
		CommandLineParser parser = new DefaultParser();
		
		try{
			CommandLine cmd = parser.parse(options, args);
			
			verbose = cmd.hasOption("v");
			help = cmd.hasOption("h");
			inputPath = cmd.getOptionValue("i");
			outputPath = cmd.getOptionValue("o");
			
		} catch (Exception e) {
			printHelp(options);
			return false;
		}
		
		return true;
	}
	
	
	private Options createOptions(){
		Options options = new Options();
		
		options.addOption(Option.builder("h").longOpt("help")
				.desc("Help")
				.build());
		
		options.addOption(Option.builder("i").longOpt("inputdir")
				.desc("Set a directory path that contains input files")
				.hasArg()
				.argName("Directory path")
				.required()
				.build());
		
		options.addOption(Option.builder("o").longOpt("outputdir")
				.desc("Set a directory path that writes out file")
				.hasArg()
				.argName("Output Directory path")
				.required()
				.build());
		
		return options;
	}
	
	private void printHelp(Options options){
		HelpFormatter formatter = new HelpFormatter();
		String header = "CLI test program";
		String footer = "\nPlease report issues at https://github.com/JYI0124/ChatCounter";
		formatter.printHelp("CLIExample", header, options, footer, true);
	}


}
