package com.JWHer.TextEditor;

public class MethodAppend implements Runnable {
	ImprovedEditor engine;
	String add,path;
	int row, col;
	public MethodAppend(ImprovedEditor e, String a, String path, int r, int c) {
		engine=e;
		add=a; this.path=path;
		row=r; col=c;
	}

	public void run() {
		synchronized(engine){
			engine.writeFile(path, "");
			engine.appendStr(add, row, col, path);
		}
	}

}
