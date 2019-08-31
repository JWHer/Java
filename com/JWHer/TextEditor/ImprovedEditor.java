package com.JWHer.TextEditor;

import javax.swing.JProgressBar;
import javax.swing.JTextArea;

public class ImprovedEditor extends Editor{
	public ImprovedEditor(JTextArea log, JProgressBar progress) {
		super(log, progress);
	}
	public void appendStr(String str, int lineNum, int colNum, String path) {
		String line;
		int lineCnt=0, found=0, length=0, progress=0;
		try {
			bufferedReader.resetToFirst();
		    System.out.println("<"+openPath+"> ���� <"+lineNum+"> �� <"+colNum+"> �� <"+str+"> �� ���Դϴ�.");
		   log.append("<"+openPath+"> ���� <"+lineNum+"> �� <"+colNum+"> �� <"+str+"> �� ���Դϴ�.\n");
		   log.setCaretPosition(log.getDocument().getLength());
			while(true) {
				lineCnt++;
			    line=bufferedReader.readLine();
				if(line==null) break;
				length=line.length();
				if(lineCnt==lineNum) {
					outbild+=line.substring(0, colNum)+" "+str+" "+line.substring(colNum)+"\r\n";
					found++;
				}
				else
					outbild+=line+"\r\n";
			    if(outbild.length()>4096) {printToFile(path, outbild); outbild=new String();}
			    progress+=length;
			    progressBar.setValue((int) ((progress*100)/fileSize));
			}
			printToFile(path, outbild);
			outbild=new String();
			progressBar.setValue(100);
			if(found==0) {
				System.err.println("�ش� ������ ã�� �� �����ϴ�.");
				log.append("�ش� ������ ã�� �� �����ϴ�.\n");
				log.setCaretPosition(log.getDocument().getLength());
				return;
				}
		    System.out.println("����Ϸ�");
		    log.append("����Ϸ�\n");
			log.setCaretPosition(log.getDocument().getLength());
		}
		catch (Exception err) {
			System.err.println("���� �˻� ����"+err);
		}
	}
	public void searchWord(String find, int[] lineNum) {
		String line, words[];
		int lineCnt=0, wordCnt=0, found=0;
		report.clrIReport();
		try {
		    System.out.println("<"+openPath+"> ���� <"+find+"> �� �˻������ ������ �����ϴ�.");
		    result+="<"+openPath+"> ���� <"+find+"> �� �˻������ ������ �����ϴ�.\r\n";
		    for(int i=0; lineNum[i]!=0; i++) {
		    	bufferedReader.resetToFirst();
		    	while(true) {
					lineCnt++;
				    line=bufferedReader.readLine();
					if(line==null) break;
					if(lineCnt==lineNum[i]) {
						words=line.split(" ");
						for(wordCnt=0; wordCnt<words.length; wordCnt++) {
							if(words[wordCnt].equals(find)) {
								System.out.println(lineNum[i]+"�� ° �� "+(wordCnt+1)+"�� ° �ܾ� ");
								result+=lineNum[i]+"�� ° �� "+(wordCnt+1)+"�� ° �ܾ� \r\n";
								CSearchItem item = new CSearchItem();
								item.setIndexNo(wordCnt+1);
								item.setLineNo(lineCnt);
								report.addSearchItm(item);
								found++;
							}
						}
						break;
					}
		    	}
			    report.addTSNo(found);
				if(found==0) {
					System.out.println(lineNum[i]+"�� ° �� ���� <"+find+"> �� ã�� �� �����ϴ�.");
					result+=lineNum[i]+"�� ° �� ���� <"+find+"> �� ã�� �� �����ϴ�. \r\n";
				}
				found=0;
				lineCnt=0;
		    }
		}
		catch(IllegalArgumentException e) {}
		catch (Exception err) {
			System.err.println("���� �˻� ����"+err);
		}
	}
}
