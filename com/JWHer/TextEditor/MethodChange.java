package com.JWHer.TextEditor;

public class MethodChange implements Runnable {
	String searchWord, changeWord, path;
	ImprovedEditor engine;
	public MethodChange(ImprovedEditor e, String s, String c, String path) {
		searchWord=s;
		changeWord=c;
		this.path=path;
		engine=e;
	}

	public void run() {
		synchronized(engine) {
		engine.writeFile(path, "");
		engine.replaceWord(searchWord, changeWord, path);
		}
	}

}
