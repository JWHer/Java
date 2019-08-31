package com.JWHer.TextEditor;

import java.io.BufferedWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JProgressBar;
import javax.swing.JTextArea;

import edu.dongguk.cse.pl.reportwritter.ReportWriter;

public class Editor {
	String openPath=new String(), result=new String(), changeline=new String(), outbild=new String();
	Long fileSize;
	boolean isFileOpen=false;
	MyBufferedReader bufferedReader;
	CReport report = new CReport();
	ReportWriter reportWriter = new ReportWriter();
	JTextArea log;
	JProgressBar progressBar;
	
	public Editor(JTextArea log, JProgressBar progress) {
		this.log=log;
		this.progressBar=progress;
	}
	
	public boolean openFile(String path) {
		try {
			File file = new File(path);
			if(file.exists()) {
				bufferedReader = new MyBufferedReader(file);
				isFileOpen = true;
				openPath=path;
				fileSize=file.length();
				System.out.println("파일 열기 성공");
				return isFileOpen;
			}
			else System.err.println("파일 열기 실패");
		} catch(Exception err) {
			System.err.println("파일 열기 실패"+err);
		}
		isFileOpen=false;
		return isFileOpen;
	}
	
	public void searchWord(String find) {
		String line, next, words[];
		int lineCnt=1, wordCnt=0, found=0;
		try {
			bufferedReader.resetToFirst();
		    System.out.println("<"+openPath+"> 에서 <"+find+"> 의 검색결과는 다음과 같습니다.");
			log.append("<"+openPath+"> 에서 <"+find+"> 의 검색결과는 다음과 같습니다.\n");
			log.setCaretPosition(log.getDocument().getLength());
		    line=bufferedReader.readLine();
		    int length=line.length();
		    int nextL=0,progress=0;
			while(true) {
				if(line==null)break;
				next=bufferedReader.readLine();
				if(next!=null)nextL=next.length();
				if(!line.endsWith(" ")) {
					if(next!=null) {
						line+=next.split(" ")[0]+" ";
						next=next.substring(next.indexOf(" ")+1);
					}
				}
				words=line.split(" ");
				for(wordCnt=0; wordCnt<words.length; wordCnt++) {
					if(words[wordCnt].equals(find)) {
						System.out.println(lineCnt+"번 째 줄 "+(wordCnt+1)+"번 째 단어 ");
						log.append(lineCnt+"번 째 줄 "+(wordCnt+1)+"번 째 단어\n ");
						log.setCaretPosition(log.getDocument().getLength());
						CSearchItem item = new CSearchItem();
						item.setIndexNo(wordCnt+1);
						item.setLineNo(lineCnt);
						report.addSearchItm(item);
						found++;
					}
				}
				lineCnt++;
				line=next;
				progress+=length;
				progressBar.setValue((int) ((progress*100)/fileSize));////////////////////////////////////////////////
				length=nextL;
			}
			progressBar.setValue(100);
		    System.out.println("이상 검색결과로 총 <"+found+"> 개의 검색이 완료되었습니다.");
			log.append("이상 검색결과로 총 <"+found+"> 개의 검색이 완료되었습니다.\n");
		log.setCaretPosition(log.getDocument().getLength());
			report.setTotalSearchedNo(found);
		}
		catch (Exception err) {
			System.err.println("파일 검색 실패"+err);
		}
	}

	public void writeFile(String path, String result) {
		try {
			File file=new File(path);
			BufferedWriter out;
			if(file.exists()) {
				out = new BufferedWriter(new FileWriter(path, false));
			}
			else {
			      out = new BufferedWriter(new FileWriter(path, true));
			}
		      out.write(result);
		      out.close();
		    } catch (IOException err) {
		        System.err.println("파일 쓰기 에러"+err); // 에러가 있다면 메시지 출력
		        return;
		    }
		System.out.println("성공");
	}
	
	public void replaceWord(String find, String change, String path) {
		String line, next, words[];
		int lineCnt=1, wordCnt=0, found=0;
		try {
			bufferedReader.resetToFirst();
		    System.out.println("<"+openPath+"> 에서 <"+find+">를 <"+change+">로 바꾼결과는 다음과 같습니다.");
			log.append("<"+openPath+"> 에서 <"+find+">를 <"+change+">로 바꾼결과는 다음과 같습니다.\n");
			log.setCaretPosition(log.getDocument().getLength());
		    line=bufferedReader.readLine();
		    int length = line.length();
		    int nextL=0, progress=0;
			while(true) {
				if(line==null)break;
				next=bufferedReader.readLine();
				if(next!=null)nextL=next.length();
				if(!line.endsWith(" ")){
					if(next!=null) {
						line+=next.split(" ")[0]+" ";
						next=next.substring(next.indexOf(" ")+1);
					}
				}
				words=line.split(" ");
				for(wordCnt=0; wordCnt<words.length; wordCnt++) {
					if(words[wordCnt].equals(find)) {
						System.out.println(lineCnt+"번 째 줄 "+(wordCnt+1)+"번 째 단어 ");
						log.append(lineCnt+"번 째 줄 "+(wordCnt+1)+"번 째 단어 \n");
						log.setCaretPosition(log.getDocument().getLength());
						found++;
					}
				}
				changeline+=line.replaceAll(" "+find+" ", " "+change+" ");
			    while(changeline.length()>length) {
			    	outbild+=changeline.substring(0,length)+"\r\n";
			    	changeline=changeline.substring(length);
			    }
			    if(outbild.length()>4096) {printToFile(path, outbild); outbild=new String();}
				lineCnt++;
				line=next;
				progress+=length;
				progressBar.setValue((int) ((progress*100)/fileSize));////////////////////////////////////////////////
				length=nextL;
			}
			printToFile(path, outbild);
			printToFile(path, changeline);
			outbild=new String();
			changeline=new String();
			progressBar.setValue(100);
		    System.out.println("이상 검색결과로 총 <"+found+"> 개의 변경이 <"+path+"> 로 완료되었습니다.");
			log.append("이상 검색결과로 총 <"+found+"> 개의 변경이 <"+path+"> 로 완료되었습니다.\n");
			log.setCaretPosition(log.getDocument().getLength());
		}
		catch (Exception err) {
			System.err.println("파일 검색 실패"+err);
		}
	}
	protected void printToFile(String path, String line) {
		try {
		      BufferedWriter out = new BufferedWriter(new FileWriter(path,true));
		      out.write(line);
		      out.close();
		    } catch (IOException err) {
		        System.err.println("파일 쓰기 에러"+err); // 에러가 있다면 메시지 출력
		        return;
		    }
	}
}//end of class
