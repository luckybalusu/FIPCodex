package com.lakshmi.finra;

import java.io.File;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class DirectoryReader {
	String filepath = "C:/testdir";
	int numOfThreads = 5;
	public DirectoryReader(){
		
	}
	public DirectoryReader(String dirFilePath, int numOfThreads){
		this.filepath = dirFilePath;
		this.numOfThreads = numOfThreads;
	}
	public static void main (String args[]) throws InterruptedException, ExecutionException{
		DirectoryReader reader = new DirectoryReader();
		if(args.length>0){
			reader.filepath = args[0];
		}
		if(args.length>1){
			try{
				reader.numOfThreads=Integer.parseInt(args[1]);
			}catch(NumberFormatException nfe){
				//numOfThreads =5;
			}
		}

		reader.execute();
	}
	public boolean execute() throws InterruptedException, ExecutionException{
	    ExecutorService executorService = Executors.newFixedThreadPool(this.numOfThreads);
		File dirLocation = new File(this.filepath);
        if(!dirLocation.isFile()){
            for(File file :dirLocation.listFiles()){
            	Future<Boolean> status = executorService.submit(new FileProcessor(file.getAbsolutePath()));
            	if(status.get().booleanValue()){
            		System.out.println("Rename Succes for "+file.getAbsolutePath());
            	}else{
            		System.out.println("Rename Failed for "+file.getAbsolutePath());
            		return false;
            	}
            		
            	
            }
        }
        executorService.shutdown();
        return true;
	}
		
}
