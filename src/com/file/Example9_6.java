package com.file;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileFilter;

public class Example9_6 {

	public static void main(String[] args) {
		FrameFileDialog f = new FrameFileDialog();
	}
}

 class FrameFileDialog extends JFrame implements ActionListener{
	 JFileChooser filedialog = null;
	 JLabel label = new JLabel("",JLabel.CENTER);
	 JButton b1,b2;
	 JTextArea text;
	 FrameFileDialog(){
		 super("���ļ��Ի���Ĵ���");
		 Container con = getContentPane();
		 con.setLayout(new BorderLayout());
		 con.setSize(40, 50);
		 JPanel p = new JPanel();
		 b1 = new JButton("���ļ�");
		 b2 = new JButton("�����ļ�");
		 b1.addActionListener(this);
		 b2.addActionListener(this);
		 p.add(b1);p.add(b2);
		 text = new JTextArea(20,30);
		 JScrollPane jsp = new JScrollPane(text);
		 filedialog = new JFileChooser("D:\\wor");
		 filedialog.setControlButtonsAreShown(true);
		 filedialog.addChoosableFileFilter(new MyFileFilter("txt"));
		 filedialog.addChoosableFileFilter(new MyFileFilter("java"));
		 text.setBackground(Color.cyan);
		 con.add(jsp,BorderLayout.CENTER);
		 con.add(label,BorderLayout.NORTH);
		 con.add(p,BorderLayout.SOUTH);
		 this.setVisible(true);
		 this.pack();
	 }
	 
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		File file = null;
		int result;
		if(e.getSource() == b1){
			filedialog.setDialogTitle("���ļ�");
			result = filedialog.showOpenDialog(this);
			text.setText("");
			if(result == JFileChooser.APPROVE_OPTION){
				file = filedialog.getSelectedFile();
				label.setText("��ѡ��򿪵��ļ������ǣ�"+file.getName());
			}else if(result == JFileChooser.CANCEL_OPTION){
				label.setText("��û��ѡ���κ��ļ�");
			}
			FileInputStream fileStream = null;
			if(file != null){
				try{
					fileStream = new FileInputStream(file);
				}catch(FileNotFoundException nfe){
					label.setText("�ļ�û���ҵ�");
					return;
				}
				int readByte;
				try{
					while((readByte = fileStream.read()) != -1)
						text.append(String.valueOf((char)readByte));
					fileStream.close();
				}catch(IOException ie){
					label.setText("��ȡ�ļ�����");
				}
			}
		}else if(e.getSource() == b2){
			filedialog.setDialogTitle("�����ļ�");
			result = filedialog.showSaveDialog(this);
			file = null; 
			String fileName;
			if(result == JFileChooser.APPROVE_OPTION){
				file = filedialog.getSelectedFile();
				label.setText("��ѡ�񱣴���ļ��ǣ�"+file.getName());
				
			}else if(result == JFileChooser.CANCEL_OPTION){
				label.setText("��û��ѡ���κ��ļ�");
			}
			FileOutputStream fileStream = null;
			if(file !=null){
				try{
					fileStream = new FileOutputStream(file);
					
				}catch(FileNotFoundException nfe){
					label.setText("�ļ�û�з���");
					return;
				}
				String content = text.getText();
				try{
					fileStream.write(content.getBytes());
				}catch(IOException ie){
					label.setText("д�ļ�����");
				}
			}
		}
	}
	 
 }
 
 class MyFileFilter extends FileFilter{
	 
	String ext;
	MyFileFilter(String t){
		ext = t;
	}
	@Override
	public boolean accept(File f) {
		// TODO Auto-generated method stub
		if(f.isDirectory())
			return true;
		String fn = f.getName();
		int index = fn.lastIndexOf(".");
		if(index>0 && index<fn.length()-1){
			String extension = fn.substring(index+1).toLowerCase();
			if(extension.equals(ext))
				return true;
		}
		return false;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		if(ext.equals("java"))
			return "JAVA Source File(*.java)";
		if(ext.equals("txt"))
			return "Txt File(*.txt)";
		return "";
	}
	 
	 
 }