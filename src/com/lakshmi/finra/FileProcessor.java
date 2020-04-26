package com.lakshmi.finra;

import java.io.File;
import java.util.Map;
import java.util.concurrent.Callable;

public class FileProcessor implements Callable<Boolean>{
	private String filePath;
	public FileProcessor(String filePath){
		this.filePath = filePath;
	}
	@Override
	public Boolean call() throws Exception {
		File oldFile= new File(this.filePath);
		String fileName = oldFile.getName();
		String pathTodir= this.filePath.replace(fileName,"");
		System.out.println(fileName);
		System.out.println(pathTodir);
		String newFilePath = pathTodir+fileName.replaceAll( "[A-Z]", "" );
		System.out.println(newFilePath);
		File newFile= new File(newFilePath);
		if(oldFile.renameTo(newFile)){
			System.out.println("Rename succesful");
			return true;
		}else{
			System.out.println("Rename failed");
			return false;
		}
	}
	public static void main(String args[]){
		String s = "Pplease rRemove UUuPPPpperCCsase".replaceAll( "[A-Z]", "" );
		System.out.println(s);
	}
}
