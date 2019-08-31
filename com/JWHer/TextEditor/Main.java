package com.JWHer.TextEditor;

import javax.swing.JFrame;

public class Main extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Frame window;
	public static void main(String[] args) {
		window = new Frame("객체2분반 허정원 2015112098");
		window.setSize(700,500);
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(EXIT_ON_CLOSE);
		window.setVisible(true);
	}
}
