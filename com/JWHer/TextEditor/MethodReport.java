package com.JWHer.TextEditor;

import java.io.IOException;

public class MethodReport implements Runnable {
	String path;
	ImprovedEditor engine;
	public MethodReport(ImprovedEditor e, String path) {
		engine=e;
		this.path=path;
	}

	public void run() {
		synchronized(engine) {
		engine.report.setFileName(path);
		try {
			engine.reportWriter.makeReport(engine.report);
		} catch (IOException e) {
			System.err.println("결과출력에러"+e);
		}
		engine.progressBar.setValue(100);
		}
	}

}
