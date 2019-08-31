package com.JWHer.TextEditor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MyBufferedReader extends BufferedReader {
	File file;
	FileInputStream in;
	BufferedReader read;
	boolean eof=false;
	public MyBufferedReader(File file) throws IOException {
		super(new FileReader(file));
		this.file=file;
		in=new FileInputStream(file);
		read = new BufferedReader(new InputStreamReader(in));
	}
	
    public String readLine() throws IOException {
    	try{
    		String str=new String(read.readLine());
    		 return str;
    	}catch(NullPointerException e)
    	{
    		eof=true;
    		return null;
    	}
    }
	
	void resetToFirst() throws IOException {
		in.getChannel().position(0);
		read = new BufferedReader(new InputStreamReader(in));
		eof=false;
	}
	
	void find(int lineNum) throws IOException {
		in.getChannel().position(lineNum-1);
		read = new BufferedReader(new InputStreamReader(in));
		eof=false;
	}
}
