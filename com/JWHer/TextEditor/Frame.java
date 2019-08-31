package com.JWHer.TextEditor;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Frame extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JTextField openPath = new JTextField();
	JTextField savePath = new JTextField();
	JTextField searchWord= new JTextField();
	JTextField changeWord= new JTextField();
	JTextField addWord= new JTextField();
	JTextField linNum= new JTextField();
	JTextField colNum= new JTextField(11);
	JLabel mainStatus = new JLabel("ȯ���մϴ�!");
	JLabel openStatus = new JLabel(" ");
	JLabel saveStatus = new JLabel(" ");
	JButton openButton = new JButton("����");
	JButton saveButton = new JButton("ã��");
	JButton searchButton = new JButton("�˻�");
	JButton changeButton = new JButton("��ȯ");
	JButton addButton = new JButton("����");
	JButton reportButton = new JButton("�ɰ����");
	JTextArea log=new JTextArea(10, 10);
	JScrollPane logScroll=new JScrollPane(log);
	JPanel panel_fileArea=new JPanel(); 
	
	JProgressBar Progress=new JProgressBar();
	
	JMenu menu1 = new JMenu("����");
	JMenuItem openMenu=new JMenuItem("����");
	JMenuItem saveMenu=new JMenuItem("����");
	JMenuItem exitMenu=new JMenuItem("����");
	JMenu menu2 = new JMenu("����");
	JMenuItem srchMenu=new JMenuItem("�˻�");
	JMenuItem chngMenu=new JMenuItem("��ȯ");
	JMenu menu3 = new JMenu("����");
	JMenuItem infoMenu=new JMenuItem("����");
	JMenuBar bar = new JMenuBar();
	
	GridBagLayout grid=new GridBagLayout();
	GridBagConstraints gc = new GridBagConstraints();
	
	private JFileChooser fileChooser = new JFileChooser();
	ImprovedEditor engine = new ImprovedEditor(log, Progress);
	
	public Frame(String title) {
		super(title);
		panel_fileArea.setLayout(grid);
		gc.fill=GridBagConstraints.HORIZONTAL;
		
		menu1.add(openMenu);
		openMenu.addActionListener(this);
		menu1.add(saveMenu);
		saveMenu.addActionListener(this);
		menu1.add(exitMenu);
		exitMenu.addActionListener(this);
		menu2.add(srchMenu);
		srchMenu.addActionListener(this);
		menu2.add(chngMenu);
		chngMenu.addActionListener(this);
		menu3.add(infoMenu);
		infoMenu.addActionListener(this);
		bar.add(menu1);
		bar.add(menu2);
		bar.add(menu3);
		
		/*
		ImageIcon ic = new ImageIcon("D:\\2018_1��ü����\\�ڹ� �ҽ�\\earth.jpg");
		Image originImg = ic.getImage();
		Image changedImg=originImg.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		ImageIcon Icon = new ImageIcon(changedImg);
		JLabel imagebox = new JLabel(Icon);
		*/
		
		//panel_fileArea.add(new JLabel("���"));
		//panel_fileArea.add(openPath);
		//panel_fileArea.add(new JLabel(""));
		//gbAdd(new JLabel(""),1,1,1,1,0);
		gbAdd(new JLabel("  �˻����"),0,0,1,1,0);
		gbAdd(openPath, 1,0,5,1,0.4); openPath.setToolTipText("��θ� �Է����ּ���...");
		openPath.addActionListener(this);
		gbAdd(openButton,6,0,1,1,0);
		openButton.addActionListener(this);
		gbAdd(openStatus,7,0,3,1,0.2);
		gbAdd(new JLabel(""),10,0,1,1,0.4);
		
		gbAdd(new JLabel("  ������"),0,1,1,1,0);
		gbAdd(savePath, 1,1,5,1,0.4); savePath.setToolTipText("��θ� �Է����ּ���...");
		savePath.addActionListener(this);
		gbAdd(saveButton,6,1,1,1,0);
		saveButton.addActionListener(this);
		gbAdd(saveStatus,7,1,3,1,0.2);
		gbAdd(new JLabel(""),10,1,1,1,0.6);
		
		gbAdd(new JLabel(" "),1,2,1,1,0);//����
		
		gbAdd(new JLabel("  ã���ܾ�"),0,3,1,1,0);
		gbAdd(searchWord, 1,3,5,1,0.4);searchWord.setToolTipText("�ܾ �Է����ּ���..");
		searchWord.addActionListener(this);
		gbAdd(searchButton,6,3,1,1,0);
		searchButton.addActionListener(this);
		gbAdd(new JLabel(""),7,3,1,1,0.6);
		
		gbAdd(new JLabel("  �ٲܴܾ�"),0,4,1,1,0);
		gbAdd(changeWord, 1,4,5,1,0.4);changeWord.setToolTipText("�ܾ �Է����ּ���...");
		changeWord.addActionListener(this);
		gbAdd(changeButton,6,4,1,1,0);
		changeButton.addActionListener(this);
		gbAdd(new JLabel(""),7,4,1,1,0.6);
		
		gbAdd(new JLabel("  ���ϴܾ�"),0,5,1,1,0);
		gbAdd(addWord,1,5,5,1,0.4);addWord.setToolTipText("�ܾ �Է����ּ���...");
		gbAdd(addButton,6,5,1,2,0);
		addButton.addActionListener(this);
		gbAdd(new JLabel(""),7,5,1,1,0.6);
		
		gbAdd(new JLabel("               ��"),0,6,1,1,0);
		gbAdd(linNum,1,6,2,1,0.2);linNum.setToolTipText("���� �Է����ּ���...");
		gbAdd(new JLabel("  ��"),3,6,1,1,0);
		gbAdd(colNum,4,6,2,1,0.2);colNum.setToolTipText("���� �Է����ּ���...");
		
		gbAdd(mainStatus,1,7,1,1,0.4);
		gbAdd(new JLabel(""),2,7,1,1,0.6);
		gbAdd(logScroll,1,8,7,10,0.5);
		log.setEditable(false);
		
		gbAdd(new JLabel(""),0,19,1,1,0.2);
		gbAdd(Progress,1,19,7,1,0.6);
		Progress.setValue(0);
		gbAdd(new JLabel(""),8,19,1,1,0.2);
		
		gbAdd(reportButton,1,20,7,1,0.6);
		reportButton.addActionListener(this);
		
		setJMenuBar(bar);
		add(panel_fileArea);
	}
	//�׼�
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==openButton||e.getSource()==openMenu) {
			int returnPath=fileChooser.showOpenDialog(this);
			if(returnPath==JFileChooser.APPROVE_OPTION) {
				String fileName=fileChooser.getSelectedFile().toString();
				openPath.setText(fileName);
				if(engine.openFile(fileName)) {openStatus.setText(fileName+" ����"); openPath.setBackground(Color.green);}
				else {openStatus.setText("������ ���� ���߽��ϴ�"); openPath.setBackground(Color.pink);}
			}
			else {openStatus.setText("������ ���� ���߽��ϴ�");openPath.setBackground(Color.pink);}
		}
		else if(e.getSource()==openPath) {
			String fileName=openPath.getText();
			if(engine.openFile(fileName)) {openStatus.setText(fileName+" ����");openPath.setBackground(Color.green);}
			else {openStatus.setText("������ ���� ���߽��ϴ�");openPath.setBackground(Color.pink);}
		}
		else if(e.getSource()==saveButton||e.getSource()==saveMenu) {
			int returnPath=fileChooser.showOpenDialog(this);
			if(returnPath==JFileChooser.APPROVE_OPTION) {
				String fileName=fileChooser.getSelectedFile().toString();
				savePath.setText(fileName);
				saveStatus.setText(fileName+"�� �����մϴ�");
			}
			else saveStatus.setText("��θ� ������ �ּ���");
		}
		else if(e.getSource()==savePath) {
			String fileName=savePath.getText();
			saveStatus.setText(fileName+" �� �����մϴ�");
		}
		else if(e.getSource()==searchButton||e.getSource()==searchWord||e.getSource()==srchMenu) {
			Progress.setValue(0);
			if(!engine.isFileOpen) {mainStatus.setText("������ ���� �����ּ���!");}
			else if(searchWord.getText().length()==0){mainStatus.setText("ã���ܾ ������ �ּ���!");}
			else {
			Thread search = new Thread(new MethodSearch(engine,searchWord.getText()));
			search.start();
			mainStatus.setText("�˻�...");
			}
		}
		else if(e.getSource()==changeButton||e.getSource()==changeWord||e.getSource()==chngMenu) {
			Progress.setValue(0);
			if(!engine.isFileOpen)
				mainStatus.setText("������ ���� �����ּ���!");
			else if(savePath.getText().equals(engine.openPath)) {
				System.out.println("������ �����Դϴ�");
				mainStatus.setText("������ �����Դϴ�");
			}
			else if(savePath.getText().length()==0)
				mainStatus.setText("�����θ� ������ �ּ���!");
			else if(searchWord.getText().length()==0)
				mainStatus.setText("ã���ܾ ������ �ּ���!");
			else if(changeWord.getText().length()==0)
				mainStatus.setText("�ٲܴܾ ������ �ּ���!");
			else {
				Thread change = new Thread(new MethodChange(engine,searchWord.getText(),changeWord.getText(),savePath.getText()));
				change.start();
				mainStatus.setText("��ȯ...");
			}
		}
		else if(e.getSource()==exitMenu) {
			System.exit(0);
		}
		else if(e.getSource()==infoMenu) {
			Frame information = new Frame("����");
			JPanel producer = new JPanel();
			producer.add(new JLabel("������ : ������"));
			producer.add(new JLabel("01085203971"));
			information.add(producer);
			information.setJMenuBar(null);
			information.setSize(300,200);
			information.setLocationRelativeTo(null);
			information.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			information.setVisible(true);
		}
		else if(e.getSource()==reportButton) {
			if(savePath.getText().length()==0)
				mainStatus.setText("�����θ� ������ �ּ���!");
			else {
				Progress.setValue(0);
				Thread report = new Thread(new MethodReport(engine,savePath.getText()));
				report.start();
				mainStatus.setText("���...");
				log.append("�˻������ <"+savePath.getText()+">�� �����մϴ�. \n");
				log.setCaretPosition(log.getDocument().getLength());
				}
		}
		else if(e.getSource()==addButton) {
			if(!engine.isFileOpen)
				mainStatus.setText("������ ���� �����ּ���!");
			else if(savePath.getText().equals(engine.openPath)) {
				System.out.println("������ �����Դϴ�");
				mainStatus.setText("������ �����Դϴ�");
			}
			else if(savePath.getText().length()==0)
				mainStatus.setText("�����θ� ������ �ּ���!");
			else if(addWord.getText().length()==0)
				mainStatus.setText("���ϴܾ ������ �ּ���!");
			else {
				try {
					int row=Integer.parseInt(linNum.getText());
					int col=Integer.parseInt(colNum.getText());
					Thread append = new Thread(new MethodAppend(engine, addWord.getText(), savePath.getText(), row, col));
					append.start();
					mainStatus.setText("���̱�...");
					}
				catch(Exception err) {mainStatus.setText("���ڸ� �Է��ϼ���!");}
			}
		}
	}
	
	//�׸���� ��ġ
	   private void gbAdd(Component c, int x, int y, int w, int h, double weightx) {
		      gc.gridx = x;
		      gc.gridy = y;  
		      gc.gridwidth  = w;	//����
		      gc.gridheight = h;	//����
		      gc.weightx=weightx;
		      
		      grid.setConstraints(c, gc); 
		      panel_fileArea.add(c);
		   }
		 
}