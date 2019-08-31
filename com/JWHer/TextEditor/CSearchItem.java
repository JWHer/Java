package com.JWHer.TextEditor;

import edu.dongguk.cse.pl.reportwritter.ISearchItem;

public class CSearchItem implements ISearchItem{
	int lineNo;
	int indexNo;
	
	public int getIndexNo() {
		return indexNo;
	}

	public int getLineNo() {
		return lineNo;
	}

	public void setIndexNo(int num) {
		indexNo=num;
	}

	public void setLineNo(int num) {
		lineNo=num;
	}

}
