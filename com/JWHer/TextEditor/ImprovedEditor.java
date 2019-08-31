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
		    System.out.println("<"+openPath+"> 에서 <"+lineNum+"> 줄 <"+colNum+"> 에 <"+str+"> 을 붙입니다.");
		   log.append("<"+openPath+"> 에서 <"+lineNum+"> 줄 <"+colNum+"> 에 <"+str+"> 을 붙입니다.\n");
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
				System.err.println("해당 라인을 찾을 수 없습니다.");
				log.append("해당 라인을 찾을 수 없습니다.\n");
				log.setCaretPosition(log.getDocument().getLength());
				return;
				}
		    System.out.println("변경완료");
		    log.append("변경완료\n");
			log.setCaretPosition(log.getDocument().getLength());
		}
		catch (Exception err) {
			System.err.println("파일 검색 실패"+err);
		}
	}
	public void searchWord(String find, int[] lineNum) {
		String line, words[];
		int lineCnt=0, wordCnt=0, found=0;
		report.clrIReport();
		try {
		    System.out.println("<"+openPath+"> 에서 <"+find+"> 의 검색결과는 다음과 같습니다.");
		    result+="<"+openPath+"> 에서 <"+find+"> 의 검색결과는 다음과 같습니다.\r\n";
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
								System.out.println(lineNum[i]+"번 째 줄 "+(wordCnt+1)+"번 째 단어 ");
								result+=lineNum[i]+"번 째 줄 "+(wordCnt+1)+"번 째 단어 \r\n";
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
					System.out.println(lineNum[i]+"번 째 줄 에는 <"+find+"> 를 찾을 수 없습니다.");
					result+=lineNum[i]+"번 째 줄 에는 <"+find+"> 를 찾을 수 없습니다. \r\n";
				}
				found=0;
				lineCnt=0;
		    }
		}
		catch(IllegalArgumentException e) {}
		catch (Exception err) {
			System.err.println("파일 검색 실패"+err);
		}
	}
}
