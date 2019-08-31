package com.JWHer.TextEditor;

public class MethodSearch implements Runnable {
	String searchWord;
	ImprovedEditor engine;
	public MethodSearch(ImprovedEditor e,String s) {
		engine=e;
		searchWord = s;
	}

	public void run() {
		synchronized(engine) {
		engine.searchWord(searchWord);
		}
	}

}
