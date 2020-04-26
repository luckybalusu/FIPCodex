package com.lakshmi.finra;

import static org.junit.Assert.fail;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import org.junit.Test;

public class TestFileProcessor {
	public String generateRandomString(){
	    int leftLimit = 65; // letter 'a'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 10;
	    Random random = new Random();
	    StringBuilder buffer = new StringBuilder(targetStringLength);
	    for (int i = 0; i < targetStringLength; i++) {
	        int randomLimitedInt = leftLimit + (int) 
	          (random.nextFloat() * (rightLimit - leftLimit + 1));
	        if(randomLimitedInt >90 && randomLimitedInt<=96){
	        	randomLimitedInt=randomLimitedInt+7;
	        }
	        buffer.append((char) randomLimitedInt);

	    }
	    String generatedString = buffer.toString();
	 
	    System.out.println(generatedString);
	    return generatedString;
	}
	
	public boolean createRandomFile() throws IOException{
		String fileName=generateRandomString();
		String filepath = "C:/testdir/"+fileName+".txt";
		File file = new File (filepath);
		BufferedWriter out = new BufferedWriter(new FileWriter(file));
		out.write(fileName);
		out.close();
		return true;
	}
	@Test
	public void test() {
		TestFileProcessor processor =new TestFileProcessor();
		boolean createSuccess = true;
		for(int i=0;i<1000;i++){
			try {
				createSuccess= processor.createRandomFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				createSuccess = false;
				break;
			}
		}
		boolean assertSuccess = true;
		if(!createSuccess){
			fail("Create Failed");
		} else{
			try {
				DirectoryReader reader = new DirectoryReader();
				assertSuccess = reader.execute();
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				fail("Test Failed");
			}
		}
	}

}
