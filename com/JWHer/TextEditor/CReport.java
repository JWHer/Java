package com.JWHer.TextEditor;

import java.util.ArrayList;

import edu.dongguk.cse.pl.reportwritter.IReport;
import edu.dongguk.cse.pl.reportwritter.ISearchItem;

public class CReport implements IReport{
	int totalSearchedNo; //�Ѱ˻�����
	String fileName; //����� �����̸�
	ArrayList<ISearchItem> itemList; //�˻��������Ʈ

	public void clrIReport() {
		itemList=null;
		totalSearchedNo=0;
		fileName=null;
	}
	
	public void addTSNo(int searchedNo) {
		totalSearchedNo+=searchedNo;
	}
	
	public void addSearchItm(ISearchItem item) {
		if(itemList == null){
			itemList = new ArrayList<ISearchItem>();
			}
		itemList.add(item);
	}

	public String getFileName() {
		return fileName;
	}

	public ArrayList<ISearchItem> getSearchList() {
		return itemList;
	}

	public int getTotalSearchedNo() {
		return totalSearchedNo;
	}

	public void setFileName(String name) {
		if(fileName == null){
			fileName = new String();
			}
		fileName=name;
	}

	public void setTotalSearchedNo(int searchedNo) {
		totalSearchedNo=searchedNo;		
	}

}
